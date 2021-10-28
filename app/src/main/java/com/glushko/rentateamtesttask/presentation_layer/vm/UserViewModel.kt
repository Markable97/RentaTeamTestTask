package com.glushko.rentateamtesttask.presentation_layer.vm

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.glushko.rentateamtesttask.business_logic_layer.interactor.UseCaseRepository
import com.glushko.rentateamtesttask.data_layer.datasource.response.UserResponse
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class UserViewModel(application: Application) : AndroidViewModel(application) {

    private var myCompositeDisposable: CompositeDisposable? = null

    val liveDataUser: MutableLiveData<UserResponse> = MutableLiveData()

    init {
        myCompositeDisposable = CompositeDisposable()
    }

    fun getUsers() {
        myCompositeDisposable?.add(
            UseCaseRepository().getUsers().observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(this::handlerResponse, this::handleError)
        )
    }

    private fun handleError(err: Throwable) {
        liveDataUser.postValue(UserResponse(per_page = -1))
    }

    private fun handlerResponse(response: UserResponse) {
        liveDataUser.postValue(response)
    }


    override fun onCleared() {
        super.onCleared()
        myCompositeDisposable?.clear()
    }

}