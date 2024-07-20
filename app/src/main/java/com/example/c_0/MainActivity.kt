package com.example.c_0

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        /*ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }*/
    }

    private lateinit var textView_r: TextView

    private lateinit var textView_b: Button

    fun retext_view(v: View){

        textView_r = findViewById(R.id.result)
        var textView_r_text = textView_r.text.toString()
        textView_b = findViewById(v.id)
        var textView_b_text = textView_b.text.toString()

        if (textView_r_text.isEmpty()){
            if (textView_b_text in "1234567890") {
                textView_r_text = textView_r_text + textView_b_text
                textView_r.text = textView_r_text
            }
            else{
                textView_r.text = ""
            }
        }
        else{
            if ((textView_r_text.endsWith('+') or textView_r_text.endsWith('-') or textView_r_text.endsWith('/') or textView_r_text.endsWith('*') or textView_r_text.endsWith('%') or textView_r_text.endsWith(',')) && (textView_b_text in ",+-/*%")){
                textView_r_text = textView_r_text.subSequence(0,textView_r_text.length-1).toString()
            }
            textView_r_text = textView_r_text + textView_b_text
//                Toast.makeText(this,textView_r_text, Toast.LENGTH_SHORT).show()
            textView_r.text = textView_r_text



        }


    }

    fun del_all_text(v: View) {
        textView_r = findViewById(R.id.result)
        val text_res = ""
        textView_r.text = text_res

    }

    fun del_text(v: View) {
        textView_r = findViewById(R.id.result)
        var textView_r_text = textView_r.text.toString()
        if (!textView_r_text.isEmpty()){
            textView_r_text = textView_r_text.subSequence(0,textView_r_text.length-1).toString()
            textView_r.text = textView_r_text
        }

//        fun onDestroy() {
//            super.onDestroy()
//
//        }
    }


}