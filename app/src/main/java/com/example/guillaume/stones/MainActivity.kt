package com.example.guillaume.stones

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import com.example.guillaume.stones.api.MagicApiService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private var disposable: Disposable? = null
    private lateinit var mAdapter: MainAdapter
    private val magicApiService by lazy {
        MagicApiService.create()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        getNewReleases()
        mAdapter = MainAdapter()
        recyclerView_main.layoutManager = LinearLayoutManager(this)
        recyclerView_main.adapter = mAdapter
    }

    private fun getNewReleases() {
        disposable = magicApiService.allCards()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        { result -> mAdapter.addItems(result.cards).notifyDataSetChanged() },
                        { error -> Log.d("ERROREUH", error.message) }
                )
    }

    override fun onPause() {
        super.onPause()
        disposable?.dispose()
    }
}
