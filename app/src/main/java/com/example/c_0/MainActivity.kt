package com.example.c_0

import android.animation.Animator
import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import android.view.ViewPropertyAnimator;
import android.view.animation.AccelerateDecelerateInterpolator
import android.widget.Toast
import androidx.core.view.WindowInsetsCompat
import android.content.SharedPreferences
import android.content.Context

class MainActivity : AppCompatActivity() {
    private lateinit var sharedpreferences: SharedPreferences
    private var KEY_RESULT_IN = "KEY_RESULT_IN"
    private var KEY_RESULT_OUT = "KEY_RESULT_OUT"
    private val SHARED_PREFS = "shared_prefs"

    private lateinit var textView_r: TextView
    private lateinit var textView_r_out: TextView



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        sharedpreferences = getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE)
        val editor = sharedpreferences.edit()
        editor.putString(KEY_RESULT_IN, "")
        editor.putString(KEY_RESULT_OUT, "")
        editor.apply()
        /*ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }*/
    }

    private lateinit var textView_b: Button

    fun retext_view(v: View){
        val editor = sharedpreferences.edit()

        var result_in = sharedpreferences.getString(KEY_RESULT_IN, "").toString()
        var result_out = sharedpreferences.getString(KEY_RESULT_OUT, "").toString()

        textView_r = findViewById(R.id.result)







        var textView_r_text = textView_r.text.toString()
        textView_b = findViewById(v.id)
        var textView_b_text = textView_b.text.toString()
        textView_r_out = findViewById(R.id.result_output)
        if (result_in.isEmpty()){
            if (textView_b_text in "1234567890423") {
                result_in += textView_b_text
                editor.putString(KEY_RESULT_IN, result_in)
                editor.putString(KEY_RESULT_OUT, result_in)
                editor.apply()
            }
            else{
                textView_r.text = ""
            }
        }
        else{

            if ((result_in.endsWith('+')
                        or result_in.endsWith('-')
                        or result_in.endsWith('/')
                        or result_in.endsWith('*')
                        or result_in.endsWith('%')
                        or result_in.endsWith(','))
                        && (textView_b_text in ",+-/*%"))
            {
                result_in = result_in.subSequence(0,result_in.length-1).toString()
            }
            result_in += textView_b_text
            editor.putString(KEY_RESULT_IN, result_in)
            editor.putString(KEY_RESULT_OUT, result_in)
            editor.apply()
            if (result_in.length >11){
                textView_r.textSize = 30F
                textView_r_out.textSize = 25F
                textView_r.setPadding(15,33,15,50)
                textView_r_out.setPadding(15,20,15,50)

                if(result_in.length >20){
                    result_in = result_in.subSequence(result_in.length-20,result_in.length).toString()
                    Toast.makeText(this,"klsn", Toast.LENGTH_SHORT).show()
                }


            }
            else{
                textView_r.textSize = 50F
                textView_r_out.textSize = 40F
                textView_r.setPadding(15,0,15,20)
                textView_r_out.setPadding(15,0,15,40)




            }
            //Toast.makeText(this,result_in, Toast.LENGTH_SHORT).show()




        }
        textView_r.text = result_in
        textView_r_out.text = result_in

    }

    fun del_all_text(v: View) {
        textView_r = findViewById(R.id.result)
        textView_r_out = findViewById(R.id.result_output)
        val text_res = ""
        textView_r.text = text_res
        textView_r_out.text = text_res
        val editor = sharedpreferences.edit()
        editor.putString(KEY_RESULT_IN, text_res)
        editor.putString(KEY_RESULT_OUT, text_res)
        editor.apply()
        textView_r.textSize = 50F
        textView_r_out.textSize = 40F
        textView_r.setPadding(15,0,15,20)
        textView_r_out.setPadding(15,0,15,40)

    }
//    override fun onAnimationEnd(animation: Animator) {
//        Toast.makeText(this,"klsn", Toast.LENGTH_SHORT).show()
//    }

    fun fun_equally(view: View) {
        textView_r_out = findViewById(R.id.result_output)
        textView_r = findViewById(R.id.result)

//        val scaleXAnimator = ObjectAnimator.ofFloat(textView_r_out, "translationY", 0.5f)
//        val scaleYAnimator = ObjectAnimator.ofFloat(textView_r, "translationY", 0.5f)
//
//        val animatorSet = AnimatorSet()
//        animatorSet.playTogether(scaleXAnimator, scaleYAnimator)
//        animatorSet.duration = 1000
//        animatorSet.start()

//        val animator = ObjectAnimator.ofFloat(textView_r_out, "translationX", 0f, 200f)
////        animator.addListener(object : Animator.AnimatorListener {
////            override fun onAnimationStart(animation: Animator?) {
////                // Animation started
////            }
////
////            override fun onAnimationEnd(animation: Animator?) {
////                // Animation ended
////            }
////        })
////        animator.duration = 1000

//        ViewCompat.animate(textView_r_out)
//            .translationX(50f)
//            .translationY(100f)
//            .setDuration(1000)
//            .setInterpolator(AccelerateDecelerateInterpolator())
//            .setStartDelay(50)
//            .setListener(object : Animator.AnimatorListener {
//                override fun onAnimationRepeat(animation: Animator) {}
//                override fun onAnimationEnd(animation: Animator) {}
//                override fun onAnimationCancel(animation: Animator) {}
//                override fun onAnimationStart(animation: Animator) {}
//            })
//        animator.start()


        var text_res = ""
        textView_r.text = textView_r_out.text.toString()
        textView_r_out.text = text_res
    }

    fun del_text(v: View) {
        val editor = sharedpreferences.edit()
        var result_in = sharedpreferences.getString(KEY_RESULT_IN, "").toString()
        var result_out = sharedpreferences.getString(KEY_RESULT_OUT, "").toString()
        textView_r = findViewById(R.id.result)
        if (result_in.isNotEmpty()){
            result_in = result_in.subSequence(0,result_in.length-1).toString()
            editor.putString(KEY_RESULT_IN, result_in)
            editor.putString(KEY_RESULT_OUT, result_in)
            editor.apply()
            if (result_in.length >11){
                textView_r.textSize = 30F
                textView_r_out.textSize = 25F
                textView_r.setPadding(15,33,15,50)
                textView_r_out.setPadding(15,20,15,50)

                if(result_in.length >20){
                    result_in = result_in.subSequence(result_in.length-20,result_in.length).toString()
                    Toast.makeText(this,"klsn", Toast.LENGTH_SHORT).show()
                }
            }
            else{
                textView_r.textSize = 50F
                textView_r_out.textSize = 40F
                textView_r.setPadding(15,0,15,20)
                textView_r_out.setPadding(15,0,15,40)
            }


            textView_r.text = result_in
            textView_r_out.text = result_in
        }




    }

//    fun onDestroy() {
//        super.onDestroy()
//
//    }



}