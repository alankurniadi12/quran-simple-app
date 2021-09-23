package com.alankurniadi.myquran.page

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.alankurniadi.myquran.api.DataItem

class SectionsPagerAdapter(activity: AppCompatActivity) : FragmentStateAdapter(activity) {

    private val mData = ArrayList<DataItem>()

    @SuppressLint("NotifyDataSetChanged")
    fun setData(items: ArrayList<DataItem>) {
        mData.clear()
        mData.addAll(items)
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = mData.size

    override fun createFragment(position: Int): Fragment {
        return ContentSurahFragment.newInstance(mData[position].number)
    }

}