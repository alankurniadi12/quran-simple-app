package com.alankurniadi.myquran.page

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import androidx.viewpager2.widget.ViewPager2
import com.alankurniadi.myquran.MainViewModel
import com.alankurniadi.myquran.R
import com.alankurniadi.myquran.api.DataItem
import com.alankurniadi.myquran.connection.CheckInternetAccess
import com.alankurniadi.myquran.connection.ConnectionStateFragment
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class PageSurahActivity : AppCompatActivity() {

    private lateinit var sectionsPagerAdapter: SectionsPagerAdapter
    private lateinit var viewPager: ViewPager2
    private val mainViewModel: MainViewModel by viewModels()
    private val frmConnecContent: View by lazy { findViewById(R.id.frame_connection_content) }
    companion object{
        const val EXTRA_NO_PAGE = "extra_page"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_page_surah)

        val noSurah = intent.getIntExtra(EXTRA_NO_PAGE, 0)

        sectionsPagerAdapter = SectionsPagerAdapter(this)
        viewPager = findViewById(R.id.view_pager)
        
        val checkNett = CheckInternetAccess(this)
        if (checkNett.checkInternetAccess()){
            frmConnecContent.visibility = View.GONE
            mainViewModel.listSurah.observe(this,{
                sectionsPagerAdapter.setData(it)
                viewPager.adapter = sectionsPagerAdapter
                viewPager.setCurrentItem(noSurah -1, true)
                val tabLayout: TabLayout = findViewById(R.id.tab_layout)
                TabLayoutMediator(tabLayout, viewPager) {tab, position ->
                    tab.text = "${position + 1} ${it[position].name.transliteration.id}"
                }.attach()
            })
        }else {
            val mConnectionStateFragment = ConnectionStateFragment()
            val mFragmentManager = supportFragmentManager
            val fragment = mFragmentManager.findFragmentByTag(ConnectionStateFragment::class.java.simpleName)
            if (fragment !is ConnectionStateFragment) {
                mFragmentManager.beginTransaction().apply {
                    add(R.id.frame_connection_content, mConnectionStateFragment, ConnectionStateFragment::class.java.simpleName)
                    commit()
                }
            }

        }
    }
}