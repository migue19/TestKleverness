package mx.com.nut.testcleverness.presentation.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.device_list_item.view.*
import mx.com.nut.testcleverness.R
import mx.com.nut.testcleverness.data.entity.Response.DeviceModel

class DevicesAdapter(val context: Context, val onItemClickListener: OnItemClickListener): RecyclerView.Adapter<DevicesAdapter.DevicesViewHolder>()  {
    var select = 0
    var items = ArrayList<DeviceModel>()

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): DevicesViewHolder {
        return DevicesViewHolder(LayoutInflater.from(context).inflate(R.layout.device_list_item,p0,false))
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: DevicesViewHolder, pos: Int) {
        holder.nameDevice.text = items[pos].aliasDevice

    }


    fun setElement(items: List<DeviceModel>?){
        this.select = 0
        this.items.clear()
        this.items.addAll(items!!)
        notifyDataSetChanged()
    }

    inner class DevicesViewHolder(view: View): RecyclerView.ViewHolder(view){
        val nameDevice = view.txtNameDevice
    }

    interface OnItemClickListener{
        fun onItemClick(select: DeviceModel)
    }

}