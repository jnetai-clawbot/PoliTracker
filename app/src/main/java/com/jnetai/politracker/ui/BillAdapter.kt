package com.jnetai.politracker.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.jnetai.politracker.databinding.ItemMainBinding
import com.jnetai.politracker.model.Bill

class BillAdapter(private val onClick: (Bill) -> Unit) : RecyclerView.Adapter<BillAdapter.VH>() {
    var items: List<Bill> = emptyList()
    inner class VH(val binding: ItemMainBinding) : RecyclerView.ViewHolder(binding.root)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = VH(ItemMainBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    override fun getItemCount() = items.size
    override fun onBindViewHolder(h: VH, pos: Int) {
        val item = items[pos]
        h.databinding.titleText.text = item.title
        h.databinding.subtitleText.text = item.billNumber + " · " + item.status
        h.databinding.root.setOnClickListener { onClick(item) }
    }
}