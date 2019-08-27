package com.deliveroo.philip.arnold.mainscreen

import com.deliveroo.philip.arnold.model.TopRatedData

interface MainActivityInterface {
    fun ReturnTopRated(topRated: TopRatedData)
    fun ReturnError(error: Throwable)
}