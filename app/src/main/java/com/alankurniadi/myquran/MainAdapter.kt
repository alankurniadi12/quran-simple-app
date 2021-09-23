package com.alankurniadi.myquran

import android.annotation.SuppressLint
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.RecyclerView
import com.alankurniadi.myquran.api.DataItem
import com.alankurniadi.myquran.databinding.ItemSuratBinding
import com.alankurniadi.myquran.page.PageSurahActivity
import java.util.*
import kotlin.collections.ArrayList

class MainAdapter(
    private val activity: MainActivity,
    private var dataItem: ArrayList<DataItem>
) : RecyclerView.Adapter<MainAdapter.MainViewHolder>(), Filterable {

    private var mData = ArrayList<DataItem>()

    init {
        mData = dataItem
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainAdapter.MainViewHolder {
        val mView = LayoutInflater.from(parent.context).inflate(R.layout.item_surat, parent, false)
        return MainViewHolder(mView)
    }

    override fun onBindViewHolder(holder: MainAdapter.MainViewHolder, position: Int) {
        holder.bind(mData[position])
    }

    override fun getItemCount(): Int = mData.size

    inner class MainViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
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

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val charSearch = constraint.toString()
                mData = if (charSearch.isEmpty()) {
                    dataItem
                } else {
                    val resultList = ArrayList<DataItem>()
                    for (row in dataItem) {
                        if (row.name.transliteration.id.lowercase(Locale.ROOT)
                                .contains(charSearch.lowercase(Locale.ROOT))
                        ) {
                            resultList.add(row)
                        }
                    }
                    resultList
                }
                val filterResult = FilterResults()
                filterResult.values = mData

                return filterResult
            }

            @SuppressLint("NotifyDataSetChanged")
            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                (results?.values as ArrayList<DataItem>).also {
                    mData = it
                }
                notifyDataSetChanged()
            }
        }
    }
}