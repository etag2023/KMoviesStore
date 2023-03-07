package com.cmaye.kmoviesstore.ui_helper.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.cmaye.kmoviesstore.R
import com.cmaye.kmoviesstore.common.DiffCallBack
import com.cmaye.kmoviesstore.common.GeneralClass
import com.cmaye.kmoviesstore.common.SharePref
import com.cmaye.kmoviesstore.databinding.UpcommingListItemHorizontalBinding
import com.cmaye.kmoviesstore.model.ResultsItem
import com.google.gson.Gson


class MoviesUpcomingAdapter(private val onItemClick : (View, ResultsItem, String) -> Unit) : RecyclerView.Adapter<MoviesUpcomingAdapter.ViewHolder>(),
    DiffCallBack
{
    private var moviesList : MutableList<ResultsItem> = mutableListOf()
    private var applicationContext : Context ?= null

    class ViewHolder(private val binding : UpcommingListItemHorizontalBinding, onItemClick: (View, ResultsItem, String) -> Unit) : RecyclerView.ViewHolder(binding.root)
    {
        private lateinit var moviesModel : ResultsItem


        fun bindData(context : Context, movies: ResultsItem)
        {
            with(binding)
            {
                moviesModel = movies
                moviesTitle.text = moviesModel.title
                moviesDetails.text = moviesModel.overview
                moviesRatting.text = "${moviesModel.voteAverage} %"

                if (moviesModel.popularity != null)
                {
                    var resultData = moviesModel.popularity.toString().substringBeforeLast(".")

                    var overThousandPopular : Int = resultData.toInt() / 1000
                    var lessThousandPopular : Int =  resultData.toInt() % 1000

                    if (overThousandPopular > 0)
                    {
                        moviesMessage.text = "$overThousandPopular K"

                    }else{
                        moviesMessage.text = lessThousandPopular.toString()
                    }
                }
                Glide.with(context.applicationContext)
                    .load(GeneralClass.IMAGE_URL + moviesModel.posterPath)
                    .into(moviesImg)

            }
        }
        init {
            with(binding)
            {
                with(cardView){
                    setOnClickListener {
                        onItemClick(cardView,moviesModel,"onClick")
                    }
                }

                with(imgFavorite)
                {
                    setOnClickListener {
                        moviesModel.favoriteData = !moviesModel.favoriteData
                        if (moviesModel.favoriteData)
                        {
                            imgFavorite.setColorFilter(ContextCompat.getColor(context, R.color.dartGreen), android.graphics.PorterDuff.Mode.MULTIPLY);
                        }else{
                            imgFavorite.setColorFilter(ContextCompat.getColor(context, R.color.black), android.graphics.PorterDuff.Mode.MULTIPLY);
                        }
                        onItemClick(imgFavorite,moviesModel,"favorite")
                    }
                }
            }
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var binding = UpcommingListItemHorizontalBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(binding,onItemClick)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindData(applicationContext!!,moviesList[position])
    }

    override fun getItemCount(): Int {
        return moviesList.size
    }

    fun setMoviesList(context: Context,list : MutableList<ResultsItem>)
    {
        applicationContext = context
        moviesList = list
    }
}