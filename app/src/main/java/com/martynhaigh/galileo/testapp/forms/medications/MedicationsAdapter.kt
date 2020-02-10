package com.martynhaigh.galileo.testapp.forms.medications

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.martynhaigh.galileo.testapp.R
import com.martynhaigh.galileo.testapp.domain.form.entity.MedicationEntity

class MedicationsAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var itemList: List<MedicationEntity> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun getItemCount(): Int {
        return itemList.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.item_medication, parent, false)
        return ViewHolder(view)
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var item: TextView = itemView.findViewById(R.id.row_item)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = (holder as ViewHolder).item
        item.text = itemList[position].name
    }
}
