package com.vinted.preferx.examples

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rx_example.setOnClickListener {
            startActivity(Intent(this, RxActivityExample::class.java))
        }

        dagger_example.setOnClickListener {
            startActivity(Intent(this, DaggerActivityExample::class.java))
        }

        object_example.setOnClickListener {
            startActivity(Intent(this, ObjectPreferenceExample::class.java))
        }
    }
}
