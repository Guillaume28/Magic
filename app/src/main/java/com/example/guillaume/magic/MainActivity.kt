package com.example.guillaume.magic

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.widget.Toast
import com.example.guillaume.magic.services.api.MagicApiService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private var mDisposable: Disposable? = null
    private lateinit var mAdapter: MainAdapter
    private val magicApiService by lazy {
        MagicApiService.create()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        getCards()
        mAdapter = MainAdapter()
        recyclerView_main.layoutManager = LinearLayoutManager(this)
        recyclerView_main.adapter = mAdapter
    }

    private fun getCards() {
        mDisposable = magicApiService.allCards()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        { result -> mAdapter.addItems(result.cards).notifyDataSetChanged() },
                        { error -> Toast.makeText(applicationContext, error.message, Toast.LENGTH_LONG).show() }
                )
    }

    override fun onPause() {
        super.onPause()
        mDisposable?.dispose()
    }
}
