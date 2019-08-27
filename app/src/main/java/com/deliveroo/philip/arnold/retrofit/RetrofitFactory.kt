package com.deliveroo.philip.arnold.retrofit

import com.deliveroo.philip.arnold.mainscreen.MainActivityInterface
import com.deliveroo.philip.arnold.model.TopRatedData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitFactory {
    val repoBaseUrl = "https://api.themoviedb.org/3/movie/"
    val repoRetrofit by lazy {
        Retrofit.Builder()
            .baseUrl(repoBaseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
    val service by lazy {
        repoRetrofit.create(RetrofitService::class.java)
    }
    val searchRetrofit by lazy {
        Retrofit.Builder()
            .baseUrl(repoBaseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
    val serviceSearch by lazy {
        searchRetrofit.create(RetrofitService::class.java)
    }

    fun getSearchedItems(searched: String, responseHandler: MainActivityInterface) {
        CoroutineScope(Dispatchers.IO).launch {
            val call = serviceSearch.getSearch(searched)
            withContext(Dispatchers.Main) {
                loadMovies(call, responseHandler)
            }
        }
    }

    fun getTopPosts(responseHandler: MainActivityInterface) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val call = service.getTopRated()
                withContext(Dispatchers.Main) {
                    loadMovies(call, responseHandler)
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    responseHandler.ReturnError(e)
                }
            }
        }
    }

    fun loadMovies(call: Response<TopRatedData>, responseHandler: MainActivityInterface) {
        if (call.isSuccessful) {
            responseHandler.ReturnTopRated(call.body()!!)
        }
    }
}