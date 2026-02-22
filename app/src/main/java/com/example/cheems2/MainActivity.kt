package com.example.cheems2

import android.content.res.Resources
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity(), View.OnClickListener {

    var GameOver = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        start()
        val update = findViewById<View>(R.id.btn_update) as Button
        update.setOnClickListener(this)
    }
    fun start (){
        for (i in 1..9){
            val imageButton = findViewById<View>(resources.getIdentifier("btn_image$i","id",this.packageName)) as ImageButton
            imageButton.setOnClickListener(this)
            imageButton.setBackgroundResource(R.drawable.cheems_question)
        }
        GameOver = (1..9).random()
    }



    override fun onClick(v: View?) {
        TODO("Not yet implemented")
    }
}