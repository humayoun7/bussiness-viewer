package com.humayoun.businessviewer.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.DecelerateInterpolator
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.humayoun.businessviewer.Injection
import com.humayoun.businessviewer.R
import com.humayoun.businessviewer.constant.Constants
import com.humayoun.businessviewer.model.Business
import com.humayoun.businessviewer.utils.hideKeyboard
import com.humayoun.businessviewer.utils.onSearch
import com.yuyakaido.android.cardstackview.*
import kotlinx.android.synthetic.main.main_fragment.*


/**
 * Handling the main view of the app, this can be renamed later if more fragments are used
 * */

class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var viewModel: MainViewModel
    private var adapter: BusinessAdapter? = null
    private lateinit var cardStackLayoutManager: CardStackLayoutManager

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.main_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        init()
    }

    private fun init() {
        viewModel = ViewModelProvider(requireActivity(), Injection.provideViewModelFactory()).get(MainViewModel::class.java)
        addObservers()

        btnBack.setOnClickListener{
            stackView.rewind()
        }

        btnNext.setOnClickListener{
            stackView.swipe()
        }

        btnSearch.setOnClickListener {
            search(false)
            requireActivity().hideKeyboard()
        }

        etSearch.onSearch {
            search(false)
            requireActivity().hideKeyboard()
        }
    }

    private fun search(useCurrentSearchTerm: Boolean) {
        // make sure we have location
        if(viewModel.location.value == null) {
            Toast.makeText(requireContext(), getString(R.string.location_error), Toast.LENGTH_LONG).show()
            return
        }

        // make sure we hae search term
        var searchFor = etSearch.text.toString()
        if(!useCurrentSearchTerm && searchFor.isEmpty()) {
            Toast.makeText(requireContext(), getString(R.string.error_search_term), Toast.LENGTH_LONG).show()
            return
        } else if (useCurrentSearchTerm) {
            searchFor = viewModel.currentlySearching?.value ?: Constants.YelpService.DEFAULT_SEARCH_TERM
        }

        // if new search term reset the adapter and pageoffset
        if(viewModel.currentlySearching?.value != searchFor) {
            adapter = null
            viewModel.pageOffset = 0
        }

        viewModel.searchForBusinesses(viewModel.location?.value, searchFor)
    }

    private fun addObservers() {
        // observing for incoming search results
        viewModel.businessSearchResult.observe(requireActivity(), Observer {
            if(adapter == null) {
                adapter = BusinessAdapter(requireActivity(), ArrayList<Business>(it.businesses))
                initStackView()
            } else {
                adapter?.addItems(it.businesses)
            }
            viewModel.pageOffset += it.businesses.size
            viewModel.fetchingBusinessData.value = false
        })

        // observing to show progress bar
        viewModel.fetchingBusinessData.observe(requireActivity(), Observer {
            progressBar.visibility = if(it) View.VISIBLE else View.GONE
        })

        // observing for location
        viewModel.location.observe(requireActivity(), Observer {
            search(true)
        })
    }


    private fun initStackView() {
        val setting = RewindAnimationSetting.Builder()
            .setDirection(Direction.Right)
            .setDuration(Duration.Normal.duration)
            .setInterpolator(DecelerateInterpolator())
            .build()

        cardStackLayoutManager = CardStackLayoutManager(requireContext())
        cardStackLayoutManager.setRewindAnimationSetting(setting)
        cardStackLayoutManager.setStackFrom(StackFrom.Top)
        cardStackLayoutManager.setSwipeableMethod(SwipeableMethod.Automatic)

        stackView.layoutManager = cardStackLayoutManager
        stackView.adapter = adapter
        addScrollListener()
    }

    private fun addScrollListener () {
        val scroll = object: RecyclerView.OnScrollListener() {

            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                val itemRemaining = cardStackLayoutManager.itemCount - cardStackLayoutManager.topPosition
                if (newState == RecyclerView.SCROLL_STATE_IDLE && itemRemaining == Constants.YelpService.RELOAD_WHEN_REMAINING_COUNT) {
                    search(true)
                }
            }
        }

        stackView.addOnScrollListener(scroll)
    }
}