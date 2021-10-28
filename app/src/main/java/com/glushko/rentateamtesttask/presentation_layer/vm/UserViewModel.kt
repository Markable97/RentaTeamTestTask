package com.glushko.rentateamtesttask.presentation_layer.vm

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.glushko.rentateamtesttask.business_logic_layer.domain.User
import com.glushko.rentateamtesttask.business_logic_layer.interactor.UseCaseRepository
import com.glushko.rentateamtesttask.data_layer.datasource.response.UserResponse
import com.glushko.rentateamtesttask.data_layer.repository.MainDatabase
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.functions.Consumer
import io.reactivex.schedulers.Schedulers

class UserViewModel(application: Application) : AndroidViewModel(application) {

    private var myCompositeDisposable: CompositeDisposable? = null

    val liveDataAPI: MutableLiveData<Boolean> = MutableLiveData()

    var liveDataDatabase: MutableLiveData<List<User>> = MutableLiveData()

    init {
        myCompositeDisposable = CompositeDisposable()
    }
    fun getUsers() {
        val requestDB = MainDatabase.getMainDao(getApplication()).getUsersDB()
            .observeOn(AndroidSchedulers.mainThread()).subscribe(
                Consumer {
                    liveDataDatabase.postValue(it)
                }
            )
        myCompositeDisposable?.addAll(
            requestDB,
            UseCaseRepository().getUsers(MainDatabase.getMainDao(getApplication()))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(this::handlerResponse, this::handleError)
        )
    }

    private fun handleError(err: Throwable) {
        liveDataAPI.postValue(false)
    }

    private fun handlerResponse(response: UserResponse) {
        //liveDataUser.postValue(response)
        liveDataAPI.postValue(true)
        if (response.per_page > 0) {
            myCompositeDisposable?.add(
                UseCaseRepository().setUsersDB(MainDatabase.getMainDao(getApplication()), response.data)
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeOn(Schedulers.io())
                    .subscribe({ Log.d("TAG","add to table") },
                        { Log.d("TAG","not add to table") })
            )
        }
    }

    override fun onCleared() {
        super.onCleared()
        myCompositeDisposable?.clear()
    }

}