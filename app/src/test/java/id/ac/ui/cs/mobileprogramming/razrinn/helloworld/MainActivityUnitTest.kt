package id.ac.ui.cs.mobileprogramming.razrinn.helloworld

import org.junit.Assert
import org.junit.Test

class MainActivityUnitTest{
    private var activity = MainActivity()
    @Test
    fun addCounter_isCorrect(){
        Assert.assertEquals(activity.addCounter(1), 2)
    }
}