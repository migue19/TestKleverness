package mx.com.nut.testcleverness

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import mx.com.nut.testcleverness.data.Service

class MainActivity : AppCompatActivity() {
    private lateinit var serviceHub: Service

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        serviceHub = Service()

        getHubs
    }



//hector@kleverness.com
    fun getHubs(){
        val token  = ""

        serviceHub.getHUB(token,{response->
            runOnUiThread {
                val code = response.data?.token
                val subscription = response.data?.user?.subscription
                Commons.saveStringPreference(this, "token", code!!)

                if (subscription == null) {
                    sendToView(SubscriptionActivity::class.java)

                } else {
                    val img = response.data?.user?.profile?.image
                    Commons.saveStringPreference(this, "imgTutor", img)
                    saveInfoInBD(code, response.data?.user!!)
                    sendToView(HomeActivity::class.java)
                }
            }
        },{error ->
            stopLoader()
            showError(error.message)
        },{exception->
            stopLoader()
            showError(exception)
        })
    }
}
