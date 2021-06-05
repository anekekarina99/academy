package com.dicoding.motive.ui.movie

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.dicoding.motive.R
import com.dicoding.motive.data.source.local.entity.MovieEntity
import com.dicoding.motive.databinding.ItemsMovieBinding
import com.dicoding.motive.ui.detail.DetailMovieActivity
import com.dicoding.motive.utils.Constant.Companion.POSTER_BASE_URL
import kotlin.collections.ArrayList

class MovieAdapter : RecyclerView.Adapter<MovieAdapter.CourseViewHolder>() {
    private var listCourses = ArrayList<MovieEntity>()

    fun setCourses(cour: List<MovieEntity>) {
        this.listCourses.clear()
        this.listCourses.addAll(cour)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CourseViewHolder {
        val itemsMovieBinding = ItemsMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CourseViewHolder(itemsMovieBinding)
    }

    override fun onBindViewHolder(holder: CourseViewHolder, position: Int) {
        val course = listCourses[position]
        holder.bind(course)
    }

    override fun getItemCount(): Int = listCourses.size


    class CourseViewHolder(private val binding: ItemsMovieBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(detailMovie: MovieEntity) {
            with(binding) {
                tvItemTitle.text = detailMovie.originalTitle
               tvItemOverview.text = detailMovie.overview
                itemView.setOnClickListener {
                    val intent = Intent(itemView.context, DetailMovieActivity::class.java)
                    intent.putExtra(DetailMovieActivity.EXTRA_ID, detailMovie.id)
                    itemView.context.startActivity(intent)
                }
                Glide.with(itemView.context)
                        .load(POSTER_BASE_URL+detailMovie.posterPath)
                        .apply(
                            RequestOptions.placeholderOf(R.drawable.ic_loading)
                                .error(R.drawable.ic_error))
                        .into(imgPoster)
            }
        }
    }
}