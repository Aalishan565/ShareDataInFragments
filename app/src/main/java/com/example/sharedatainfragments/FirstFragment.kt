package com.example.sharedatainfragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.example.sharedatainfragments.databinding.FragmentFirstBinding

class FirstFragment : Fragment() {

    private var _fragmentFirstBinding: FragmentFirstBinding? = null
    private val fragmentFirstBinding get() = _fragmentFirstBinding!!

    //This is the shared view model by which we will communicate
    private lateinit var sharedViewModel: SharedViewModel

    companion object {
        @JvmStatic
        fun newInstance() = FirstFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _fragmentFirstBinding = FragmentFirstBinding.inflate(inflater, container, false)
        return _fragmentFirstBinding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        /* Initializing the view model with activity reference so that we will have sharedview
         model available for both the fragment*/
        sharedViewModel = ViewModelProvider(requireActivity()).get(SharedViewModel::class.java)

        fragmentFirstBinding.btnSendMessage.setOnClickListener {
            //Sending the data to the view Model
            val message = fragmentFirstBinding.etMessage.text.toString()
            sharedViewModel.sendMessage(message)
            Navigation.findNavController(fragmentFirstBinding.root)
                .navigate(R.id.action_firstFragment_to_secondFragment)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _fragmentFirstBinding = null
    }

}