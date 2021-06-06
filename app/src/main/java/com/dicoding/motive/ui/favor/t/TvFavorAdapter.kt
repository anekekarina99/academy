package com.dicoding.motive.ui.favor.t

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
import com.dicoding.motive.databinding.ItemsTvFavoriteBinding
import com.dicoding.motive.ui.detail.DetailTvActivity
import com.dicoding.motive.utils.Constant

class TvFavorAdapter (private val callback :TvFavorFragmentCallback ):
    PagedListAdapter<TvEntity, TvFavorAdapter.ViewHolder>(DIFF_CALLBACK) {
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

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemsTBinding =
            ItemsTvFavoriteBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(itemsTBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val course = getItem(position)
        if (course != null) {
            holder.bind(course)
        }
    }

    fun getSwipedData(swipedPosition: Int): TvEntity? = getItem(swipedPosition)

    inner class ViewHolder(private val binding: ItemsTvFavoriteBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(course: TvEntity) {
            with(binding) {
                tvItemTitle.text = course.originalName
                tvItemOverview.text = course.overview
                itemView.setOnClickListener {
                    val intent = Intent(itemView.context, DetailTvActivity::class.java)
                    intent.putExtra(DetailTvActivity.EXTRA_COURSE, course.id)
                    itemView.context.startActivity(intent)
                }
                imgShare.setOnClickListener { callback.onShareClick(course) }
                Glide.with(itemView.context)
                    .load(Constant.POSTER_BASE_URL+course.posterPath)
                    .apply(
                        RequestOptions.placeholderOf(R.drawable.ic_loading)
                            .error(R.drawable.ic_error)
                    )
                    .into(imgPoster)
            }
        }
    }
}