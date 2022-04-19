package com.example.sharedatainfragments

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SharedViewModel() : ViewModel() {

    //This is the mutable live data in which we are putting the data
    private val msgMLD = MutableLiveData<String>()

    //This is msg live data by which we will observe the data in other fragment
    val msgLiveData get() = msgMLD

    //This is the method which will be called by fragment 1 and will put data in mutalbe live data
    fun sendMessage(message: String) {
        msgMLD.value = message
    }

}