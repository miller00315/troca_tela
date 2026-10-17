package com.miller.oliveira.trocatela

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class ConfirmActivity : AppCompatActivity() {

    private lateinit var btConfirm: Button

    private lateinit var tvConfirmValue: TextView
    private lateinit var tvConfirmCode: TextView
    private lateinit var tvConfirmQuantity: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_confirm)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        btConfirm = findViewById(R.id.btConfirm)

        tvConfirmValue = findViewById(R.id.tvConfirmValue)
        tvConfirmCode = findViewById(R.id.tvConfirmCode)
        tvConfirmQuantity = findViewById(R.id.tvConfirmQuantity)

        intent.getStringExtra("code")?.let {
            tvConfirmCode.text = it
        }

        intent.getStringExtra("quantity")?.let {
            tvConfirmQuantity.text = it
        }

        intent.getStringExtra("value")?.let {
            tvConfirmValue.text = it
        }
    }
}