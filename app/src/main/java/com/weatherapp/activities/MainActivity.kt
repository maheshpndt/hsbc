package com.weatherapp.activities

import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.weatherapp.R
import com.weatherapp.adapters.Adapter
import com.weatherapp.api.WeatherApiHelper
import com.weatherapp.api.WeatherServiceImplementation
import com.weatherapp.models.ListItem
import com.weatherapp.utils.Constants.showAlertDialog
import com.weatherapp.utils.Status
import com.weatherapp.view_model.MainViewModel
import com.weatherapp.view_model.ViewModelFactory

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var mainViewModel: MainViewModel
    private lateinit var mAdapter: Adapter
    private lateinit var weatherList: List<ListItem>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initView()

        initViewModel()

        setupObserver()

    }

    private fun initView() {
        val button = findViewById<Button>(R.id.button)
        button.setOnClickListener(this)
    }

    /**
     * view model initialization
     */
    private fun initViewModel() {
        mainViewModel = ViewModelProviders.of(
                this,
                ViewModelFactory(WeatherApiHelper(WeatherServiceImplementation()))
        ).get(MainViewModel::class.java)
    }

    /**
     * Function is used to get the weather details
     */
    private fun setupObserver() {
        mainViewModel.getWeather().observe(this, Observer {
            when (it.status) {
                Status.SUCCESS -> {
                    println(it.data)
                    setAdapter(it.data!!.list)
                }
                Status.LOADING -> {
                    //TODO : show loading
                }
                Status.ERROR -> {
                    //TODO : hide loading
                    Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()
                }
            }
        })
    }

    /**
     * Method executed to set data to the recycler view
     */
    private fun setAdapter(weatherList: List<ListItem>) {
        this.weatherList = weatherList
        val recyclerView: RecyclerView = findViewById(R.id.recyclerView)
        mAdapter = Adapter(weatherList)
        val layoutManager = LinearLayoutManager(applicationContext)
        recyclerView.layoutManager = layoutManager
        recyclerView.itemAnimator = DefaultItemAnimator()
        recyclerView.adapter = mAdapter
    }

    override fun onClick(p0: View?) {
        if (p0 != null) {
            if (p0.id == R.id.button) {
                val editText = findViewById<EditText>(R.id.editText)
                if (!TextUtils.isEmpty(editText.text)) {
                    val someList: List<String> =
                        weatherList.filter { it.name.startsWith(editText.text) }
                            .map { it.name }
                    val count = someList.count()
                    showAlertDialog(this,"Count of number of cities started with $count")
                } else {
                    showAlertDialog(this,getString(R.string.errorName))
                }
            }
        }
    }
}