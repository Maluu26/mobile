package com.malu.sensores

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorManager
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.malu.sensores.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val sensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager
        val allSensors: List<Sensor> = sensorManager.getSensorList(Sensor.TYPE_ALL)
    }
    private fun joinOnStrings(allSensors: List<Sensor>){
        var sensors = allSensors.joinToString("\n\n") { "${it.name} - ${it.vendor} - ${it.type}\n" }
        binding.sensorInfo.text = sensors
    }
}