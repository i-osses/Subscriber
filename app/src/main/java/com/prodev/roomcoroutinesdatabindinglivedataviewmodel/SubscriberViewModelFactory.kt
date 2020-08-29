package com.prodev.roomcoroutinesdatabindinglivedataviewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.prodev.roomcoroutinesdatabindinglivedataviewmodel.db.SubscriberRepository
import java.lang.IllegalArgumentException

// boilerplate code, just change ViewModel name and constructor parameter Class
class SubscriberViewModelFactory(private val repository: SubscriberRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(SubscriberViewModel::class.java)){
                return SubscriberViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown View Model Class")
    }
}