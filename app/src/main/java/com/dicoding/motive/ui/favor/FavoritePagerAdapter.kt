@file:Suppress("DEPRECATION")

package com.dicoding.motive.ui.favor

import android.content.Context
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

import com.dicoding.motive.R
import com.dicoding.motive.ui.favor.m.MovieFavoriteFragment
import com.dicoding.motive.ui.favor.t.TvFavoriteFragment


class FavoritePagerAdapter(private val mContext: Context, fm: FragmentManager) : FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    companion object {
        @StringRes
        private val TAB_TITLES = intArrayOf(R.string.movie_fav, R.string.tv_fav)
    }

    override fun getItem(position: Int): Fragment =
            when (position) {
                0 -> MovieFavoriteFragment()
                1 -> TvFavoriteFragment()
                else -> Fragment()
            }

    override fun getPageTitle(position: Int): CharSequence = mContext.resources.getString(TAB_TITLES[position])

    override fun getCount(): Int = 2

}