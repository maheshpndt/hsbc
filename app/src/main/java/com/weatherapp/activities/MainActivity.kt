package com.weatherapp.activities

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.gms.location.*
import com.weatherapp.R
import com.weatherapp.adapters.Adapter
import com.weatherapp.models.ResponseWeather
import com.weatherapp.network.WeatherService
import com.weatherapp.utils.Constants
import retrofit.*

class MainActivity : AppCompatActivity() {

    private lateinit var mAdapter: Adapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        getWeatherDetails();

    }

    /**
     * Function is used to get the weather details of the current location based on the latitude longitude
     */
    private fun getWeatherDetails() {

        if (Constants.isNetworkAvailable(this@MainActivity)) {
            /**
             * Add the built-in converter factory first. This prevents overriding its
             * behavior but also ensures correct behavior when using converters that consume all types.
             */
            val retrofit: Retrofit = Retrofit.Builder()
                // API base URL.
                .baseUrl(Constants.BASE_URL)
                /** Add converter factory for serialization and deserialization of objects. */
                /**
                 * Create an instance using a default {@link Gson} instance for conversion. Encoding to JSON and
                 * decoding from JSON (when no charset is specified by a header) will use UTF-8.
                 */
                .addConverterFactory(GsonConverterFactory.create())
                /** Create the Retrofit instances. */
                .build()
            // END

            /**
             * Here we map the service interface in which we declares the end point and the API type
             *i.e GET, POST and so on along with the request parameter which are required.
             */
            val service: WeatherService =
                retrofit.create<WeatherService>(WeatherService::class.java)

            /** An invocation of a Retrofit method that sends a request to a web-server and returns a response.
             * Here we pass the required param in the service
             */
            val listCall: Call<ResponseWeather> = service.getWeather()

            // Callback methods are executed using the Retrofit callback executor.
            listCall.enqueue(object : Callback<ResponseWeather> {
                override fun onResponse(
                    response: Response<ResponseWeather>,
                    retrofit: Retrofit
                ) {

                    // Check weather the response is success or not.
                    if (response.isSuccess) {

                        /** The de-serialized response body of a successful response. */
                        val weatherList: ResponseWeather = response.body()
                        Log.i("Response Result", "$weatherList")

                        setAdapter(weatherList)

                    } else {
                        // If the response is not success then we check the response code.
                        val sc = response.code()
                        when (sc) {
                            400 -> {
                                Log.e("Error 400", "Bad Request")
                            }
                            404 -> {
                                Log.e("Error 404", "Not Found")
                            }
                            else -> {
                                Log.e("Error", "Generic Error")
                            }
                        }
                    }
                }

                override fun onFailure(t: Throwable) {
                    Log.e("LOG", t.message.toString())
                }
            })
            // END

        } else {
            Toast.makeText(
                this@MainActivity,
                "No internet connection available.",
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    /**
     * Method executed to set data to the recycler view
     */
    private fun setAdapter(weatherList: ResponseWeather) {
        val recyclerView: RecyclerView = findViewById(R.id.recyclerView)
        mAdapter = Adapter(weatherList.list)
        val layoutManager = LinearLayoutManager(applicationContext)
        recyclerView.layoutManager = layoutManager
        recyclerView.itemAnimator = DefaultItemAnimator()
        recyclerView.adapter = mAdapter
    }
}