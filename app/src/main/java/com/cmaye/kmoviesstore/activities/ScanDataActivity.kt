package com.cmaye.kmoviesstore.activities

import android.os.Build
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.cmaye.kmoviesstore.databinding.ActivityScanDataBinding
import com.cmaye.kmoviesstore.ui_helper.adapter.ScanItemDataAdapter
import com.cmaye.kmoviesstore.ui_helper.adapter.ValueString
import kotlin.random.Random

class ScanDataActivity : AppCompatActivity() {
    private lateinit var binding: ActivityScanDataBinding
    private var dataList: MutableList<ValueString> = mutableListOf()
    private lateinit var scanDataAdpter: ScanItemDataAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityScanDataBinding.inflate(layoutInflater)
        supportActionBar!!.title = "MyData"
        setContentView(binding.root)
        setUpRecyclerView()
        bindData()
        onClickListener()
    }

    private fun bindData() {
        with(binding) {
            var maxInt = Random.nextInt(0, 500)
            Log.e("ASDASDA", maxInt.toString())
            for (i in 0..maxInt) {

                var myInt = i + Random.nextInt(0, 1500)

                var strList = String.format("%04d", myInt)
                dataList.add(ValueString(strList))
            }

            scanDataAdpter.setScanDataList(dataList)
        }
    }

    private fun setUpRecyclerView() {
        scanDataAdpter = ScanItemDataAdapter{ View,ValueString,action ->
            when(action)
            {
                "itemClick" -> Toast.makeText(this, "invalid item", Toast.LENGTH_SHORT).show()
            }
        }

        binding.recyclerScanData.adapter = scanDataAdpter
    }

    private fun onClickListener() {
        with(binding) {
            btnScan.setOnClickListener {
//                scan(133)
                var number = etNumber.text.toString()
                scanDataAdpter.highLightScan(number)
                etNumber.text = null
                etNumber.requestFocus()

            }
        }
    }

}