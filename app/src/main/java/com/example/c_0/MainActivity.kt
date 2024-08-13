package com.example.c_0

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.content.SharedPreferences
import android.content.Context
import android.util.Log

class MainActivity : AppCompatActivity() {
    private lateinit var sharedpreferences: SharedPreferences
    private val KEY_RESULT_IN = "KEY_RESULT_IN"
    private val KEY_RESULT_OUT = "KEY_RESULT_OUT"
    private val SHARED_PREFS = "shared_prefs"

    private lateinit var textCalculateAndResault: TextView
    private lateinit var textResaultNow: TextView



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        sharedpreferences = getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE)
        val editor = sharedpreferences.edit()
        editor.putString(KEY_RESULT_IN, "")
        editor.putString(KEY_RESULT_OUT, "")
        editor.apply()
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

    }

    private lateinit var textView_b: Button

    fun retext_view(v: View){
        val editor = sharedpreferences.edit()

        var result_in = sharedpreferences.getString(KEY_RESULT_IN, "").toString()
        var result_out = sharedpreferences.getString(KEY_RESULT_OUT, "").toString()

        textCalculateAndResault = findViewById(R.id.result)

        var textView_r_text = textCalculateAndResault.text.toString()
        textView_b = findViewById(v.id)
        var textView_b_text = textView_b.text.toString()
        textResaultNow = findViewById(R.id.result_output)
        if (result_in.isEmpty()){
            if (textView_b_text in getString(R.string.all_numbers)) {
                result_in += textView_b_text
                editor.putString(KEY_RESULT_IN, result_in)
                editor.putString(KEY_RESULT_OUT, result_in)
                editor.apply()
            }
            else if(textView_b_text == ".") {
                result_in += getString(R.string.zero)+textView_b_text
                editor.putString(KEY_RESULT_IN, result_in)
                editor.putString(KEY_RESULT_OUT, result_in)
                editor.apply()
            }
            else{
                textCalculateAndResault.text = ""
            }
        }
        else{

            if (textView_b_text == "."){
                if (('+' in result_in) or
                    ('-' in result_in) or
                    ('%' in result_in) or
                    ('*' in result_in)or
                    ('/' in result_in)){
                    var i = result_in.length-1
                    var flag = true
                    while(flag){
                        if (result_in[i] in "+-/*%"){
                            flag = false
                            if ('.' in result_in.substring(i+1,result_in.length)){
                                textView_b_text = ""
                            }
                        }
                        i -= 1
                    }
                }
                else{
                    if ('.' in result_in){
                        textView_b_text = ""
                    }
                }
            }


            if ((result_in.endsWith('+')
                        or result_in.endsWith('-')
                        or result_in.endsWith('/')
                        or result_in.endsWith('*')
                        or result_in.endsWith('%')
                        or result_in.endsWith('.'))
                        && (textView_b_text in ".+-/*%"))
            {
                result_in = result_in.subSequence(0,result_in.length-1).toString()
            }
            result_in += textView_b_text
            editor.putString(KEY_RESULT_IN, result_in)
            editor.putString(KEY_RESULT_OUT, result_in)
            editor.apply()

            if (result_in.length >20){
                result_in = result_in.subSequence(result_in.length-20,result_in.length).toString()
            }
            //Toast.makeText(this,result_in, Toast.LENGTH_SHORT).show()




        }
        textCalculateAndResault.text = result_in
        textResaultNow.text = CalculatingTheResult()

    }

    fun del_all_text(v: View) {
        textCalculateAndResault = findViewById(R.id.result)
        textResaultNow = findViewById(R.id.result_output)
        val text_res = ""
        textCalculateAndResault.text = text_res
        textResaultNow.text = text_res
        val editor = sharedpreferences.edit()
        editor.putString(KEY_RESULT_IN, text_res)
        editor.putString(KEY_RESULT_OUT, text_res)
        editor.apply()

    }
