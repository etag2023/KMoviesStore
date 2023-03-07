package com.cmaye.kmoviesstore.ui_helper.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.cmaye.kmoviesstore.R
import com.cmaye.kmoviesstore.common.CommonFunction
import com.cmaye.kmoviesstore.common.DiffCallBack
import com.cmaye.kmoviesstore.common.GeneralClass
import com.cmaye.kmoviesstore.common.SharePref
import com.cmaye.kmoviesstore.databinding.RecommendedListItemHorizontalBinding
import com.cmaye.kmoviesstore.model.ResultsItem
import com.google.gson.Gson

class MoviesPopularAdapter(private val onItemClick : (View, ResultsItem, String) -> Unit) : RecyclerView.Adapter<MoviesPopularAdapter.ViewHolder>(),
    DiffCallBack
{
//    private var moviesList : MutableList<Movies> by Delegates.observable(mutableListOf()) { _, old, new ->
//        compareItem(old,new) {o,n -> o.MoviesID === n.MoviesID}
//    }

    private var moviesList : MutableList<ResultsItem> = mutableListOf()
    private var applicationContext : Context ?= null
    private lateinit var binding : RecommendedListItemHorizontalBinding
    class ViewHolder(private val binding : RecommendedListItemHorizontalBinding,
                     onItemClick: (View, ResultsItem, String) -> Unit) : RecyclerView.ViewHolder(binding.root)
    {
        private lateinit var moviesResult : ResultsItem


        fun bindData(context : Context, movies: ResultsItem)
        {
            with(binding)
            {
                try {
                    moviesResult = movies
                    moviesTitle.text = moviesResult.title
                    moviesRatting.text = "${movies.voteAverage.toString()} % "
                    if (moviesResult.favoriteData!!)
                    {
                        imgFavorite.setColorFilter(ContextCompat.getColor(context, R.color.dartGreen), android.graphics.PorterDuff.Mode.MULTIPLY);
                    }else{
                        imgFavorite.setColorFilter(ContextCompat.getColor(context, R.color.black), android.graphics.PorterDuff.Mode.MULTIPLY);
                    }

                    Glide.with(context.applicationContext)
                        .load(GeneralClass.IMAGE_URL + moviesResult.posterPath)
                        .into(moviesImg)
                }catch (ex : Exception)
                {
                    ex.toString()
                }
            }
        }
        init {
            with(binding)
            {
//                with(cardView){
//                    setOnClickListener {
//                        onItemClick(cardView,moviesResult,"onClick")
//                    }
//                }
                with(moviesImg)
                {
                    setOnClickListener {
                        onItemClick(moviesImg,moviesResult,"onClick")

                    }
                }
                with(imgFavorite)
                {
                    setOnClickListener {
                        moviesResult.favoriteData = !moviesResult.favoriteData
                        if (moviesResult.favoriteData!!)
                        {
                            imgFavorite.setColorFilter(ContextCompat.getColor(context, R.color.dartGreen), android.graphics.PorterDuff.Mode.MULTIPLY);
                        }else{
                            imgFavorite.setColorFilter(ContextCompat.getColor(context, R.color.black), android.graphics.PorterDuff.Mode.MULTIPLY);
                        }
                        var gson = Gson()
                        var objJson = gson.toJson(moviesResult)
                        SharePref.saveSharePref(context,"save_movies_data",objJson.toString())


                        onItemClick(imgFavorite,moviesResult,"favorite")
                    }
                }
            }
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        binding = RecommendedListItemHorizontalBinding.inflate(LayoutInflater.from(parent.context),parent,false)
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
        this.moviesList = list
    }
}