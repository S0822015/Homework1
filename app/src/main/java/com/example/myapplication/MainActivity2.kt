package com.example.myapplication

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*

class MainActivity2 : AppCompatActivity() {

    private lateinit var spinner1: Spinner
    private lateinit var spinner2: Spinner

    @SuppressLint("SuspiciousIndentation")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        // 設定第一個下拉式選單
        spinner1 = findViewById(R.id.spinner1)

        // 在下拉式選單中添加選項
        val items1 = listOf("XS", "S", "M", "L", "XL", "XXL", "3XL")
        val adapter1 = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, items1)
        spinner1.adapter = adapter1

        // 設定選擇選項後禁用下拉式選單
        spinner1.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                spinner1.setSelection(position)
            }
            override fun onNothingSelected(parent: AdapterView<*>?) {
                // do nothing
            }
        }

        // 設定第二個下拉式選單
        spinner2 = findViewById(R.id.spinner2)

        // 在下拉式選單中添加選項
        val items2 = listOf("Black", "White", "Red", "Blue", "Purple", "Yellow", "Gray")
        val adapter2 = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, items2)
        spinner2.adapter = adapter2

        // 設定選擇選項後禁用下拉式選單
        spinner2.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                spinner2.setSelection(position)
            }
            override fun onNothingSelected(parent: AdapterView<*>?) {
                // do nothing
            }
        }

        // 設置confirm按鈕為回傳輸入文字與跳轉回原頁面
        val button1 = findViewById<Button>(R.id.button1)
            button1.setOnClickListener{

                val editText1 = findViewById<EditText>(R.id.editTextTextPersonName1)
                val editText2 = findViewById<EditText>(R.id.editTextTextPersonName2)
                val editText3 = findViewById<EditText>(R.id.editTextTextPersonName3)
                val selectedOption1 = spinner1.selectedItem.toString()
                val selectedOption2 = spinner2.selectedItem.toString()
                val intent=Intent(this,MainActivity::class.java)

                intent.putExtra("selectedOption1", selectedOption1)
                intent.putExtra("selectedOption2", selectedOption2)
                intent.putExtra("key1",editText1.text.toString())
                intent.putExtra("key2",editText2.text.toString())
                intent.putExtra("key3",editText3.text.toString())

                startActivity(intent)
                finish()

            }

        intent?.extras?.let{

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

        }
    }

    override fun onResume() {
        super.onResume()

        // 讀取選項值
        val sharedPref1 = getSharedPreferences("MyPref", Context.MODE_PRIVATE)
        val selectedOption1 = sharedPref1.getString("selectedOption1", "")
        val sharedPref2 = getSharedPreferences("MyPref", Context.MODE_PRIVATE)
        val selectedOption2 = sharedPref2.getString("selectedOption2", "")
        if (selectedOption1 != null && selectedOption1.isNotEmpty()) {
            // 設定下拉式選單的選項
            val position = (spinner1.adapter as ArrayAdapter<String>).getPosition(selectedOption1)
            spinner1.setSelection(position)
        }
        if (selectedOption2 != null && selectedOption2.isNotEmpty()) {
            // 設定下拉式選單的選項
            val position = (spinner2.adapter as ArrayAdapter<String>).getPosition(selectedOption2)
            spinner2.setSelection(position)
        }
    }

    override fun onPause() {
        super.onPause()

        // 儲存選項值
        val sharedPref1 = getSharedPreferences("MyPref", Context.MODE_PRIVATE)
        with(sharedPref1.edit()) {
            putString("selectedOption1", spinner1.selectedItem.toString())
            commit()
        }
        val sharedPref2 = getSharedPreferences("MyPref", Context.MODE_PRIVATE)
        with(sharedPref2.edit()) {
            putString("selectedOption2", spinner2.selectedItem.toString())
            commit()
        }
    }
}

