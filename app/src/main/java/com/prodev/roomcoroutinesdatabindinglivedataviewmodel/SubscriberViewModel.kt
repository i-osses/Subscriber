package com.prodev.roomcoroutinesdatabindinglivedataviewmodel

import androidx.databinding.Bindable
import androidx.databinding.Observable
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.prodev.roomcoroutinesdatabindinglivedataviewmodel.db.Subscriber
import com.prodev.roomcoroutinesdatabindinglivedataviewmodel.db.SubscriberRepository
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class SubscriberViewModel(private val repository: SubscriberRepository) : ViewModel(),Observable {

    //repository instance in the constructor to access the functions from Repository
    // We get the load from repository
    val subscribers = repository.subscribers

    /*
    to use @Bindable text in the ViewModel , this ViewModel Class should implement the Observer Interface. It should be
    (androidx.databinding)
    add ,Observable after "class SubscriberViewModel(private val repository: SubscriberRepository) : ViewModel()"

    */

    @Bindable
    val inputName = MutableLiveData<String>()

    @Bindable
    val inputEmail = MutableLiveData<String>()

    @Bindable
    val saveOrUpdateButtonText = MutableLiveData<String>()

    @Bindable
    val clearAllOrDeleteButtonText = MutableLiveData<String>()

    init {
        saveOrUpdateButtonText.value = "Save"
        clearAllOrDeleteButtonText.value = "Clear All"
    }

    fun saveOrUpdate() {

        val name = inputName.value!!
        val email = inputEmail.value!!
        inster(Subscriber(0,name,email))

        inputName.value = null
        inputEmail.value = null


    }

    fun clearOrDelete() {
        clearAll()

    }


    fun inster(subscriber:Subscriber) : Job = viewModelScope.launch {
            repository.insert(subscriber)
        }
    fun update(subscriber: Subscriber) : Job = viewModelScope.launch {
            repository.update(subscriber)
    }
    fun delete(subscriber: Subscriber) : Job = viewModelScope.launch {
            repository.delete(subscriber)
    }
    fun clearAll () : Job = viewModelScope.launch {
            repository.deleteAll()
    }

    override fun addOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {

    }

    override fun removeOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {

    }


}