package id.ac.ui.cs.mobileprogramming.razrinn.helloworld

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    private var counter = 0
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val downloadRamButton = findViewById<Button>(R.id.button)
        downloadRamButton.setOnClickListener {
            // make a toast on button click event
            Toast.makeText(this, "Downloaded 4GB RAM", Toast.LENGTH_LONG).show()
        }
        val addCounterButton = findViewById<Button>(R.id.button2)
        val counterValue = findViewById<TextView>(R.id.counter)
        counterValue.text = """$counter"""
        addCounterButton.setOnClickListener{
            counterValue.text = """${addCounter(counter)}"""
            this.counter += 1
        }
    }
    fun addCounter(counter: Int): Int {
        return counter + 1
    }
}