package com.dicoding.motive.ui.detail

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.dicoding.motive.R
import com.dicoding.motive.databinding.ActivityTvDetailBinding
import com.dicoding.motive.utils.Constant
import com.dicoding.motive.viewmodel.ViewModelFactory
import com.dicoding.motive.vo.Status

class DetailTvActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_COURSE = "id"
    }

    private lateinit var detailMovieBinding: ActivityTvDetailBinding
    private lateinit var viewModel : DetailTvViewModel
    private var menu: Menu? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        detailMovieBinding = ActivityTvDetailBinding.inflate(layoutInflater)

        setContentView( detailMovieBinding.root)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val factory = ViewModelFactory.getInstance(this)
        viewModel = ViewModelProvider(this, factory)[DetailTvViewModel::class.java]

        getDetailTv()
        setFavoriteTv()


    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_detail, menu)
        this.menu = menu
        viewModel.getTvDetail.observe(this, {
            if (it != null) {
                when (it.status) {
                    Status.LOADING -> detailMovieBinding.progressBar.visibility = View.VISIBLE
                    Status.SUCCESS -> if (it.data != null) {
                        val state = it.data.isFavorite
                        setFavS(state)
                    }
                    Status.ERROR -> {
                        detailMovieBinding.progressBar.visibility = View.GONE
                        Toast.makeText(this, "Terjadi kesalahan", Toast.LENGTH_SHORT).show()

                    }

                }
            }
        })
        return true
    }

    private fun setFavoriteTv() {
        viewModel.getTvDetail.observe(this, {
            if (it != null) {
                if (it.data != null) {
                    val state = it.data.isFavorite
                    setFavS(state)
                }
            }
        })
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId){
            R.id.btnFav -> {
                viewModel.setTv()
                return true
            }
            else-> super.onOptionsItemSelected(item)
        }
    }

    private fun setFavS(state: Boolean) {
        if(menu == null) return
        val menuI = menu?.findItem(R.id.btnFav)
        if (state) {
            menuI?.icon = ContextCompat.getDrawable(this, R.drawable.ic_bookmark_blue)
        } else {
            menuI?.icon = ContextCompat.getDrawable(this, R.drawable.ic_bookmark_white)
        }

    }

    private fun getDetailTv() {
        val extras = intent.extras
        if (extras != null) {
            val courseId = extras.getInt(EXTRA_COURSE, 0)
            viewModel.setTvId(courseId)
            viewModel.getTvDetail.observe(this, {
                if (it != null) {
                    when (it.status) {
                        Status.LOADING -> detailMovieBinding.progressBar.visibility = View.VISIBLE
                        Status.SUCCESS -> if (it.data != null) {
                            detailMovieBinding.progressBar.visibility = View.GONE
                            detailMovieBinding.textTitle.text = it.data.originalName
                            detailMovieBinding.textDescription.text = it.data.overview
                            detailMovieBinding.textDate.text = it.data.firstAirDate
                            detailMovieBinding.textDesc.text = it.data.id.toString()

                            Glide.with(this)
                                .load(Constant.POSTER_BASE_URL + it.data.posterPath)
                                .transform(RoundedCorners(10))
                                .into(detailMovieBinding.imagePoster)
                        }
                        Status.ERROR -> {
                            detailMovieBinding.progressBar.visibility = View.GONE
                            Toast.makeText(this, "Terjadi kesalahan", Toast.LENGTH_SHORT).show()
                        }
                    }
                }

            })

        }
    }
}
