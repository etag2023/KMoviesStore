package com.cmaye.kmoviesstore.ui_helper.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.cmaye.kmoviesstore.common.CommonFunction
import com.cmaye.kmoviesstore.common.DiffCallBack
import com.cmaye.kmoviesstore.databinding.RecommendedListItemHorizontalBinding
import com.cmaye.kmoviesstore.model.ResultsItem

class MoviesPopularAdapter(private val onItemClick : (View, ResultsItem, String) -> Unit) : RecyclerView.Adapter<MoviesPopularAdapter.ViewHolder>(),
    DiffCallBack
{
//    private var moviesList : MutableList<Movies> by Delegates.observable(mutableListOf()) { _, old, new ->
//        compareItem(old,new) {o,n -> o.MoviesID === n.MoviesID}
//    }

    private var moviesList : MutableList<ResultsItem> = mutableListOf()
    private lateinit var binding : RecommendedListItemHorizontalBinding
    class ViewHolder(private val binding : RecommendedListItemHorizontalBinding,onItemClick: (View, ResultsItem, String) -> Unit) : RecyclerView.ViewHolder(binding.root)
    {
        private lateinit var moviesResult : ResultsItem


        fun bindData(movies: ResultsItem)
        {
            with(binding)
            {
                try {
                    moviesResult = movies
                    moviesTitle.text = moviesResult.title

                    val thread = Thread {
                        try {
                            moviesImg.setImageBitmap(CommonFunction.getImage(moviesResult.posterPath!!))
                        } catch (e: java.lang.Exception) {
                            e.printStackTrace()
                        }
                    }

                    thread.start()

                }catch (ex : Exception)
                {
                    ex.toString()
                }
            }
        }
        init {
            with(binding)
            {
                with(cardView){
                    setOnClickListener {
                        onItemClick(cardView,moviesResult,"onClick")
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
        holder.bindData(moviesList[position])
    }

    override fun getItemCount(): Int {
        return moviesList.size
    }

    fun setMoviesList(list : MutableList<ResultsItem>)
    {
        this.moviesList = list
    }
}