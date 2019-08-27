package com.deliveroo.philip.arnold.retrofit

import com.deliveroo.philip.arnold.BuildConfig
import com.deliveroo.philip.arnold.model.TopRatedData
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface RetrofitService {
    @GET("top_rated?")
    suspend fun getTopRated(
        @Query("api_key") api_key: String = BuildConfig.API_KEY
    ) : Response<TopRatedData>
    @GET("search?")
    suspend fun getSearch(
        @Query("query") queryString: String,
        @Query("api_key") api_key: String = BuildConfig.API_KEY
    ) : Response<TopRatedData>
}