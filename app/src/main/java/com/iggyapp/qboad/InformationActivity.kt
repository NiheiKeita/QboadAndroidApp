package com.iggyapp.qboad

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView

class InformationActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_information)
        findViewById<ImageView>(R.id.informarion_header_my_image).setImageResource(R.drawable.sample_nihei_icon)
        findViewById<TextView>(R.id.information_footer_button).setBackgroundColor(Color.parseColor("#808080"))
        findViewById<TextView>(R.id.information_footer_button).setTextColor(Color.WHITE)
    }
}