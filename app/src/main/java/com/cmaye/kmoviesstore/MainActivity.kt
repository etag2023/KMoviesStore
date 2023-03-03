package com.cmaye.kmoviesstore

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Parcelable
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import com.cmaye.kmoviesstore.activities.MoviesDetailsActivity
import com.cmaye.kmoviesstore.common.GeneralClass
import com.cmaye.kmoviesstore.databinding.ActivityMainBinding
import com.cmaye.kmoviesstore.model.ResultsItem
import com.cmaye.kmoviesstore.ui_helper.adapter.MoviesPopularAdapter
import com.cmaye.kmoviesstore.ui_helper.adapter.MoviesUpcomingAdapter
import com.cmaye.kmoviesstore.ui_helper.viewmodel.MoviesViewModel
import kotlinx.coroutines.launch


class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding
    private lateinit var recommendAdapter : MoviesPopularAdapter
    private lateinit var upcomingAdapter : MoviesUpcomingAdapter
    private val viewModel : MoviesViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setUpRecyclerView()
        bindData()
    }

    fun bindData()
    {
        lifecycleScope.launch {
            try{
                var moviesList = viewModel.getPopularMoviesList(GeneralClass.APIKEY)
                recommendAdapter.setMoviesList(moviesList!!.results as MutableList<ResultsItem>)
                recommendAdapter.notifyDataSetChanged()

                var moviesUpComingMoviesList = viewModel.getUpComingMoviesList(GeneralClass.APIKEY)
                upcomingAdapter.setMoviesList(moviesUpComingMoviesList!!.results as MutableList<ResultsItem>)
                upcomingAdapter.notifyDataSetChanged()
                Log.e("UpComing",moviesUpComingMoviesList.toString())

            }catch (ex : Exception)
            {
                Toast.makeText(this@MainActivity, ex.toString(), Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun setUpRecyclerView()
    {
        recommendAdapter = MoviesPopularAdapter{ _, MovieResult, action ->
            when(action)
            {
                "onClick" ->{
                        try {
                            var intent = Intent(this, MoviesDetailsActivity::class.java)
                            intent.putExtra("movies",MovieResult as Parcelable)
                            startActivity(intent)
                        }catch (ex : Exception)
                        {
                            Toast.makeText(this, ex.toString(), Toast.LENGTH_SHORT).show()
                        }


                }
            }
        }

        binding.recyclerRecommended.adapter = recommendAdapter
        recommendAdapter.notifyDataSetChanged()

        upcomingAdapter = MoviesUpcomingAdapter{_,MovieResult,action ->
            when(action)
            {
                "onClick"->{
                    var intent = Intent(this,MoviesDetailsActivity::class.java)
                    intent.putExtra("movies",MovieResult as Parcelable)
                    startActivity(intent)

                }
            }
        }

        binding.recyclerUpcoming.adapter = upcomingAdapter
        upcomingAdapter.notifyDataSetChanged()
    }
}

