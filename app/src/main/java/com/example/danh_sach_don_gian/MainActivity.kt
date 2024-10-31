package com.example.danh_sach_don_gian

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView


class MainActivity : AppCompatActivity() {

    private lateinit var edtNumber: EditText
    private lateinit var radioGroup: RadioGroup
    private lateinit var radioEven: RadioButton
    private lateinit var radioOdd: RadioButton
    private lateinit var radioSoChinhPhuong: RadioButton
    private lateinit var btnShow: Button
    private lateinit var listView: ListView
    private lateinit var tvError: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        edtNumber = findViewById(R.id.edtNumber)
        radioGroup = findViewById(R.id.radioGroup)
        radioEven = findViewById(R.id.radioEven)
        radioOdd = findViewById(R.id.radioOdd)
        radioSoChinhPhuong = findViewById(R.id.radioSoChinhPhuong)
        btnShow = findViewById(R.id.btnShow)
        listView = findViewById(R.id.listView)
        tvError = findViewById(R.id.tvError)

        btnShow.setOnClickListener {
            val input = edtNumber.text.toString()
            if (input.isEmpty()) {
                tvError.text = "Vui lòng nhập số nguyên dương."
                return@setOnClickListener
            }

            val n: Int = try {
                input.toInt().takeIf { it >= 0 } ?: run {
                    tvError.text = "Vui lòng nhập số nguyên dương lớn hơn hoặc bằng 0."
                    return@setOnClickListener
                }
            } catch (e: NumberFormatException) {
                tvError.text = "Dữ liệu không hợp lệ. Vui lòng nhập số nguyên."
                return@setOnClickListener
            }

            tvError.text = "" // Xóa thông báo lỗi nếu hợp lệ
            val resultList = ArrayList<String>()

            // Kiểm tra loại RadioButton được chọn
            when (radioGroup.checkedRadioButtonId) {
                -1 -> {
                    tvError.text = "Vui lòng chọn loại số cần hiển thị."
                    return@setOnClickListener
                }
                R.id.radioEven -> {
                    // Số chẵn từ 0 đến n
                    for (i in 0..n step 2) {
                        resultList.add(i.toString())
                    }
                }
                R.id.radioOdd -> {
                    // Số lẻ từ 1 đến n
                    for (i in 1..n step 2) {
                        resultList.add(i.toString())
                    }
                }
                R.id.radioSoChinhPhuong -> {
                    // Số chính phương từ 0 đến n
                    var i = 0
                    while (i * i <= n) {
                        resultList.add((i * i).toString())
                        i++
                    }
                }
            }

            // Hiển thị kết quả trong ListView
            val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, resultList)
            listView.adapter = adapter
        }
    }
}