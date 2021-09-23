package com.alankurniadi.myquran.page

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.alankurniadi.myquran.databinding.FragmentContentSurahBinding

class ContentSurahFragment : Fragment() {

    private var _binding: FragmentContentSurahBinding? = null
    private val binding get() = _binding!!
    private lateinit var pageAdapter: PageAdapter
    private lateinit var pageViewModel: PageViewModel

    companion object {
        private const val EXTRA_DATA = "extra_data"
        private const val TAG = "ContentSurahFragment"

        fun newInstance(index: Int) =
            ContentSurahFragment().apply {
                arguments = Bundle().apply {
                    putInt(EXTRA_DATA, index)
                }
            }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentContentSurahBinding.inflate(inflater, container, false)
        return binding.root
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        pageAdapter = PageAdapter()
        binding.rvItemAyat.layoutManager = LinearLayoutManager(context)
        binding.rvItemAyat.adapter = pageAdapter

        pageViewModel = ViewModelProvider(this).get(PageViewModel::class.java)
        val index = arguments?.getInt(EXTRA_DATA, 0)
        if (index != null) {
            pageViewModel.findListAyat(index)
        }
        pageViewModel.listItemAyat.observe(viewLifecycleOwner, { dataItem ->
            binding.progressBarPage.visibility = View.GONE
            pageAdapter.setData(dataItem)
        })
        pageViewModel.dataSurah.observe(viewLifecycleOwner, {
            binding.tvJuz.text = "Juz " + it.data.verses[1].meta.juz.toString()
            if (it.data.preBismillah != null) {
                binding.tvBissmillah.visibility = View.VISIBLE
                binding.tvBissmillah.text = it.data.preBismillah.text.arab
            } else {
                Log.d(TAG, "Bissmillah " + it.data.preBismillah.toString())
            }
            binding.tvJumlahAyat.text = it.data.numberOfVerses.toString() + " Ayat"
            if (it.data.name.transliteration.id.isNotEmpty()) {
                binding.namaSurahInPage.visibility = View.VISIBLE
                binding.namaSurahInPage.text = it.data.name.transliteration.id
            }

        })
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}

