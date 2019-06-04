package mx.com.nut.testcleverness.data

import kotlinx.coroutines.*
import mx.com.nut.testcleverness.data.entity.ErrorModel
import mx.com.nut.testcleverness.data.entity.ResponseHub
import mx.com.nut.testcleverness.data.repository.ApiClient
import mx.com.nut.testcleverness.data.repository.ApiHub
import java.io.IOException
import kotlin.coroutines.CoroutineContext

class Service: CoroutineScope {
    private val contextJob = Job()
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + contextJob

    private val service = ApiClient.makeRetrofitService().create(ApiHub::class.java)


    fun getHUB(
        token: String,
        successHandler: (ResponseHub) -> Unit,
        failureHandler: (ErrorModel) -> Unit,
        exceptionHandler: (String) -> Unit
    ) {
        launch(dispatcher) {
            try {
                val request = service.getHub(token)
                val response = request.await()

                if (response.isSuccessful) {
                    val responseData = response.body()
                    val code: String? = responseData?.code
                    when (code) {
                        "200", "201" -> successHandler(responseData)
                    }
                } else {
                    //failureHandler(RestService.getErrorModel(response.errorBody()))
                }
            } catch (exception: IOException) {
                exceptionHandler(exception.localizedMessage)
            } catch (exception: Throwable) {
                exceptionHandler(exception.localizedMessage)
            }
        }
    }





}