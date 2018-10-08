package com.example.guillaume.stones

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.widget.Toast
import com.example.guillaume.stones.api.MagicApiService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private var mDisposable: Disposable? = null
    private lateinit var mAdapter: MainAdapter
    private val mMagicApiService by lazy {
        MagicApiService.create()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        getCards()
        mAdapter = MainAdapter()
        recyclerView_main.layoutManager = LinearLayoutManager(this)
        recyclerView_main.adapter = mAdapter
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
    }

    private fun getCards() {
        mDisposable = mMagicApiService.allCards()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        { result -> mAdapter.addItems(result.cards).notifyDataSetChanged() },
                        { error -> Toast.makeText(applicationContext, error.message, Toast.LENGTH_LONG).show() }
                )
    }

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_home -> {
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_dashboard -> {
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_notifications -> {
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    override fun onPause() {
        super.onPause()
        mDisposable?.dispose()
    }
}
