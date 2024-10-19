package com.example.contextmenu

import android.graphics.Color
import android.os.Bundle
import android.view.ContextMenu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import kotlin.random.Random


class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var textET: EditText
    private lateinit var buttonRandom: Button
    private var workButton: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        textET = findViewById(R.id.textET)
        registerForContextMenu(textET)

        buttonRandom = findViewById(R.id.randomButton)

    }

    override fun onCreateContextMenu(
        menu: ContextMenu?,
        v: View?,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        super.onCreateContextMenu(menu, v, menuInfo)
        menuInflater.inflate(R.menu.context_menu ,menu)
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        var backgroundTextBox = ""
        textET.setTextColor(Color.BLACK)

        when(item.itemId){
            R.id.menu_color_quality -> {
                if(!workButton) {
                    backgroundTextBox = when (textET.text.toString()) {
                        "1" -> "#FF7F50"  //Оранжевый
                        "2" -> "#DFFF00"  //Жёлтый
                        "3" -> "#00FF00"  //Зелёный
                        "4" -> "#0000FF"  //Синий
                        "5" -> "#FF0000"  //Красный
                        else -> "#999999" //Серый
                    }
                }else{
                    val num: Int = Integer.valueOf(textET.text.toString())
                    backgroundTextBox = when(num){
                        in 1..10 -> "#FF0000"  //Красный
                        in 11..20 -> "#FF7F50" //Оранжевый
                        in 21..30 -> "#DFFF00" //Жёлтый
                        in 31..40 -> "#00FF00" //Зелёный
                        in 41..50 -> "#0000FF" //Синий
                        else -> "#999999"
                    }
                    workButton = false
                 }
//                textET.setBackgroundColor(backgroundTextBox.toInt())
                textET.setBackgroundColor(Color.parseColor(backgroundTextBox))
            }
            R.id.menu_exit -> finish()
            else -> return  super.onContextItemSelected(item)
        }

        return true
    }

    override fun onClick(v: View){
        val randomNum = (1..50).random().toString()
        textET.setText(randomNum)
        workButton = true
    }

}
