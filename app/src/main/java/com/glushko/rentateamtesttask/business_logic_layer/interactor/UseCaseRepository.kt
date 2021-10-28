package com.glushko.rentateamtesttask.business_logic_layer.interactor

import com.glushko.rentateamtesttask.data_layer.datasource.NetworkService
import com.glushko.rentateamtesttask.data_layer.datasource.response.UserResponse
import io.reactivex.Observable

class UseCaseRepository() {

    fun getUsers(): Observable<UserResponse>{
        return NetworkService.makeNetworkServiceRxJava().getUsers()
    }
}