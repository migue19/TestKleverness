package mx.com.nut.testcleverness.presentation.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import mx.com.nut.testcleverness.R
import mx.com.nut.testcleverness.data.Service
import mx.com.nut.testcleverness.data.entity.Response.DeviceModel
import mx.com.nut.testcleverness.presentation.adapter.DevicesAdapter

class MainActivity : AppCompatActivity(),DevicesAdapter.OnItemClickListener {
    private lateinit var serviceHub: Service
    lateinit var adapter: DevicesAdapter



    override fun onItemClick(select: DeviceModel) {

    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        serviceHub = Service()
        adapter = DevicesAdapter(this,this)
        rvDevices.layoutManager = GridLayoutManager(this,3)

        rvDevices.adapter = adapter

        getHubs()
    }



//hector@kleverness.com
    fun getHubs(){
        val token  = getString(R.string.token)

        serviceHub.getHUB(token,{response->
            runOnUiThread {
                val device = response.hub?.device
                printDevice(device)
            }
        },{error ->

        },{exception->

        })
    }



    fun printDevice(devices: List<DeviceModel>?){
        adapter.setElement(devices)

    }



}