//    override fun onAnimationEnd(animation: Animator) {
//        Toast.makeText(this,"klsn", Toast.LENGTH_SHORT).show()
//    }

    fun fun_equally(view: View) {
        textResaultNow = findViewById(R.id.result_output)
        textCalculateAndResault = findViewById(R.id.result)
        val result = CalculatingTheResult()
        val editor = sharedpreferences.edit()
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
        textCalculateAndResault.text = result
        editor.putString(KEY_RESULT_IN, result)
        editor.apply()
        textResaultNow.text = text_res
    }

    private fun CalculatingTheResult(): String {
        var result: String
        var resultDouble = 0.0
        var result_in = sharedpreferences.getString(KEY_RESULT_IN, "").toString()
        if (result_in.isNotEmpty() ){
            if (result_in.last() in "+-*/%"){
                result_in = result_in.substring(0,result_in.length-1)
            }
        }
        var pos = 0
        var pos1 = 0
        var pos2 = 0
        var k = 0.0
        var k2 = 0.0
        var index1: Int
        var index2: Int
        var index3: Int
        var FlagPos1: Boolean
        var FlagPos2: Boolean
        if(('*' in result_in) or ('/' in result_in) or ('%' in result_in)){
            var count_oper = result_in.count{ (it == '/') or (it == '*') or (it == '%') }
//            Log.d("count_oper", count_oper.toString())
            for (i in 0..count_oper-1){
                pos = 0
                k = 0.0
                index1 = result_in.indexOf("*")
                index2 = result_in.indexOf("/")
                index3 = result_in.indexOf("%")
                val List_index = ArrayList<Int>()
                if (index1>-1){
                    List_index.add(index1)
                }
                if (index2>-1){
                    List_index.add(index2)
                }
                if (index3>-1){
                    List_index.add(index3)
                }
                var MinIndex = List_index.min()
                if (MinIndex == index1){
                    pos = index1
                }
                else if (MinIndex == index2){
                    pos = index2
                }
                else{
                    pos = index3
                }

                FlagPos1 = true
                FlagPos2 = true
//                Log.e("pos", pos.toString())
                var j = 1
                while(FlagPos1 or FlagPos2){
                    if (FlagPos1 && ((pos-j) == 0)){
                        k = result_in.substring(0,pos).toDouble()
                        FlagPos1 = false
                        pos1 = 0
                    }
                    if (FlagPos1 && (result_in[pos-j] in "+-*/%")){
                        k = result_in.substring(pos-j+1,pos).toDouble()
                        FlagPos1 = false
                        pos1 = pos-j+1
                    }

//                    Log.d("leingh", result_in.substring(pos+1,pos2))
//                    Log.d("leingh12", FlagPos2.toString())
//                    Log.d("leingh34", (pos2 == result_in.length).toString())

                    if (FlagPos2 && (pos+j == result_in.length)){
                        k2 = result_in.substring(pos+1,result_in.length).toDouble()
                        FlagPos2 = false
                        pos2 = pos+j
                    }
                    if (FlagPos2 && (result_in[pos+j] in "+-*/%")){
                        k2 = result_in.substring(pos+1,pos+j).toDouble()
                        FlagPos2 = false
                        pos2 = pos+j
                    }
                    j+=1
                }
                if (result_in[pos] == '*'){
                    resultDouble = k*k2
                }
                else if (result_in[pos] == '%'){
                    resultDouble = k/100.0*k2
                }
                else{
                    if (k2 != 0.0){
                        resultDouble = k/k2
                    }
                    else{
                        return getString(R.string.DivisionBy0)
                    }
                }
//                if (result_in[pos1+1] in "+-*/"){
//                    pos1 +=1
//                }
//                Log.e("123",result_in.substring(0,pos1) )
//                Log.e("1233",result_in.substring(pos2))
                if (Math.round(resultDouble)/1.0 == resultDouble){
                    result_in = result_in.substring(0,pos1)+Math.round(resultDouble).toString()+result_in.substring(pos2)

                }
                else {
                    result_in = result_in.substring(0,pos1)+resultDouble.toString()+result_in.substring(pos2)

                }

//                Log.e("1236",result_in )
            }
        }
            pos = 0
            k = 0.0
            index1 = result_in.indexOf("+")
            index2 = result_in.indexOf("-")
            if((index1 == -1) && (index2 == -1)){
                return result_in
            }
            else{
                if(index1*index2>0){
                    if (index1 <= index2){
                        pos = index1
                    }
                    else{
                        pos = index2
                    }
                }
                else{
                    if (index1 != -1){
                        pos = index1
                    }
                    else {
                        pos = index2
                    }
                }
            }
            resultDouble = result_in.substring(0,pos).toDouble()
//            Log.d("mypos", result_in[pos].toString())
            var i = pos+1  ///3+ 3* 3
            while(i < result_in.length){
//                Log.e("my2", result_in[i].toString())
//                Log.e("my_i", i.toString())
//                Log.e("mylength", result_in.length.toString())
                if ((result_in[i] in "+-") or (i == result_in.length-1)){
                    if (i == result_in.length-1){
                        i +=1
                    }
                    k = result_in.substring(pos+1,i).toDouble()
                    Log.e("myk", k.toString())
                    if (result_in[pos] == '+'){
                        resultDouble +=k
                        pos = i
                    }
                    else{
                        resultDouble -= k
                        pos = i
                    }
                }
                i +=1
            }
//        Log.d("my", Math.round(resultDouble).toString())
        if (Math.round(resultDouble)/1.0 == resultDouble){
            result = Math.round(resultDouble).toString()
        }
        else {
            result = resultDouble.toString()
        }
//        Log.d("myTag", result)
        return result;
    }
    fun del_text(v: View) {
        val editor = sharedpreferences.edit()
        var result_in = sharedpreferences.getString(KEY_RESULT_IN, "").toString()
        var result_out = sharedpreferences.getString(KEY_RESULT_OUT, "").toString()
        textCalculateAndResault = findViewById(R.id.result)
        if (result_in.isNotEmpty()){
            result_in = result_in.subSequence(0,result_in.length-1).toString()
            editor.putString(KEY_RESULT_IN, result_in)
            editor.putString(KEY_RESULT_OUT, result_in)
            editor.apply()
            if (result_in.length >20){
                result_in = result_in.subSequence(result_in.length-20,result_in.length).toString()
            }

            textCalculateAndResault.text = result_in
            textResaultNow.text = CalculatingTheResult()

        }




    }

//    fun onDestroy() {
//        super.onDestroy()
//
//    }



}