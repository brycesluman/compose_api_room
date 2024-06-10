package org.sluman.republic.data.network

import com.itkacher.okprofiler.BuildConfig
import com.localebro.okhttpprofiler.OkHttpProfilerInterceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitClient() {
    val retrofit: Retrofit by lazy {
        val builder = OkHttpClient.Builder()
        if (BuildConfig.DEBUG) { builder.addInterceptor(OkHttpProfilerInterceptor()) }
        val client = builder.build()
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
    companion object {
        private const val BASE_URL = "https://d49c3a78-a4f2-437d-bf72-569334dea17c.mock.pstmn.io/"
    }
}

class ApiClient() {
    val apiService: ApiService by lazy {
        RetrofitClient().retrofit.create(ApiService::class.java)
    }
}