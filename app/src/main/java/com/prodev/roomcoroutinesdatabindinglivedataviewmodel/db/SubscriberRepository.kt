package com.prodev.roomcoroutinesdatabindinglivedataviewmodel.db

class SubscriberRepository (private val dao: SubscriberDAO) {

    // we call to the DAO function  to assign LiveData to subscribers
    val subscribers = dao.getAllSubscribers()

    suspend fun insert ( subscriber: Subscriber):Long{
        return dao.insertSubscriber(subscriber)
    }

    suspend fun delete(subscriber: Subscriber) : Int{
        return dao.deleteSubscriber(subscriber)
    }

    suspend fun update(subscriber: Subscriber) : Int{
        return dao.updateSubscriber(subscriber)
    }
    suspend fun deleteAll() : Int {
        return dao.deleteAll()

    }

}
