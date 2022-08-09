package com.example.cryptoapp

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_coin_detail.*
import kotlinx.android.synthetic.main.item_coin_info.*
import kotlinx.android.synthetic.main.item_coin_info.tvPrice

class CoinDetailActivity : AppCompatActivity() {

    private lateinit var viewModel : CoinViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_coin_detail)
        if(!intent.hasExtra(EXTRA_FROM_SYMBOL)) {
            finish()
            return
        }
        val fromSymbol = intent.getStringExtra(EXTRA_FROM_SYMBOL).toString()

        viewModel = ViewModelProvider(this)[CoinViewModel::class.java]
        viewModel.getDetailInfo(fromSymbol).observe(this, Observer{
            Log.d("DETAIL_INFO", it.toString())
            tvPrice.text = it.price
            tvMaxPrice.text = it.highDay
            tvMinPrice.text = it.lowDay
            tvLastMarket.text = it.lastMarket
            tvLastDateUpdate.text = it.getFormattedTime()
            tvFromSymbol.text = it.fromSymbol
            tvToSymbol.text = it.toSymbol
            Picasso.get().load(it.getFullImageUrl()).into(ivLogoCoines)
        })
    
    
    }

    companion object{
       private const val EXTRA_FROM_SYMBOL = "fSym"

       fun newIntent(context: Context, fromSymbol: String): Intent {
           val intent = Intent(context, CoinDetailActivity::class.java)
           intent.putExtra(EXTRA_FROM_SYMBOL, fromSymbol)
           return intent
       }
    }
}