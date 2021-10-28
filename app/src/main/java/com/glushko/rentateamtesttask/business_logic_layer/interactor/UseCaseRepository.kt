package com.glushko.rentateamtesttask.business_logic_layer.interactor

import com.glushko.rentateamtesttask.business_logic_layer.domain.User
import com.glushko.rentateamtesttask.data_layer.datasource.NetworkService
import com.glushko.rentateamtesttask.data_layer.datasource.response.UserResponse
import com.glushko.rentateamtesttask.data_layer.repository.MainDao
import io.reactivex.Observable
import io.reactivex.Single
import java.lang.Exception

class UseCaseRepository() {

    fun getUsers(dao: MainDao): Observable<UserResponse>{
        return NetworkService.makeNetworkServiceRxJava().getUsers()
    }

    fun setUsersDB(mainDao: MainDao, users: List<User>): Single<List<Long>> {
        return mainDao.insert(users)
    }
}