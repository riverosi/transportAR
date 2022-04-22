package ar.com.eldars.transporte.servicies

import android.app.IntentService
import android.content.Intent
import android.util.Log
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley

class ClimateService : IntentService("Climate Service") {
    companion object {
        const val TAG = "ClimateService"
        const val ACTION_TEMPERATURE = "ar.com.eldars.transporte.action.temperature"
        const val EXTRA_TEMPERATURE = "ar.com.eldars.transporte.extra.temperature"
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Log.d(TAG, "onStartCommand")
        return super.onStartCommand(intent, flags, startId)
    }

    override fun onHandleIntent(intent: Intent?) {
        val queue = Volley.newRequestQueue(this)
        val city = intent?.getStringExtra("city") ?: "argentina"
        val token = assets.open("token.txt").bufferedReader().use { it.readText()}
        val url = "https://api.openweathermap.org/data/2.5/weather?q=$city&appid=$token&units=metric"
        Log.d(TAG, "jsonOk")
        val jsonObjectRequest = JsonObjectRequest(
            Request.Method.GET, url, null,
            {
                val temperature : Double = it.getJSONObject("main").getDouble("temp")
                Log.d(TAG, "jsonOk")
                val broadCastIntent = Intent(ACTION_TEMPERATURE)
                broadCastIntent.putExtra(EXTRA_TEMPERATURE, temperature)
                sendBroadcast(broadCastIntent)
            },
            null)
        queue.add(jsonObjectRequest)
    }

}