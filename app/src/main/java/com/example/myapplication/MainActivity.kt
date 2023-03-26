package com.example.myapplication

import android.app.AlertDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.InputFilter
import android.text.InputType
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import java.util.*

@Suppress("DEPRECATION")
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val editText4 = findViewById<EditText>(R.id.editTextTextPersonName4)
        editText4.isEnabled = false

        val editText5 = findViewById<EditText>(R.id.editTextTextPersonName5)
        editText5.isEnabled = false

        val button2 = findViewById<Button>(R.id.button2)
        button2.isEnabled = false

        // 設置輸入類型為文字
        val editText3 = findViewById<EditText>(R.id.editTextTextPersonName3)
        editText3.inputType = InputType.TYPE_CLASS_TEXT

        // 設置輸入為female或male
        val filter = InputFilter { source, start, end, dest, dstart, dend ->
            val input = source.subSequence(start, end).toString().toLowerCase(Locale.getDefault())
            if (input == "male" || input == "female") null else ""
        }
        editText3.filters = arrayOf(filter)

        // 設置next按鈕為傳送輸入文字與跳轉頁面
        val button1 = findViewById<Button>(R.id.button1)
        button1.setOnClickListener {
            val editText1 = findViewById<EditText>(R.id.editTextTextPersonName1)
            val editText2 = findViewById<EditText>(R.id.editTextTextPersonName2)
            val editText3 = findViewById<EditText>(R.id.editTextTextPersonName3)
            val intent = Intent(this, MainActivity2::class.java)

            intent.putExtra("key1", editText1.text.toString())
            intent.putExtra("key2", editText2.text.toString())
            intent.putExtra("key3", editText3.text.toString())

            startActivity(intent)
            finish()
        }

        // 設置submit按鈕為結束程式
        button2.setOnClickListener{
            val builder = AlertDialog.Builder(this)
            builder.setMessage("Order information has been submitted.")
                .setCancelable(false)
                .setPositiveButton("confirm") { dialog, _ ->
                    dialog.dismiss()
                    finish()
                }
            val alert = builder.create()
            alert.show()

        }

        intent?.extras?.let {

            val value1 = it.getString("key1")
            val value2 = it.getString("key2")
            val value3 = it.getString("key3")

            val editText1 = findViewById<EditText>(R.id.editTextTextPersonName1)
            editText1.setText(value1.toString())
            editText1.isEnabled = false

            val editText2 = findViewById<EditText>(R.id.editTextTextPersonName2)
            editText2.setText(value2.toString())
            editText2.isEnabled = false

            val editText3 = findViewById<EditText>(R.id.editTextTextPersonName3)
            editText3.setText(value3.toString())
            editText3.isEnabled = false

            val editText4 = findViewById<EditText>(R.id.editTextTextPersonName4)
            val selectedOption1 = it.getString("selectedOption1")
            editText4.setText(selectedOption1.toString())
            editText4.isEnabled = false

            val editText5 = findViewById<EditText>(R.id.editTextTextPersonName5)
            val selectedOption2 = it.getString("selectedOption2")
            editText5.setText(selectedOption2.toString())
            editText5.isEnabled = false

            button2.isEnabled=true
        }
    }
}