package com.glushko.rentateamtesttask.data_layer.datasource.response

import com.glushko.rentateamtesttask.business_logic_layer.domain.User

data class UserResponse(val per_page: Int, val data: MutableList<User> = mutableListOf())
