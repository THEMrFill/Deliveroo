package com.deliveroo.philip.arnold.retrofit

import com.deliveroo.philip.arnold.BuildConfig
import com.deliveroo.philip.arnold.model.TopRatedData
import io.reactivex.Flowable
import retrofit2.http.GET
import retrofit2.http.Query

interface RetrofitRxService {
    @GET("top_rated?")
    fun getUsers(
            @Query("api_key") api_key: String = BuildConfig.API_KEY
        ) : Flowable<TopRatedData>
    @GET("search?")
    fun getSearch(
            @Query("api_key") api_key: String = BuildConfig.API_KEY,
            @Query("query") queryString: String
        ) : Flowable<TopRatedData>
}