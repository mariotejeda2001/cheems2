package com.example.cheems2

import android.content.Intent
import android.content.res.Resources
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity(), View.OnClickListener {

    var GameOver = 0
    var gameWin = 0
    var activeGame = true
    var counterWin = 0

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
        val save = findViewById<View>(R.id.btn_save) as Button
        save.setOnClickListener(this)
        findViewById<Button>(R.id.btn_save).visibility = View.GONE
    }
    fun start (){
        for (i in 1..9){
            val btnCard = findViewById<View>(resources.getIdentifier("btn_image$i","id",this.packageName)) as ImageButton
            btnCard.setOnClickListener(this)
            btnCard.setBackgroundResource(R.drawable.cheems_question)
        }
        GameOver = (1..9).random()
        gameWin = (1..9).random()
        if (GameOver == gameWin){
            gameWin = (1..9).random()
        }
        activeGame = true
        counterWin = 0
        findViewById<Button>(R.id.btn_save).visibility = View.GONE
        Log.d("valor de la carta", "start: esta es la carta mala ${GameOver}")
        Log.d("valor de la carta", "esta es la carta buena ${gameWin}")
    }


    fun flip (card: Int){
        if (!activeGame) return
        if (card == GameOver){
            Toast.makeText(this, "maldito perdedor", Toast.LENGTH_LONG).show()
            activeGame = false
            for (i in 1..9){
                val btnCard = findViewById<View>(resources.getIdentifier("btn_image$i", "id", this.packageName)) as ImageButton
                if (i == card){
                    btnCard.setBackgroundResource(R.drawable.cheems_bad)
                }else if (i == gameWin){
                    btnCard.setBackgroundResource(R.drawable.cheems_super)
                }else{
                    btnCard.setBackgroundResource(R.drawable.cheems_ok)
                }
            }
        }else if (card == gameWin){
            Toast.makeText(this, "sptm ganaste...", Toast.LENGTH_SHORT).show()
            findViewById<View>(R.id.btn_save).visibility = View.VISIBLE
            activeGame = false
            for (i in 1..9){
                val btnCard = findViewById<View>(resources.getIdentifier("btn_image$i", "id", this.packageName)) as ImageButton
                if (i == card){
                    btnCard.setBackgroundResource(R.drawable.cheems_super)
                }else if (i == GameOver){
                    btnCard.setBackgroundResource(R.drawable.cheems_bad)
                }else{
                    btnCard.setBackgroundResource(R.drawable.cheems_ok)
                }
            }
            }else{
            var btnCard = findViewById<View>(resources.getIdentifier("btn_image$card", "id", this.packageName)) as ImageButton
            btnCard.setBackgroundResource(R.drawable.cheems_ok)
            counterWin ++
            if (counterWin == 7){
                Toast.makeText(this, "you win", Toast.LENGTH_SHORT).show()
                findViewById<View>(R.id.btn_save).visibility = View.VISIBLE

            }
        }
        }

    override fun onClick(v: View) {
        when(v.id){
            R.id.btn_image1 -> {flip(1)}
            R.id.btn_image2 -> {flip(2)}
            R.id.btn_image3 -> {flip(3)}
            R.id.btn_image4 -> {flip(4)}
            R.id.btn_image5 -> {flip(5)}
            R.id.btn_image6 -> {flip(6)}
            R.id.btn_image7 -> {flip(7)}
            R.id.btn_image8 -> {flip(8)}
            R.id.btn_image9 -> {flip(9)}
            R.id.btn_update -> {start()}
            R.id.btn_save -> {
                val intentWinnerForm = Intent(this, WinnerFormActivity::class.java)
                startActivity(intentWinnerForm)
            }
        }
    }
}