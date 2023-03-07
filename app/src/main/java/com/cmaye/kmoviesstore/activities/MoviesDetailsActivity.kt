package com.cmaye.kmoviesstore.activities

import android.R
import android.annotation.SuppressLint
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.cmaye.kmoviesstore.MainActivity
import com.cmaye.kmoviesstore.common.CommonFunction
import com.cmaye.kmoviesstore.common.DateTimeText
import com.cmaye.kmoviesstore.common.GeneralClass
import com.cmaye.kmoviesstore.common.SharePref
import com.cmaye.kmoviesstore.databinding.ActivityDetailsBinding
import com.cmaye.kmoviesstore.model.CastItem
import com.cmaye.kmoviesstore.model.ResultsItem
import com.cmaye.kmoviesstore.ui_helper.adapter.CastImgRecyclerAdapter
import com.cmaye.kmoviesstore.ui_helper.viewmodel.MoviesViewModel
import com.cmaye.kmoviesstore.ui_helper.viewmodel.ShareDataViewModel
import com.google.gson.Gson
import kotlinx.coroutines.launch


class MoviesDetailsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailsBinding
    private lateinit var movies : ResultsItem
    private lateinit var adapter : CastImgRecyclerAdapter
    private val viewModel : MoviesViewModel by viewModels()


    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initialized()
        setUpRecyclerView()
        bindData()
        onClickListener()
        binding.tvMoviesDescriptionDetails.movementMethod = ScrollingMovementMethod.getInstance()
    }

    private fun initialized() {
        try {
            movies = intent.getParcelableExtra("movies") !!
        } catch (ex: Exception) {
            Log.e("EXE", ex.toString())
            Toast.makeText(this, ex.toString(), Toast.LENGTH_SHORT).show()
        }
    }

    private fun setUpRecyclerView()
    {
        adapter = CastImgRecyclerAdapter()
        binding.castImgRecycler.adapter = adapter
    }
    @SuppressLint("SetTextI18n")
    @RequiresApi(Build.VERSION_CODES.O)
    fun bindData() {
        with(binding) {

            if (movies.favoriteData)
            {
                imgFavorite.setColorFilter(ContextCompat.getColor(this@MoviesDetailsActivity, com.cmaye.kmoviesstore.R.color.dartGreen), android.graphics.PorterDuff.Mode.MULTIPLY)
            }else{
                imgFavorite.setColorFilter(ContextCompat.getColor(this@MoviesDetailsActivity, com.cmaye.kmoviesstore.R.color.black), android.graphics.PorterDuff.Mode.MULTIPLY)
            }
            lifecycleScope.launch {
                try {
                    var moviesDetails = viewModel.getMovieDetails(movies.id!!.toInt(),GeneralClass.APIKEY)
                    var moviesCredits = viewModel.getMoviesCredits(movies.id!!.toInt(),GeneralClass.APIKEY)
                    if (moviesDetails != null)
                    {
                        tvMoviesTitle.text = moviesDetails.title
                        tvMoviesDescriptionDetails.text = moviesDetails.overview
                        tvMoviesRatting.text = "${CommonFunction.roundDownToMultipleOf(moviesDetails.voteAverage.toString().toDouble())} % "
                        tvVotesCounts.text = "${moviesDetails.voteCount.toString()} votes"


                        Log.e("RATING",moviesDetails.voteAverage.toString())


                        /**get spoken language*/
                        var allAvailableSpokenLanguage = ""
                        if (moviesDetails.spokenLanguages!!.size == 1)
                            allAvailableSpokenLanguage = moviesDetails.spokenLanguages!![0].toString()
                        else{
                            moviesDetails.spokenLanguages!!.forEachIndexed { index, spokenLanguagesItem ->
                                allAvailableSpokenLanguage = "$spokenLanguagesItem ${if (index == moviesDetails.spokenLanguages!!.size - 1) "" else "|"}"
                            }
                        }

                        tvProductionCountries.text = moviesDetails.productionCountries?.get(0)?.iso31661.toString()

                        /**duration*/
                        var duration = ""
                        if (moviesDetails.runtime != null)
                        {
                            duration = DateTimeText.getHourAndMinutesFromMinute(moviesDetails.runtime!!.toInt())
                        }

                        /**movies type*/
                        var moviesType = ""
                        if (moviesDetails.genres!!.size == 1)
                            moviesType = moviesDetails.genres!![0]!!.name.toString()
                        else{
                            moviesDetails.genres!!.forEachIndexed { index, genres ->
                                if (genres != null) {
                                    moviesType = "${genres.name} ${if (index == moviesDetails.genres!!.size - 1) "" else ","}"
                                }
                            }
                        }

                        tvTimesAndMoviesType.text = "$duration| $moviesType"


                        if (moviesDetails.releaseDate != null)
                        {
                            tvUploadCountryAndDate.text = DateTimeText.convertDateFormat(DateTimeText.DATE_FORMAT3,DateTimeText.DATE_FORMAT2,movies.releaseDate.toString())
                        }

                        val imgURL = GeneralClass.IMAGE_URL + moviesDetails.backdropPath


                        Glide.with(applicationContext)
                            .load(imgURL)
                            .into(imgDetails)

                        if (moviesCredits != null)
                        {
                            adapter.sendCastList(this@MoviesDetailsActivity,moviesCredits.cast as MutableList<CastItem>)
                        }
                    }
                }catch (ex : Exception)
                {
                    Toast.makeText(this@MoviesDetailsActivity, ex.toString(), Toast.LENGTH_SHORT).show()
                    Log.e("EXE",ex.toString())

                }

            }
        }
    }

    private fun onClickListener() {
        with(binding) {
            imgBack.setOnClickListener {
                super.onBackPressed()
            }
            imgFavorite.setOnClickListener {
                movies.favoriteData = !movies.favoriteData!!
                if (movies.favoriteData!!)
                {
                    imgFavorite.setColorFilter(ContextCompat.getColor(this@MoviesDetailsActivity, com.cmaye.kmoviesstore.R.color.dartGreen), android.graphics.PorterDuff.Mode.MULTIPLY)
                }else{
                    imgFavorite.setColorFilter(ContextCompat.getColor(this@MoviesDetailsActivity, com.cmaye.kmoviesstore.R.color.black), android.graphics.PorterDuff.Mode.MULTIPLY)
                }
                bindFav(movies)
            }
        }
    }
    fun bindFav(obj : ResultsItem)
    {
        var gson = Gson()
        var objJson = gson.toJson(obj)
        SharePref.saveSharePref(this,"save_movies_data",objJson.toString())
    }
}

