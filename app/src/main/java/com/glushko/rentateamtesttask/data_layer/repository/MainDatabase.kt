package com.glushko.rentateamtesttask.data_layer.repository

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.room.*
import com.glushko.rentateamtesttask.business_logic_layer.domain.User
import io.reactivex.Flowable
import io.reactivex.Single

@Database(entities = [User::class], version = 1, exportSchema = false)
abstract class MainDatabase: RoomDatabase(){

    abstract fun mainDao(): MainDao

    companion object{
        private var INSTANCE: MainDatabase? = null
        private var MAIN_DAO: MainDao? = null

        fun getMainDao(context: Context): MainDao{
            if(MAIN_DAO ==null){
                MAIN_DAO = getDatabase(context).mainDao()
            }
            return MAIN_DAO!!
        }

        private fun getDatabase(context: Context): MainDatabase{
            var tempInstance = INSTANCE
            if(tempInstance==null){
                synchronized(this){
                    tempInstance = Room.databaseBuilder(context.applicationContext,
                        MainDatabase::class.java,
                        "users_db")
                        .build()
                    return tempInstance!!
                }
            }else{
                return tempInstance!!
            }

        }
    }

}

@Dao
interface MainDao{
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(users: List<User>): Single<List<Long>>
    @Query("select * from table_users")
    fun getUsersDB(): Flowable<List<User>>
}