package id.ac.ui.cs.mobileprogramming.helloworld

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import java.lang.Thread.sleep
import kotlin.math.floor

class TimerActivity : AppCompatActivity() {
    private var minuteToCountdown = 5
    private var isCounting = false
    private var seconds = minuteToCountdown * 60
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_timer)
        val btnStartTime = findViewById<Button>(R.id.startButton)
        val btnStopTime = findViewById<Button>(R.id.stopButton)
        val btnIncrement = findViewById<Button>(R.id.incrementCounter)
        val btnDecrement = findViewById<Button>(R.id.decrementCounter)
        val txtTimeToCount = findViewById<TextView>(R.id.timeToCount)
        val txtTimeCounter = findViewById<TextView>(R.id.timeCounter)
        btnIncrement.setOnClickListener{
            incrementTimer()
        }
        btnDecrement.setOnClickListener{
            decrementTimer()
        }
        btnStartTime.setOnClickListener{
            initTimer()
        }
        btnStopTime.setOnClickListener{
            stopTimer()
        }
        btnStopTime.isEnabled = false
        txtTimeToCount.text = "${minuteToCountdown} menit"
        txtTimeCounter.text = "00:00"

    }
    private fun initTimer(){
        val btnStartTime = findViewById<Button>(R.id.startButton)
        val btnStopTime = findViewById<Button>(R.id.stopButton)
        val txtTimeCounting = findViewById<TextView>(R.id.timeCounter)
        btnStartTime.isEnabled = false
        btnStopTime.isEnabled = true
        isCounting = true
        Log.d("INI LOG", "MASUK ONCLICK")
        txtTimeCounting.text = getMinuteSecond(seconds)

        val runnableTick = Runnable {
            txtTimeCounting.text = getMinuteSecond(seconds)
        }
        val runnableFinish = Runnable {
            isCounting = false
            btnStartTime.isEnabled = true
            btnStopTime.isEnabled = false
        }
        Thread(Runnable {
            while(seconds > 0 && isCounting){
                sleep(1000)
                seconds -= 1
                txtTimeCounting.post(runnableTick)
            }
            txtTimeCounting.post(runnableFinish)
        }).start()
    }

    private fun stopTimer(){
        isCounting = false
        val btnStartTime = findViewById<Button>(R.id.startButton)
        val btnStopTime = findViewById<Button>(R.id.stopButton)
        btnStartTime.isEnabled = true
        btnStopTime.isEnabled = false
    }
    private fun incrementTimer(){
        val textView = findViewById<TextView>(R.id.timeToCount)
        val txtTimeCounter = findViewById<TextView>(R.id.timeCounter)
        if(!isCounting) {
            minuteToCountdown += 1
            seconds += 60
            textView.text = "${minuteToCountdown} menit"
            txtTimeCounter.text = getMinuteSecond(seconds)
        }
    }
    private fun decrementTimer(){
        val textView = findViewById<TextView>(R.id.timeToCount)
        val txtTimeCounter = findViewById<TextView>(R.id.timeCounter)
        if (minuteToCountdown > 0 && !isCounting){
            minuteToCountdown -= 1
            seconds -= 60
            textView.text = "${minuteToCountdown} menit"
            txtTimeCounter.text = getMinuteSecond(seconds)
        }
    }

    private fun getMinuteSecond(int: Int): String{
        val minutes = floor(((int / 60) % 60).toDouble()).toInt().toString().padStart(2, '0')
        val seconds = (int % 60).toString().padStart(2, '0')
        return "${minutes}:${seconds}"
    }
}

