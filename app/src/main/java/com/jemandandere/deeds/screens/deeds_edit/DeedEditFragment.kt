package com.jemandandere.deeds.screens.deeds_edit

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.jemandandere.deeds.App
import com.jemandandere.deeds.R
import com.jemandandere.deeds.databinding.DeedItemBinding
import com.jemandandere.deeds.databinding.FragmentDeedEditBinding
import com.jemandandere.deeds.model.Deed

class DeedEditFragment : Fragment() {

    private var _binding: FragmentDeedEditBinding? = null
    private val mBinding
        get() = _binding!!

    private lateinit var mViewModel: DeedEditViewModel

    private var mDeed: Deed? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDeedEditBinding.inflate(inflater, container, false)
        mDeed = arguments?.getSerializable("deed") as Deed?
        return mBinding.root
    }

    override fun onStart() {
        super.onStart()
        setHasOptionsMenu(true)
        mViewModel = ViewModelProvider(this).get(DeedEditViewModel::class.java)
        mBinding.deedText.setText(mDeed?.text)
    }

    override fun onPrepareOptionsMenu(menu: Menu) {
        if (mDeed == null) {
            menu.removeItem(R.id.menu_delete)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.edit_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                App.instance.navController.popBackStack()
            }
            R.id.menu_save -> {
                if (mDeed == null) {
                    mDeed = Deed(
                        text = mBinding.deedText.text.toString(),
                        timestamp = System.currentTimeMillis(),
                        done = false
                    )
                } else {
                    mDeed?.text = mBinding.deedText.text.toString()
                }
                mViewModel.save(mDeed!!) {
                    App.instance.navController.navigate(R.id.action_deedEditFragment_to_deedsListFragment)
                }
            }
            R.id.menu_delete -> mViewModel.delete(mDeed!!) {
                App.instance.navController.navigate(R.id.action_deedEditFragment_to_deedsListFragment)
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}