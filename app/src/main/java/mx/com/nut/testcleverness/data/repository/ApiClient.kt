package mx.com.nut.testcleverness.data.repository

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

object ApiClient {

    const  val BASE_URL = "http://d001.kleverness.com/webapi/"

    fun makeRetrofitService(): Retrofit {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        val client =  OkHttpClient.Builder().
            connectTimeout(60L, TimeUnit.SECONDS).
            readTimeout(60L, TimeUnit.SECONDS).
            callTimeout(60L, TimeUnit.SECONDS).
            addInterceptor(interceptor).build()

        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .client(client)
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .build()
        //.create(ApiDirectory::class.java)
    }
}