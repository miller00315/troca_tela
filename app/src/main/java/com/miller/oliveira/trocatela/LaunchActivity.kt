package com.miller.oliveira.trocatela

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class LaunchActivity : AppCompatActivity() {

    private lateinit var edCode: EditText
    private lateinit var edQuantity: EditText
    private lateinit var edValue: EditText

    private lateinit var btConfirm: Button
    private lateinit var btList: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_launch)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        edCode = findViewById(R.id.etCode)
        edQuantity = findViewById(R.id.etQuantity)
        edValue = findViewById(R.id.etValue)

        btConfirm = findViewById(R.id.btConfirm)
        btList = findViewById(R.id.btList)

        btConfirm.setOnClickListener {
            btConfirmOnCLick()
        }

        btList.setOnClickListener {
            btListOnCLick()
        }
    }

    private fun btConfirmOnCLick() {

        val intent = Intent(this, ConfirmActivity::class.java)

        var error = false

        if (edCode.text.isEmpty()) {
            edCode.error = "Campo obrigatório"

            error = true
        }
        if (edQuantity.text.isEmpty()) {
            edQuantity.error = "Campo obrigatório"

            error = true
        }
        if (edValue.text.isEmpty()) {
            edValue.error = "Campo obrigatório"

            error = true
        }

        if (error) {
            Toast.makeText(
                this,
                "Verifique o scampos",
                Toast.LENGTH_SHORT
            ).show()

            return
        }


        intent.putExtra("code", edCode.text.toString())
        intent.putExtra("quantity", edQuantity.text.toString())
        intent.putExtra("value", edValue.text.toString())

        startActivity(intent)
    }

    private fun btListOnCLick() {

    }
}