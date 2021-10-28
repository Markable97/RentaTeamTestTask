package com.glushko.rentateamtesttask.business_logic_layer.domain

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize


@Parcelize
@Entity(tableName = "table_users")
data class User(
    @PrimaryKey
    val id: Long,
    val email: String,
    val first_name: String,
    val last_name: String,
    val avatar: String
):Parcelable
