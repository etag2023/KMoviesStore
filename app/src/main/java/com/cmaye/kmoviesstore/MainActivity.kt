package com.cmaye.kmoviesstore

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Parcelable
import android.util.Log
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.cmaye.kmoviesstore.activities.MoviesDetailsActivity
import com.cmaye.kmoviesstore.common.CommonFunction
import com.cmaye.kmoviesstore.common.CommonFunction.Companion.hideKeyboard
import com.cmaye.kmoviesstore.common.CommonFunction.Companion.roundDownToMultipleOf
import com.cmaye.kmoviesstore.common.GeneralClass
import com.cmaye.kmoviesstore.common.SharePref
import com.cmaye.kmoviesstore.databinding.ActivityMainBinding
import com.cmaye.kmoviesstore.model.PopularMovies
import com.cmaye.kmoviesstore.model.ResultsItem
import com.cmaye.kmoviesstore.model.UpComingMovies
import com.cmaye.kmoviesstore.ui_helper.adapter.MoviesPopularAdapter
import com.cmaye.kmoviesstore.ui_helper.adapter.MoviesUpcomingAdapter
import com.cmaye.kmoviesstore.ui_helper.viewmodel.MoviesViewModel
import com.cmaye.kmoviesstore.ui_helper.viewmodel.ShareDataViewModel
import com.google.android.material.tabs.TabItem
import com.google.android.material.tabs.TabLayout
import com.google.gson.Gson
import kotlinx.coroutines.launch
import kotlin.math.roundToLong


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var recommendAdapter: MoviesPopularAdapter
    private lateinit var upcomingAdapter: MoviesUpcomingAdapter
    private val viewModel: MoviesViewModel by viewModels()

    var popularMovies: PopularMovies? = null
    var upcomingMovies: UpComingMovies? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setUpRecyclerView()
        bindData()
//        apiTest()
    }


    private fun apiTest()
    {

//        lifecycleScope.launch {
//            var similarMovies = viewModel.getMoviesCredits(500,GeneralClass.APIKEY)
//            Log.e("SMOV",similarMovies.toString())
//        }

    }

    override fun onResume() {
        super.onResume()

        try {
            with(binding)
            {
                if (simpleSearchView != null) {
                    simpleSearchView.clearFocus();
                }
            }

            if (popularMovies != null) {
                var jsonData = SharePref.getSharePref(this, "save_movies_data")
                var resultItem =
                    Gson().fromJson<ResultsItem>(jsonData.toString(), ResultsItem::class.java)
                Log.e("RESULT", resultItem.toString())

                popularMovies!!.myList.forEach { item ->
                    if (item!!.id == resultItem.id) {
                        item.favoriteData = resultItem.favoriteData
                    }
                }

                recommendAdapter.setMoviesList(
                    this@MainActivity, popularMovies!!.myList
                )
                recommendAdapter.notifyDataSetChanged()
            }
            if (upcomingMovies != null) {

                var jsonData = SharePref.getSharePref(this, "save_movies_data")
                var resultItem =
                    Gson().fromJson<ResultsItem>(jsonData.toString(), ResultsItem::class.java)
                Log.e("RESULT", resultItem.toString())


                upcomingMovies!!.results!!.forEach { item ->
                    if (item!!.id == resultItem.id) {
                        item.favoriteData = resultItem.favoriteData
                    }
                }

                upcomingAdapter.setMoviesList(
                    this@MainActivity, upcomingMovies!!.results as MutableList<ResultsItem>
                )
                upcomingAdapter.notifyDataSetChanged()
            }
        } catch (ex: Exception) {
            Toast.makeText(this, ex.toString(), Toast.LENGTH_SHORT).show()
            Log.e("BUG", ex.toString())
        }
    }

    fun bindData() {
        with(binding) {

            moviesTabLayout.addTab(moviesTabLayout.newTab().setText(getString(R.string.movies)))
            moviesTabLayout.addTab(moviesTabLayout.newTab().setText(getString(R.string.event)))
            moviesTabLayout.addTab(moviesTabLayout.newTab().setText(getString(R.string.play)))
            moviesTabLayout.addTab(moviesTabLayout.newTab().setText(getString(R.string.sport)))
            moviesTabLayout.addTab(moviesTabLayout.newTab().setText(getString(R.string.activites)))


            lifecycleScope.launch {
                try {
                    popularMovies = viewModel.getPopularMoviesList(GeneralClass.APIKEY)
                    recommendAdapter.setMoviesList(
                        this@MainActivity, popularMovies!!.myList
                    )
                    recommendAdapter.notifyDataSetChanged()

                    upcomingMovies = viewModel.getUpComingMoviesList(GeneralClass.APIKEY)
                    upcomingAdapter.setMoviesList(
                        this@MainActivity, upcomingMovies!!.results as MutableList<ResultsItem>
                    )
                    upcomingAdapter.notifyDataSetChanged()
                    Log.e("UpComing", upcomingMovies.toString())

                } catch (ex: Exception) {
                    Toast.makeText(this@MainActivity, ex.toString(), Toast.LENGTH_SHORT).show()
                }
            }
        }

    }

    private fun setUpRecyclerView() {
        recommendAdapter = MoviesPopularAdapter { _, MovieResult, action ->
            when (action) {
                "onClick" -> {
                    try {
                        var intent = Intent(this, MoviesDetailsActivity::class.java)
                        intent.putExtra("movies", MovieResult as Parcelable)
                        startActivity(intent)
                    } catch (ex: Exception) {
                        Toast.makeText(this, ex.toString(), Toast.LENGTH_SHORT).show()
                    }
                }

                "favorite" -> {
                    popularMovies!!.results!!.forEach {resultItem ->
                        if (resultItem.id == MovieResult.id)
                        {
                            resultItem.favoriteData = MovieResult.favoriteData
                        }
                    }
                }
            }
        }

        binding.recyclerRecommended.adapter = recommendAdapter
        recommendAdapter.notifyDataSetChanged()

        upcomingAdapter = MoviesUpcomingAdapter { _, MovieResult, action ->
            when (action) {
                "onClick" -> {
                    var intent = Intent(this, MoviesDetailsActivity::class.java)
                    intent.putExtra("movies", MovieResult as Parcelable)
                    startActivity(intent)
                }

                "favorite" ->{
                    upcomingMovies!!.results!!.forEach {resultItem ->
                        if (resultItem!!.id == MovieResult.id)
                        {
                            resultItem.favoriteData = MovieResult.favoriteData
                        }
                    }
                }

            }
        }

        binding.recyclerUpcoming.adapter = upcomingAdapter
        upcomingAdapter.notifyDataSetChanged()
    }
}

