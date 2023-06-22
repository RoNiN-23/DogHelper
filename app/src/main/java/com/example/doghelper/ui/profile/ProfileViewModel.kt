package com.example.doghelper.ui.profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ProfileViewModel : ViewModel() {
    private val _text = MutableLiveData<String>().apply {
        value = "This is PROFILE Fragment"
    }
    val text: LiveData<String> = _text
}