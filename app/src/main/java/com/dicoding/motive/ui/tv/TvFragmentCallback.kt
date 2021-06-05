package com.dicoding.motive.ui.tv

import com.dicoding.motive.data.source.local.entity.TvEntity

interface TvFragmentCallback {
    fun onShareClick(detailTv: TvEntity)
}

