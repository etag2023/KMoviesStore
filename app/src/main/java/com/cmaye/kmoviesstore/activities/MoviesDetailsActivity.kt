package com.cmaye.kmoviesstore.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.util.Log
import android.widget.Toast
import com.cmaye.kmoviesstore.common.CommonFunction
import com.cmaye.kmoviesstore.databinding.ActivityDetailsBinding
import com.cmaye.kmoviesstore.model.ResultsItem


class MoviesDetailsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailsBinding

    private lateinit var movies : ResultsItem

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initialized()
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

    fun bindData() {
        with(binding) {
            try {
                tvMoviesTitle.text = movies.title
                tvMoviesRatting.text = "12"
                tvMoviesDescriptionDetails.text = movies.overview
                val thread = Thread {
                    try {
                        imgDetails.setImageBitmap(CommonFunction.getImage(movies.backdropPath!!))
                    } catch (e: java.lang.Exception) {
                        e.printStackTrace()
                    }
                }

                thread.start()
            } catch (ex: Exception) {
                Log.e("EXE", ex.toString())
                Toast.makeText(this@MoviesDetailsActivity, ex.toString(), Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun onClickListener() {
        with(binding) {
            imgBack.setOnClickListener { super.onBackPressed() }

        }
    }

}

