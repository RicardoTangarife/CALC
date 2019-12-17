package com.ricardotangarife.calc

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import com.udojava.evalex.*
import kotlinx.android.synthetic.main.activity_main.*
import java.math.BigDecimal

class MainActivity : AppCompatActivity() {

    private lateinit var result : BigDecimal

    var lastNum: Boolean = false
    var error: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        textView.text = ""
    }

    fun onDigit(view: View) {
        if (!error) {
            textView.text = textView.text.toString() + (view as Button).text.toString()
        }
        else{
            textView.text = (view as Button).text.toString()
        }
        lastNum = true
    }

    fun onOper(view: View) {
        if (lastNum && !error) {
            textView.text = textView.text.toString() + (view as Button).text.toString()
            lastNum= false
        }
    }

    fun onBorr(view: View) {
        textView.text  = ""
        lastNum = false
        error = false
    }

    fun onIgual(view: View) {
        if (lastNum && !error) {
            val formula = textView.text.toString()
            try {
                val result = Expression(formula).eval()
                textView.text = result.toPlainString()
            } catch (ex: ArithmeticException) {
                textView.text = "Error!!!!"
                error = true
                lastNum = false
            }
        }
    }

}
