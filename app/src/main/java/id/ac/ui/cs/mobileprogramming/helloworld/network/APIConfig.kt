package id.ac.ui.cs.mobileprogramming.helloworld.network
import id.ac.ui.cs.mobileprogramming.helloworld.network.model.WifiResponse
import id.ac.ui.cs.mobileprogramming.helloworld.network.model.WifiRequest
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface APIConfig {
    @POST(".")
    fun sendWifi(@Body requestBody: WifiRequest): Call<WifiResponse>?

    companion object {
        const val BASE_URL = "https://97c96bb5d2dfa80a7926519bd838294c.m.pipedream.net"
    }
}