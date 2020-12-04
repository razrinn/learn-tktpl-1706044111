package id.ac.ui.cs.mobileprogramming.helloworld

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class WifiViewModel() : ViewModel() {
    private val allWifi = MutableLiveData<ArrayList<String>>()


    fun get(): MutableLiveData<ArrayList<String>> {
        return allWifi
    }

    fun add(string: String) {
        allWifi.value?.add(string)
        allWifi.value = allWifi.value
    }

    fun reset(){
        allWifi.value = ArrayList()
        allWifi.value = allWifi.value
    }

    init {
        allWifi.value = ArrayList()
    }
}