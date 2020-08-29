package com.prodev.roomcoroutinesdatabindinglivedataviewmodel

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.prodev.roomcoroutinesdatabindinglivedataviewmodel.databinding.ActivityMainBinding
import com.prodev.roomcoroutinesdatabindinglivedataviewmodel.db.SubscriberDatabase
import com.prodev.roomcoroutinesdatabindinglivedataviewmodel.db.SubscriberRepository

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding    // To get the data binding object
    private lateinit var subscriberViewModel: SubscriberViewModel //****defines a reference variable for a SubscriberViewModel instance

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // setContentView(R.layout.activity_main) //Because it´s using binding
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        // to create a ViewModelFactory instance, we need to pass a DAO as an argument
        val dao = SubscriberDatabase.getInstance(application).subscriberDAO
        //going to it repository using the DAO instance
        val repository = SubscriberRepository(dao)
        //Create the ViewModelFactory instance using the above repository instance
        val factory = SubscriberViewModelFactory(repository)
        // **** now we can write codes to get the ViewModel instance
        subscriberViewModel = ViewModelProvider(this, factory).get(SubscriberViewModel::class.java)
        // Assign the ViewModel instance to the DataBinding object
        binding.myViewModel = subscriberViewModel
        // Since it's using LiveDate with DataBinding we need to provide a LifeCycle owner
        binding.lifecycleOwner = this
        displaySubscribersList()



    }

    //Function to observe the list of "subscribers" data in the Database table

        //for now we will just look the results but later need to write code to display this data on the RecyclerView
        //Code to observe the list of subscribers which is in LiveData format
        //Code to observe the LiveData
        private fun displaySubscribersList() {
            subscriberViewModel.subscribers.observe(this, Observer {
                Log.i("MYTAG",it.toString())
            })


    }
}