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
        var direction: String

        fun run(url: String) {
            val request = Request.Builder()
                .url(url)
                .build()
            client.newCall(request).enqueue(object : Callback {
                override fun onFailure(call: Call, e: IOException) {}
                override fun onResponse(call: Call, response: Response) = println(response.body()?.string())
            })
        }

        /*sldSet.addOnChangeListener { slider, value, fromUser ->
            val values = sldSet.value
            liveSpeed.text = "${values}"*/

            sldSet.addOnChangeListener { slider, value, fromUser ->
                speed = value.toInt()
                liveSpeed.text = speed.toString()
            }


            fwBtn.setOnTouchListener { v, event ->
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    //Toast.makeText(this@MainActivity, "I pressed up", Toast.LENGTH_SHORT).show()
                    direction = "front"
                    println(direction)
                    //println(values)
                    println("varianta a doua" +speed)
                    run("http://192.168.4.1:5000/f?speed="+speed)
                    true
                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    //Toast.makeText(this@MainActivity, "I depressed up", Toast.LENGTH_SHORT).show()
                    run("http://192.168.4.1:5000/s")
                    direction = "stop"
                    println(direction)
                    true
                }
                false
            }

            bkBtn.setOnTouchListener { v, event ->
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    //Toast.makeText(this@MainActivity, "I pressed up", Toast.LENGTH_SHORT).show()
                    direction = "back"
                    println(direction)
                    //println(values)
                    println("varianta a doua "+speed)
                    run("http://192.168.4.1:5000/b?speed="+speed)
                    true
                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    //Toast.makeText(this@MainActivity, "I depressed up", Toast.LENGTH_SHORT).show()
                    direction = "stop"
                    println(direction)
                    run("http://192.168.4.1:5000/s")
                }
                false
            }

            rgBtn.setOnTouchListener { v, event ->
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    //Toast.makeText(this@MainActivity, "I pressed up", Toast.LENGTH_SHORT).show()
                    direction = "right"
                    println(direction)
                    //println(values)
                    run("http://192.168.4.1:5000/r?speed="+speed)
                    println("varianta a doua" +speed)
                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    //Toast.makeText(this@MainActivity, "I depressed up", Toast.LENGTH_SHORT).show()
                    direction = "stop"
                    println(direction)
                    run("http://192.168.4.1:5000/s")
                }
                false
            }

            lfBtn.setOnTouchListener { v, event ->
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    //Toast.makeText(this@MainActivity, "I pressed up", Toast.LENGTH_SHORT).show()
                    direction = "left"
                    println(direction)
                    //println(values)
                    run("http://192.168.4.1:5000/l?speed="+speed)
                    println("varianta a doua" +speed)
                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    //Toast.makeText(this@MainActivity, "I depressed up", Toast.LENGTH_SHORT).show()
                    direction = "stop"
                    println(direction)
                    run("http://192.168.4.1:5000/s")
                }
                false
            }
        }
    }
//}


