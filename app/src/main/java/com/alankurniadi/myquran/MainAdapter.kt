package com.alankurniadi.myquran

import android.annotation.SuppressLint
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.alankurniadi.myquran.api.DataItem
import com.alankurniadi.myquran.databinding.ItemSuratBinding
import com.alankurniadi.myquran.page.PageSurahActivity

class MainAdapter(private val activity: MainActivity): RecyclerView.Adapter<MainAdapter.MainViewHolder>() {
    companion object {
        private const val TAG = "MainAdapter"
    }

    private val mData = ArrayList<DataItem>()
    @SuppressLint("NotifyDataSetChanged")
    fun setData(items: ArrayList<DataItem>) {
        mData.clear()
        mData.addAll(items)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainAdapter.MainViewHolder {
        val mView = LayoutInflater.from(parent.context).inflate(R.layout.item_surat, parent, false)
        return MainViewHolder(mView)

    }

    override fun onBindViewHolder(holder: MainAdapter.MainViewHolder, position: Int) {
        holder.bind(mData[position])
    }

    override fun getItemCount(): Int = mData.size

    inner class MainViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        private val binding = ItemSuratBinding.bind(itemView)
        fun bind(dataItem: DataItem) {
            binding.nomorSurah.text = dataItem.number.toString()
            binding.namaSurah.text = dataItem.name.transliteration.id
            binding.lafazNamaSurah.text = dataItem.name.jsonMemberShort
            binding.tempatTurun.text = dataItem.revelation.id
            binding.jumlahAyat.text = dataItem.numberOfVerses.toString()

            binding.cvItem.setOnClickListener {
                val intent = Intent(activity, PageSurahActivity::class.java)
                intent.putExtra(PageSurahActivity.EXTRA_NO_PAGE, dataItem.number)
                activity.startActivity(intent)
            }
        }
    }
}