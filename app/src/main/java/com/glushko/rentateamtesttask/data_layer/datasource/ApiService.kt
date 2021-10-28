package com.glushko.rentateamtesttask.data_layer.datasource

import com.glushko.rentateamtesttask.data_layer.datasource.response.UserResponse
import io.reactivex.Observable
import io.reactivex.Observer
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET

interface ApiService {
    companion object{
        //Methods
        const val GET_USERS = "users"
    }

    //@FormUrlEncoded
    @GET(GET_USERS)
    fun getUsers(): Observable<UserResponse>
}