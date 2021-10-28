package com.glushko.rentateamtesttask.presentation_layer.ui.about_programm_screen


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.glushko.rentateamtesttask.R
import com.glushko.rentateamtesttask.business_logic_layer.domain.User
import com.glushko.rentateamtesttask.business_logic_layer.interactor.GlideApp
import com.glushko.rentateamtesttask.business_logic_layer.interactor.MyGlideApp

class UserDetailFragment: Fragment(R.layout.fragment_user_detail){

    companion object{
        const val EXTRA_USER_KEY = "user_info"
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_user_detail, container, false);
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val userDetail = arguments?.getParcelable<User>(EXTRA_USER_KEY)
        val imgView = view.findViewById<ImageView>(R.id.avatar)
        GlideApp.with(requireContext())
            .load(userDetail?.avatar)
            .placeholder(R.drawable.ic_mood_bad)
            .into(imgView)
        view.findViewById<TextView>(R.id.tvNameUser).apply {
            val name = "${userDetail?.first_name} ${userDetail?.last_name}"
            text = name
        }
        view.findViewById<TextView>(R.id.tvEmailUser).apply {
            text = userDetail?.email
        }
    }

}