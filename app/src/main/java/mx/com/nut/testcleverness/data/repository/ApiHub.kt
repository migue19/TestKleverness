package mx.com.nut.testcleverness.data.repository

import kotlinx.coroutines.Deferred
import mx.com.nut.testcleverness.data.entity.Response.ResponseHub
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header

interface ApiHub {

    @GET("Hub/54:10:EC:A7:60:16")
    fun getHub(@Header("Authorization") header: String): Deferred<Response<ResponseHub>>


}