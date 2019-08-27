package com.deliveroo.philip.arnold.retrofit

import android.annotation.SuppressLint
import com.deliveroo.philip.arnold.mainscreen.MainActivityInterface
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import java.io.IOException

object RetrofitRxFactory {
    val repoBaseUrl = "https://api.themoviedb.org/3/movie/"
    val repoRetrofit by lazy {
        Retrofit.Builder()
            .baseUrl(repoBaseUrl)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val service by lazy {
        repoRetrofit.create(RetrofitRxService::class.java)
    }
    var disposable: Disposable? = null

    @SuppressLint("CheckResult")
    @Throws(IOException::class)
    fun getTopRated(responseHandler: MainActivityInterface) {
        service.getUsers()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe(responseHandler::ReturnTopRated, responseHandler::ReturnError)
    }
}