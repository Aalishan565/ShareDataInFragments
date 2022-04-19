package com.example.sharedatainfragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.sharedatainfragments.databinding.FragmentSecondBinding

class SecondFragment : Fragment() {

    private var _fragmentSecondBinding: FragmentSecondBinding? = null
    private val fragmentSecondBinding get() = _fragmentSecondBinding!!

    //This is the shared view model by which we will communicate
    private lateinit var sharedViewModel: SharedViewModel

    companion object {
        @JvmStatic
        fun newInstance() = SecondFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _fragmentSecondBinding = FragmentSecondBinding.inflate(inflater, container, false)
        return _fragmentSecondBinding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        /* Initializing the view model with activity reference so that we will have sharedview
        model available for both the fragment*/
        sharedViewModel = ViewModelProvider(requireActivity()).get(SharedViewModel::class.java)
        //Here we are observing the changes and showing in the text view of fragment
        sharedViewModel.msgLiveData.observe(viewLifecycleOwner) { message ->
            fragmentSecondBinding.tvMessage.text = message
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _fragmentSecondBinding = null
    }

}