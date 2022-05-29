package com.example.rccarcontroller

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MotionEvent
import android.widget.ImageButton
import android.widget.TextView
import okhttp3.*
import java.io.IOException

class MainActivity : AppCompatActivity() {
    private val client = OkHttpClient()
    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportActionBar?.hide()

        val fwBtn = findViewById<ImageButton>(R.id.fwBtn)
        val bkBtn = findViewById<ImageButton>(R.id.bkBtn)
        val rgBtn = findViewById<ImageButton>(R.id.rgBtn)
        val lfBtn = findViewById<ImageButton>(R.id.lfBtn)
        val sldSet = findViewById<com.google.android.material.slider.Slider>(R.id.valSetter)
        val liveSpeed = findViewById<TextView>(R.id.liveSpeed)
        var speed : Int = 20

        fun run(url: String) {
            val request = Request.Builder()
                .url(url)
                .build()
            client.newCall(request).enqueue(object : Callback {
                override fun onFailure(call: Call, e: IOException) {}
                override fun onResponse(call: Call, response: Response) = println(response.body()?.string())
            })
        }

            sldSet.addOnChangeListener { slider, value, fromUser ->
                speed = value.toInt()
                liveSpeed.text = speed.toString()
            }


            fwBtn.setOnTouchListener { v, event ->
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    println("varianta a doua" +speed)
                    run("http://192.168.4.1:5000/l?speed="+speed)
                    true
                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    run("http://192.168.4.1:5000/s")
                    true
                }
                false
            }

            bkBtn.setOnTouchListener { v, event ->
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    run("http://192.168.4.1:5000/r?speed="+speed)
                    true
                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    run("http://192.168.4.1:5000/s")
                    true
                }
                false
            }

            rgBtn.setOnTouchListener { v, event ->
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    run("http://192.168.4.1:5000/f?speed="+speed)
                    true
                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    run("http://192.168.4.1:5000/s")
                    true
                }
                false
            }

            lfBtn.setOnTouchListener { v, event ->
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    run("http://192.168.4.1:5000/b?speed="+speed)
                    true
                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    run("http://192.168.4.1:5000/s")
                    true
                }
                false
            }
        }
    }
//}


