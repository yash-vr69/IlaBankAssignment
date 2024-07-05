package com.example.interview.ui

import android.os.Bundle
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager2.widget.ViewPager2
import com.example.interview.R
import com.example.interview.databinding.ActivityMainBinding
import com.example.interview.ui.adapter.BannerListAdapter
import com.example.interview.ui.adapter.ImageCarouselAdapter
import com.example.interview.ui.dialog.StatisticsDialogFragment


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var imageCarouselAdapter: ImageCarouselAdapter

    private val bannerListAdapter: BannerListAdapter = BannerListAdapter()
    private val viewModel: MainActivityViewModel = MainActivityViewModel()

    private lateinit var indicators: List<ImageView>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupUi()
    }

    private fun setupUi() {
        setupRecyclerView()
        setupSearchView()
        setupObserver()
        setupFab()
    }

    private fun setupObserver() {
        viewModel.images.observe(this) {
            setupViewPager(it)
            setupIndicators(it.size)
        }
        viewModel.bannerList.observe(this) {
            bannerListAdapter.updateData(it)
        }
    }

    private fun setupIndicators(itemCount: Int) {
        indicators = List(itemCount) { _ ->
            val indicator = ImageView(this).apply {
                setImageResource(R.drawable.indicator_unselected)
            }
            val params = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            ).apply {
                setMargins(8, 8, 8, 8)
            }
            binding.indicatorContainer.addView(indicator, params)
            indicator
        }
        updateIndicators(0) // Set the first indicator as selected
    }

    private fun updateIndicators(position: Int) {
        indicators.forEachIndexed { index, imageView ->
            if (index == position) {
                imageView.setImageResource(R.drawable.indicator_selected)
            } else {
                imageView.setImageResource(R.drawable.indicator_unselected)
            }
        }
    }

    private fun setupViewPager(images: List<Int>) {

        imageCarouselAdapter = ImageCarouselAdapter(images)
        binding.viewPager.adapter = imageCarouselAdapter
        binding.viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageScrollStateChanged(state: Int) {}
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
            }

            override fun onPageSelected(position: Int) {
                updateIndicators(position)
                viewModel.getRecyclerViewList(position)
                binding.searchView.apply {
                    setQuery("", false)
                    clearFocus()
                }
            }
        })
    }

    private fun setupRecyclerView() {
        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = bannerListAdapter
        }
    }

    private fun setupSearchView() {
        with(binding.searchView) {
            setOnClickListener {
                isIconified = false
                requestFocus()
            }
            setOnQueryTextListener(object :
                androidx.appcompat.widget.SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?): Boolean {
                    return false
                }

                override fun onQueryTextChange(newText: String?): Boolean {
                    bannerListAdapter.filter(newText ?: "")
                    return true
                }
            })
        }
    }

    private fun setupFab() {
        binding.fab.setOnClickListener {
            val statisticDialog = StatisticsDialogFragment()
            statisticDialog.setCurrentDisplayedList(bannerListAdapter.getFilteredItems)
            statisticDialog.show(supportFragmentManager, null)
        }
    }
}
