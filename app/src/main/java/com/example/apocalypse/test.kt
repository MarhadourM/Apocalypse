package com.example.apocalypse

import android.os.Bundle
import android.view.ViewGroup
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import eightbitlab.com.blurview.BlurView
import eightbitlab.com.blurview.RenderScriptBlur
import androidx.palette.graphics.Palette
import android.graphics.drawable.BitmapDrawable
import android.graphics.Color
import android.widget.ImageView
import androidx.core.graphics.ColorUtils


class test : BaseActivity() {
    lateinit var buttonSurvivre : Button
    lateinit var backgroundView : ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_test)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        val controller = WindowInsetsControllerCompat(window, window.decorView)
        controller.hide(WindowInsetsCompat.Type.systemBars())
        controller.systemBarsBehavior =
            WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE

        buttonSurvivre = findViewById(R.id.buttonSurvivre2)
        buttonSurvivre.setOnClickListener {
            createIntent(this, IntroGameView::class.java)
        }
    val glassBox = findViewById<BlurView>(R.id.glassBox)
    backgroundView = findViewById(R.id.bgImage)

    setupDynamicGlassmorphism(this,glassBox,backgroundView, 5f,153)

    colorizeButtonFromBackground(buttonSurvivre,backgroundView)


    }





}