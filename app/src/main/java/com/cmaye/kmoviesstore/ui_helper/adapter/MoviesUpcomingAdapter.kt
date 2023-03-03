package com.cmaye.kmoviesstore.ui_helper.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.cmaye.kmoviesstore.common.CommonFunction
import com.cmaye.kmoviesstore.common.DiffCallBack
import com.cmaye.kmoviesstore.databinding.UpcommingListItemHorizontalBinding
import com.cmaye.kmoviesstore.model.ResultsItem


class MoviesUpcomingAdapter(private val onItemClick : (View, ResultsItem, String) -> Unit) : RecyclerView.Adapter<MoviesUpcomingAdapter.ViewHolder>(),
    DiffCallBack
{
    private var moviesList : MutableList<ResultsItem> = mutableListOf()

    private lateinit var binding : UpcommingListItemHorizontalBinding
    class ViewHolder(private val binding : UpcommingListItemHorizontalBinding,onItemClick: (View, ResultsItem, String) -> Unit) : RecyclerView.ViewHolder(binding.root)
    {
        private lateinit var moviesModel : ResultsItem


        fun bindData(movies: ResultsItem)
        {
            with(binding)
            {
                moviesModel = movies
                moviesTitle.text = moviesModel.title
                moviesDetails.text = moviesModel.overview
                val thread = Thread {
                    try {
                        moviesImg.setImageBitmap(CommonFunction.getImage(moviesModel.posterPath!!))
                    } catch (e: java.lang.Exception) {
                        e.printStackTrace()
                    }
                }

                thread.start()


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
            }
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        binding = UpcommingListItemHorizontalBinding.inflate(LayoutInflater.from(parent.context),parent,false)
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
        moviesList = list
    }
}