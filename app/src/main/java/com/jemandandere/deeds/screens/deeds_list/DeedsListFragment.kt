package com.jemandandere.deeds.screens.deeds_list

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.jemandandere.deeds.App
import com.jemandandere.deeds.R
import com.jemandandere.deeds.databinding.ActivityMainBinding
import com.jemandandere.deeds.databinding.FragmentDeedsListBinding
import com.jemandandere.deeds.model.Deed

class DeedsListFragment : Fragment() {

    private var _binding: FragmentDeedsListBinding? = null
    private val mBinding
        get() = _binding!!

    private lateinit var mViewModel: DeedsListViewModel

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: DeedsListAdapter
    private lateinit var observer: Observer<List<Deed>>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDeedsListBinding.inflate(inflater, container, false)
        return mBinding.root
    }

    override fun onStart() {
        super.onStart()
        recyclerView = mBinding.recyclerViewDeeds
        recyclerView.layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        recyclerView.addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
        adapter = DeedsListAdapter()
        recyclerView.adapter = adapter
        observer = Observer {
            adapter.setList(it)
        }
        mViewModel = ViewModelProvider(this).get(DeedsListViewModel::class.java)
        mViewModel.deeds.observe(this, observer)
        mBinding.floatingActionButton.setOnClickListener {
            App.instance.navController.navigate(R.id.action_deedsListFragment_to_deedEditFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        recyclerView.adapter = null
        mViewModel.deeds.removeObserver(observer)
    }
}