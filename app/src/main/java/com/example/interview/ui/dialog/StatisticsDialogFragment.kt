package com.example.interview.ui.dialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.example.interview.data.BannerContent
import com.example.interview.databinding.DialogStatisticsBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class StatisticsDialogFragment : BottomSheetDialogFragment() {

    private lateinit var binding: DialogStatisticsBinding
    private val currentDisplayedList: ArrayList<BannerContent> = ArrayList()
    private val viewModel = StatisticsViewModel()

    fun setCurrentDisplayedList(list: ArrayList<BannerContent>) {
        this.currentDisplayedList.addAll(list)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = DialogStatisticsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getStatistics(currentDisplayedList)
        setUpObserver()
    }

    private fun setUpObserver(){
        viewModel.statsList.observe(this) {
            if(it?.isNotEmpty() == true){
                binding.tvListSizeData.text = "${currentDisplayedList.size}"
                binding.firstElement.text = it[0]
                binding.secondElement.text = it[1]
                binding.thirdElement.text = it[2]
            } else{
                binding.tvListSizeData.text = "0"
                binding.firstElement.text = "NA"
                binding.secondElement.text = "NA"
                binding.thirdElement.text = "NA"
            }
        }
    }
}