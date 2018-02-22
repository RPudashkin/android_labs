package com.example.unnamed.lab2

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.Snackbar
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    private var m_arg1: Double? = null
    private var m_arg2: Double? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val lineRes = this.lineResult

        this.btnPlus.setOnClickListener {
            if (readArgs()) {
                var result = m_arg1!!.toDouble() + m_arg2!!.toDouble()
                lineRes.text = "= " + result.toString()
            }
            else lineRes.text = ""
        }

        this.btnSub.setOnClickListener {
            if (readArgs()) {
                var result = m_arg1!!.toDouble() - m_arg2!!.toDouble()
                lineRes.text = "= " + result.toString()
            }
            else lineRes.text = ""
        }

        this.btnMult.setOnClickListener {
            if (readArgs()) {
                var result = m_arg1!!.toDouble() * m_arg2!!.toDouble()
                lineRes.text = "= " + result.toString()
            }
            else lineRes.text = ""
        }

        this.btnDiv.setOnClickListener {
            if (readArgs()) {
                if (m_arg2!!.toDouble() == 0.0) {
                    Snackbar.make(lineRes, "Division by zero!", Snackbar.LENGTH_LONG).show()
                    lineRes.text = ""
                }
                else {
                    var result = m_arg1!!.toDouble() / m_arg2!!.toDouble()
                    lineRes.text = "= " + result.toString()
                }
            }
            else lineRes.text = ""
        }
    }

    private inline fun readArgs(): Boolean {
        m_arg1 = parseDouble(this.lineArg1.text.toString())
        m_arg2 = parseDouble(this.lineArg2.text.toString())

        var state1 = true
        var state2 = true

        if (m_arg1 == null) {
            state1 = false
            Snackbar.make(this.lineResult, "Wrong arg1!", Snackbar.LENGTH_LONG).show()
        }
        if (m_arg2 == null) {
            state2 = false
            Snackbar.make(this.lineResult, "Wrong arg2!", Snackbar.LENGTH_LONG).show()
        }

        return state1 && state2
    }

    private inline fun parseDouble(str: String): Double? {
        val len = str.length

        if (len != 0 && str[len-1] != '.' && str != ".")
            return str.toDouble()
        return  null
    }
}