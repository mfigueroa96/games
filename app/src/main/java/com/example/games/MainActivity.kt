package com.example.games

import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast


class MainActivity : AppCompatActivity() {
    var player = 1
    var p1 = ArrayList<Int>()
    var p2 = ArrayList<Int>()
    var autoplayer = false
    var scorep1 = 0
    var scorep2 = 0
    var scoreA2 = 0
    var scoreA1 = 0
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

    fun isWinner(plays: ArrayList<Int>): Boolean{
        for (i in 1..7 step 3)
            if(plays.contains(i) && plays.contains(i+1) && plays.contains(i + 2))
                return true
        for(i in 1..3)
            if(plays.contains(i) && plays.contains(i+3) && plays.contains(i + 6))
                return true
        if(plays.contains(1) && plays.contains(5) && plays.contains(9) || plays.contains(3) && plays.contains(5) && plays.contains(7))
            return true
        return false
    }
    
    fun andTheWinnerIs() {
        var win = 0
        if(isWinner(p1))
            win = 1
        if(isWinner(p2))
            win = 2

        if (win != 0) {
            Toast.makeText(this, "AND the winner IS PLAYER $win", Toast.LENGTH_LONG).show()
            turnOffButtons()
            if(win == 1){
                scorep1++
            }else
                scorep2++
            updateScore()
        }

    }

    private fun updateScore() {
        var textViewP1: TextView= findViewById(R.id.scoreP1) as TextView
        var textViewP2: TextView= findViewById(R.id.scoreP2) as TextView
        textViewP1.setText("Player 1: $scorep1")
        textViewP2.setText("Player 2: $scorep2")
    }

    private fun turnOffButtons() {
        for (i in 1..9) {
            var btn = findViewById(resources.getIdentifier("button$i", "id", applicationContext.packageName)) as Button
            btn.isEnabled = false
            if(!p1.contains(i) && !p2.contains(i))
                btn.setBackgroundColor(Color.RED)
        }
    }
}
