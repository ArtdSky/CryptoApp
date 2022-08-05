package com.example.cryptoapp.api

import retrofit2.http.GET
import com.example.cryptoapp.pojo.CoinInfoListOfData
import com.example.cryptoapp.pojo.CoinPriceInfoRawData
import io.reactivex.Single
import retrofit2.http.Query
import com.example.cryptoapp.utils.apiKey

interface ApiService {

    @GET("top/totalvolfull")
    fun getTopCoinsInfo(
        @Query(QUERY_PARAM_API_KEY) apiKey: String = ApiKey,
        @Query(QUERY_PARAM_LIMIT) limit: Int = 10,
        @Query(QUERY_PARAM_TO_SYMBOL) tSym: String = CURRENCY
        ): Single<CoinInfoListOfData>

    @GET("pricemultifull")
    fun getFullPriceList(
        @Query(QUERY_PARAM_API_KEY) apiKey: String = ApiKey,
        @Query(QUERY_PARAM_FROM_SYMBOLS) fSyms: String,
        @Query(QUERY_PARAM_TO_SYMBOLS) tSyms: String = CURRENCY
    ): Single<CoinPriceInfoRawData>

    companion object {
    private const val QUERY_PARAM_API_KEY = "api_key"
    private const val QUERY_PARAM_LIMIT = "limit"
    private const val QUERY_PARAM_TO_SYMBOL = "tsym"
    private const val QUERY_PARAM_TO_SYMBOLS = "tsyms"
    private const val QUERY_PARAM_FROM_SYMBOLS = "fsyms"

    private const val ApiKey = apiKey
    private const val CURRENCY = "USD"
    }

}