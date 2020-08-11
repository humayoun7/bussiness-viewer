package com.humayoun.businessviewer.ui.main

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.DecelerateInterpolator
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.humayoun.businessviewer.R
import com.humayoun.businessviewer.model.Business
import com.yuyakaido.android.cardstackview.*
import kotlinx.android.synthetic.main.main_fragment.*

class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var viewModel: MainViewModel
    private var adapter: BusinessAdapter? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {

        return inflater.inflate(R.layout.main_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        init()
    }

    private fun init() {
        viewModel = ViewModelProvider(requireActivity()).get(MainViewModel::class.java)
        viewModel.businessSearchResult.observe(requireActivity(), Observer {
            Log.i("MainFragment", it.toString())
            if(adapter == null) {
                adapter = BusinessAdapter(requireActivity(), ArrayList<Business>(it.businesses))
                initStackView()
            }
        })

        btnBack.setOnClickListener{
            stackView.rewind()
        }

        btnNext.setOnClickListener{
            stackView.swipe()
        }
    }

    private fun initStackView() {
        val setting = RewindAnimationSetting.Builder()
            .setDirection(Direction.Right)
            .setDuration(Duration.Normal.duration)
            .setInterpolator(DecelerateInterpolator())
            .build()

        val cardStackLayoutManager = CardStackLayoutManager(requireContext())
        cardStackLayoutManager.setRewindAnimationSetting(setting)
        cardStackLayoutManager.setStackFrom(StackFrom.Top)
        cardStackLayoutManager.setSwipeableMethod(SwipeableMethod.Automatic)
        stackView.layoutManager = cardStackLayoutManager
        stackView.adapter = adapter
    }



}