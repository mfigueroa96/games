package com.example.games

import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast


class MainActivity : AppCompatActivity() {
    var player = 1
    var p1 = ArrayList<Int>()
    var p2 = ArrayList<Int>()
    var autoplayer = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        reset()
    }

    fun select(view : View) {
        val selectedBtn = view as Button
        var buttonCode = 0
        when (selectedBtn.id) {
            R.id.button1 -> buttonCode = 1
            R.id.button2 -> buttonCode = 2
            R.id.button3 -> buttonCode = 3
            R.id.button4 -> buttonCode = 4
            R.id.button5 -> buttonCode = 5
            R.id.button6 -> buttonCode = 6
            R.id.button7 -> buttonCode = 7
            R.id.button8 -> buttonCode = 8
            R.id.button9 -> buttonCode = 9
        }

        if (!autoplayer) {
            gameOn(buttonCode, selectedBtn)
        }
        else {
            gameOnWithAutoplayer(buttonCode, selectedBtn)
        }

        andTheWinnerIs()
    }

    fun resetBtnOnClick (view : View) {
        reset()
    }

    fun reset() {
        p1.clear()
        p2.clear()
        for (i in 1..9) {
            var btn = findViewById(resources.getIdentifier("button$i", "id", applicationContext.packageName)) as Button
            btn.text = ""
            btn.setBackgroundColor(Color.GRAY)
            btn.isEnabled = true
        }
    }

    fun autoplayerBtnOnClick (view : View) {
        autoplayer = !autoplayer
        if (autoplayer) view.setBackgroundResource(R.color.green)
        else view.setBackgroundResource(R.color.blue)
        reset()
    }

    fun gameOn(buttonCode : Int, selectedButton : Button) {
        if (player == 1) {
            selectedButton.text = "X"
            selectedButton.setBackgroundResource(R.color.blue)
            p1.add(buttonCode)
            player = 2
        }
        else if (player == 2) {
            selectedButton.text = "O"
            selectedButton.setBackgroundResource(R.color.green)
            p2.add(buttonCode)
            player = 1
        }

        selectedButton.isEnabled = false
    }

    fun gameOnWithAutoplayer(buttonCode : Int, selectedButton : Button) {
        selectedButton.text = "X"
        selectedButton.setBackgroundResource(R.color.blue)
        p1.add(buttonCode)
        selectedButton.isEnabled = false

        var random = 0
        do {
            random = (1..9).random()
        } while (p1.contains(random) || p2.contains(random))

        var button : Button =
            findViewById(resources.getIdentifier("button$random", "id", applicationContext.packageName)) as Button

        button.text = "O"
        button.setBackgroundResource(R.color.green)
        button.isEnabled = false
        p2.add(random)
    }
    
    fun andTheWinnerIs() {
        var win = 0
        if (p1.contains(1) && p1.contains(2) && p1.contains(3)
            || p1.contains(4) && p1.contains(5) && p1.contains(6)
            || p1.contains(7) && p1.contains(8) && p1.contains(9)
            || p1.contains(1) && p1.contains(4) && p1.contains(7)
            || p1.contains(2) && p1.contains(5) && p1.contains(8)
            || p1.contains(3) && p1.contains(6) && p1.contains(9)
            || p1.contains(1) && p1.contains(5) && p1.contains(9)
            || p1.contains(3) && p1.contains(5) && p1.contains(7)) {
                win = 1;
        }

        if (p2.contains(1) && p2.contains(2) && p2.contains(3)
            || p2.contains(4) && p2.contains(5) && p2.contains(6)
            || p2.contains(7) && p2.contains(8) && p2.contains(9)
            || p2.contains(1) && p2.contains(4) && p2.contains(7)
            || p2.contains(2) && p2.contains(5) && p2.contains(8)
            || p2.contains(3) && p2.contains(6) && p2.contains(9)
            || p2.contains(1) && p2.contains(5) && p2.contains(9)
            || p2.contains(3) && p2.contains(5) && p2.contains(7)) {
                win = 2;
        }
        
        if (win != 0) {
            Toast.makeText(this, "AND the winner IS PLAYER $win", Toast.LENGTH_LONG).show()
            
        }
    }

    fun findWinner(array: ArrayList<Int>) {

    }
}
