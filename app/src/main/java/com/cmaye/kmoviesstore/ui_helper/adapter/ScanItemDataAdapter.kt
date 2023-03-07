package com.cmaye.kmoviesstore.ui_helper.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.cmaye.kmoviesstore.common.DiffCallBack
import com.cmaye.kmoviesstore.databinding.ScanItemDataBinding

class ScanItemDataAdapter(private val onItemClick: (View, ValueString, String) -> Unit) :
    RecyclerView.Adapter<ScanItemDataAdapter.ViewHolder>(), DiffCallBack {
    private var scanDataList: MutableList<ValueString> = mutableListOf()

    class ViewHolder(
        private val binding: ScanItemDataBinding, onItemClick: (View, ValueString, String) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {
        lateinit var strValue: ValueString


        fun bindData(data: ValueString) {
            strValue = data
            with(binding) {

                scanItem.text = data.dataString
                if (data.isScan) {
                    scanItem.setBackgroundColor(Color.GREEN)
                } else {
                    scanItem.setBackgroundColor(Color.WHITE)
                }
            }
        }

        init {
            with(binding) {
                cardView.setOnClickListener {
                    if (strValue.scanClick) {
                        if (strValue.isScan) {
                            strValue.isScan = false
                            scanItem.setBackgroundColor(Color.WHITE)
                        } else {
                            strValue.isScan = true
                            scanItem.setBackgroundColor(Color.GREEN)
                        }
                    } else {
                        onItemClick(cardView, strValue, "itemClick")
                    }
                }
            }
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            ScanItemDataBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding, onItemClick)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindData(scanDataList[position])
    }

    override fun getItemCount(): Int {
        return scanDataList.size
    }

    fun setScanDataList(list: MutableList<ValueString>) {
        this.scanDataList = list
        notifyDataSetChanged()
    }

    fun highLightScan(highLightNum: String) {
        val data = scanDataList.find { it.dataString == highLightNum }
        if (data != null) {
            data.isScan = true
            data.scanClick = true
            notifyDataSetChanged()
        }
    }
}

data class ValueString(
    val dataString: String, var isScan: Boolean = false, var scanClick: Boolean = false
)
