package com.mcebotari.moviesapp.view.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.endava.encarsharing.android.ui.base.BaseFragment
import com.mcebotari.moviesapp.data.model.MovieListResponse
import com.mcebotari.moviesapp.databinding.FragmentListBinding
import com.mcebotari.moviesapp.view.list.adapter.ListAdapter
import com.mcebotari.moviesapp.view.main.MainViewModel
import com.softrunapps.paginatedrecyclerview.PaginatedAdapter
import kotlinx.android.synthetic.main.fragment_list.*

private const val API_PAGE_SIZE = 20

abstract class ListFragment : BaseFragment(), PaginatedAdapter.OnPaginationListener {

    private lateinit var binding: FragmentListBinding
    private lateinit var adapter: ListAdapter

    protected val viewModel by provide<ListViewModel>()
    protected val activityViewModel by provideFromActivity<MainViewModel>()

    abstract fun fetchNextPage()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentListBinding.inflate(layoutInflater)
        binding.viewModel = viewModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupAdapter()
        viewModel.getMoviesListLiveData().observe(viewLifecycleOwner, Observer { updateList(it) })
        viewModel.getOpenDetailsActionLiveData().observe(viewLifecycleOwner, Observer { openDetailsFor(it) })
        fetchNextPage()
    }

    private fun openDetailsFor(movieId: Int) {
        activityViewModel.navigateToDetails(movieId)
    }

    private fun setupAdapter() {
        adapter = ListAdapter(viewModel)
        adapter.recyclerView = list_recycler
        adapter.setPageSize(API_PAGE_SIZE)
        adapter.setOnPaginationListener(this)
    }

    override fun onFinish() {
        //todo show no more elements
    }

    override fun onCurrentPage(page: Int) {
        // noop
    }

    override fun onNextPage(page: Int) {
        list_progress_bar.visibility = View.VISIBLE
        viewModel.fetchPopularMoviesNextPage()
    }

    private fun updateList(it: MovieListResponse) {
        list_progress_bar.visibility = View.GONE
        adapter.submitItems(it.results)
    }
}

class PopularMoviesFragment : ListFragment() {
    override fun fetchNextPage() {
        viewModel.fetchPopularMoviesNextPage()
    }
}

class TopRatedMoviesFragment : ListFragment() {
    override fun fetchNextPage() {
        viewModel.fetchTopRatedMoviesNextPage()
    }
}