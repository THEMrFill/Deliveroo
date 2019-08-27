package com.deliveroo.philip.arnold.mainscreen

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.deliveroo.philip.arnold.R
import com.deliveroo.philip.arnold.adapter.RecyclerAdapter
import com.deliveroo.philip.arnold.model.TopRatedData
import com.deliveroo.philip.arnold.retrofit.RetrofitFactory
import com.deliveroo.philip.arnold.retrofit.RetrofitRxFactory
import io.reactivex.disposables.CompositeDisposable
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), MainActivityInterface {
//    private var mCompositeDisposable: CompositeDisposable? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recycler.layoutManager = LinearLayoutManager(this)
        recycler.adapter = RecyclerAdapter(TopRatedData())

        rx.setOnClickListener { getDataFromRx() }
        coroutine.setOnClickListener { getDataFromCoroutine() }

        searchButton.setOnClickListener { searchForMovie() }

        getDataFromCoroutine()
    }

    private fun getDataFromCoroutine() {
        progress_circular.show()
        ReturnTopRated(TopRatedData())
        RetrofitFactory.getTopPosts(this)
    }

    private fun getDataFromRx() {
        progress_circular.show()
        ReturnTopRated(TopRatedData())
//        mCompositeDisposable = CompositeDisposable()
        RetrofitRxFactory.getTopRated(this)
    }

    fun searchForMovie() {
        if (editText.text.length > 0) {
            RetrofitFactory.getSearchedItems(editText.text.toString(), this)
        }
    }

    override fun ReturnTopRated(topRated: TopRatedData) {
        progress_circular.hide()
        (recycler.adapter as RecyclerAdapter).LoadData(topRated)
    }

    override fun ReturnError(error: Throwable) {
        progress_circular.hide()
        Toast.makeText(this, "error: " + error.localizedMessage, Toast.LENGTH_LONG).show()
    }
}
