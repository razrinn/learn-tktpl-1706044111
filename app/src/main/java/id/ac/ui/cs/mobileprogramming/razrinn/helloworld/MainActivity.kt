package id.ac.ui.cs.mobileprogramming.razrinn.helloworld

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnClickMe = findViewById<Button>(R.id.button)

        btnClickMe.setOnClickListener {
            // make a toast on button click event
            Toast.makeText(this, "Downloaded 4GB RAM", Toast.LENGTH_LONG).show()
        }
    }
}