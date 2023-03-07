package com.cmaye.kmoviesstore.ui_helper.adapter

import android.app.Activity
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.cmaye.kmoviesstore.common.GeneralClass
import com.cmaye.kmoviesstore.databinding.CastListItemHorizontalBinding
import com.cmaye.kmoviesstore.model.CastItem

class CastImgRecyclerAdapter() : RecyclerView.Adapter<CastImgRecyclerAdapter.ViewHolder>()
{
    private var castList : MutableList<CastItem> = mutableListOf()
    private lateinit var applicationContext : Activity
    class ViewHolder(private val binding : CastListItemHorizontalBinding) : RecyclerView.ViewHolder(binding.root)
    {
        fun bindData(context : Context,castItem: CastItem)
        {
            with(binding)
            {
                castTitle.text = castItem.name
                Glide.with(context.applicationContext)
                    .load(GeneralClass.IMAGE_URL + castItem.profilePath)
                    .into(castImg)
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var binding = CastListItemHorizontalBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return castList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindData(applicationContext,castList[position])
    }

    fun sendCastList(context: Activity,list : MutableList<CastItem>)
    {
        this.applicationContext = context
        this.castList = list
        notifyDataSetChanged()

    }
}