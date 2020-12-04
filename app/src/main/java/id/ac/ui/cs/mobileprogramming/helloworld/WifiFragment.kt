package id.ac.ui.cs.mobileprogramming.helloworld

import android.Manifest
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.content.pm.PackageManager
import android.net.wifi.WifiManager
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import id.ac.ui.cs.mobileprogramming.helloworld.network.APIConfig
import id.ac.ui.cs.mobileprogramming.helloworld.network.model.WifiRequest
import id.ac.ui.cs.mobileprogramming.helloworld.network.model.WifiResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


/**
 * A fragment representing a list of Items.
 */
class WifiFragment : Fragment() {

    private var columnCount = 1
    private lateinit var wifiManager: WifiManager
    private lateinit var wifiRecycleView: RecyclerView
    private var wifiViewModel = WifiViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            columnCount = it.getInt(ARG_COLUMN_COUNT)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_wifi_list, container, false)
        val fab: FloatingActionButton? = activity?.findViewById(R.id.fab)
        fab?.setOnClickListener {
            sendToAPI()
        }
        wifiManager = activity?.applicationContext?.getSystemService(Context.WIFI_SERVICE) as WifiManager
        wifiRecycleView = view.findViewById(R.id.list)
        if (hasPermission()) {
            startScan()
        } else{
            requestPermission()
        }
        return view
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        when (requestCode) {
            WIFI_REQUEST_CODE -> if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                startScan()
            } else {
                createToast(WIFI_PERMISSION_DENIED)
            }
            else -> super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        }
    }

    private fun hasPermission(): Boolean {
        val result =
            activity?.applicationContext?.let {
                ContextCompat.checkSelfPermission(it, Manifest.permission.ACCESS_COARSE_LOCATION)
            }
        return result == PackageManager.PERMISSION_GRANTED
    }

    private fun requestPermission() {
        activity?.let {
            ActivityCompat.requestPermissions(
                it,
                arrayOf(
                    Manifest.permission.ACCESS_COARSE_LOCATION,
                    Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.ACCESS_WIFI_STATE
                ),
                WIFI_REQUEST_CODE
            )
        }
    }

    private fun startScan(){
        val wifiScanReceiver = object : BroadcastReceiver() {
            @RequiresApi(Build.VERSION_CODES.M)
            override fun onReceive(context: Context, intent: Intent) {
                val success = intent.getBooleanExtra(WifiManager.EXTRA_RESULTS_UPDATED, false)
                if (success) {
                    handleSuccess()
                } else {
                    createToast(WIFI_SCAN_FAILED)
                }
            }
        }

        val intentFilter = IntentFilter()
        intentFilter.addAction(WifiManager.SCAN_RESULTS_AVAILABLE_ACTION)
        activity?.applicationContext?.registerReceiver(wifiScanReceiver, intentFilter)

        val success = wifiManager.startScan()
        if (!success) {
            createToast(WIFI_SCAN_FAILED)
        }
    }

    private fun handleSuccess() {
        wifiViewModel.reset()
        val results = wifiManager.scanResults
        for (res in results) {
            wifiViewModel.add(res.SSID)
        }
        wifiRecycleView.layoutManager = LinearLayoutManager(activity?.applicationContext)
        wifiViewModel.get().observe(this, Observer {
            val recyclerAdapter =  WifiAdapter(it)
            wifiRecycleView.adapter = recyclerAdapter
        })
        if(results.isEmpty()){
            createToast(NO_WIFI_NEARBY)
        }
    }

    private fun createToast(string: String){
        Toast.makeText(
            activity,
            string,
            Toast.LENGTH_LONG
        ).show()
    }

    @Synchronized
    private fun sendToAPI(){
        val rf = Retrofit.Builder()
            .baseUrl(APIConfig.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val request = rf.create(APIConfig::class.java)
        wifiViewModel.get().observe(this, Observer {
            val call = request.sendWifi(WifiRequest(it))
            call?.enqueue(object: Callback<WifiResponse?> {
                override fun onFailure(call: Call<WifiResponse?>, t: Throwable) {
                    createToast(FAILED_TO_POST)
                }
                override fun onResponse(call: Call<WifiResponse?>, response: Response<WifiResponse?>) {
                    createToast(SUCCESS_TO_POST)
                }
            })
        })
    }

    companion object {

        const val ARG_COLUMN_COUNT = "column-count"
        const val WIFI_REQUEST_CODE = 999
        const val WIFI_PERMISSION_DENIED = "Wifi permission denied"
        const val WIFI_SCAN_FAILED = "Wifi scan failed"
        const val NO_WIFI_NEARBY = "No nearby Wifi detected"
        const val FAILED_TO_POST = "Send Wifi data failed"
        const val SUCCESS_TO_POST = "Send Wifi data successful"
        @JvmStatic
        fun newInstance(columnCount: Int) =
            WifiFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_COLUMN_COUNT, columnCount)
                }
            }
    }
}