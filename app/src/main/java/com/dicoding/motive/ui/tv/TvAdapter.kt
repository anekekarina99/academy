package com.dicoding.motive.ui.tv

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.dicoding.motive.R
import com.dicoding.motive.data.source.local.entity.TvEntity
import com.dicoding.motive.databinding.ItemsTvBinding
import com.dicoding.motive.ui.detail.DetailTvActivity
import com.dicoding.motive.utils.Constant.Companion.POSTER_BASE_URL

class TvAdapter : PagedListAdapter<TvEntity, TvAdapter.CourseViewHolder>(DIFF_CALLBACK) {

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<TvEntity>() {
            override fun areItemsTheSame(oldItem: TvEntity, newItem: TvEntity): Boolean {
                return oldItem.id == newItem.id
            }
            override fun areContentsTheSame(oldItem: TvEntity, newItem: TvEntity): Boolean {
                return oldItem == newItem
            }
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CourseViewHolder {
        val itemsTvBinding = ItemsTvBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CourseViewHolder(itemsTvBinding)
    }

    override fun onBindViewHolder(holder: TvAdapter.CourseViewHolder, position: Int) {
        val course = getItem(position)
        if (course != null) {
            holder.bind(course)
        }
    }

    inner class CourseViewHolder(private val binding: ItemsTvBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(detailTv: TvEntity) {
            with(binding) {
                tvItemTitle.text = detailTv.originalName
                tvItemOverview.text = detailTv.overview
                itemView.setOnClickListener {
                    val intent = Intent(itemView.context, DetailTvActivity::class.java)
                    intent.putExtra(DetailTvActivity.EXTRA_COURSE, detailTv.id)
                    itemView.context.startActivity(intent)
                }
                Glide.with(itemView.context)
                        .load(POSTER_BASE_URL+detailTv.posterPath)
                        .apply(
                            RequestOptions.placeholderOf(R.drawable.ic_loading)
                                .error(R.drawable.ic_error))
                        .into(imgPoster)
            }
        }
    }
}