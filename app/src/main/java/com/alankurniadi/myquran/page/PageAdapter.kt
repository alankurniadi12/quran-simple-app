package com.alankurniadi.myquran.page

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.alankurniadi.myquran.R
import com.alankurniadi.myquran.api.VersesItem
import com.alankurniadi.myquran.databinding.ItemAyatBinding

class PageAdapter : RecyclerView.Adapter<PageAdapter.PageViewHolder>() {

    private val mData = ArrayList<VersesItem>()

    @SuppressLint("NotifyDataSetChanged")
    fun setData(items: ArrayList<VersesItem>) {
        mData.clear()
        mData.addAll(items)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PageViewHolder {
        val mView = LayoutInflater.from(parent.context).inflate(R.layout.item_ayat, parent, false)
        return PageViewHolder(mView)
    }

    override fun onBindViewHolder(holder: PageViewHolder, position: Int) {
        holder.bind(mData[position])
    }

    override fun getItemCount(): Int = mData.size

    inner class PageViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = ItemAyatBinding.bind(itemView)
        fun bind(versesItem: VersesItem) {
            binding.nomorAyat.text = versesItem.number.inSurah.toString()
            binding.ayat.text = versesItem.text.arab
            binding.ejaan.text = versesItem.text.transliteration.en
            binding.terjemahan.text = versesItem.translation.id
        }
    }
}