package com.glushko.rentateamtesttask.presentation_layer.ui.about_programm_screen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.glushko.rentateamtesttask.R
import com.glushko.rentateamtesttask.business_logic_layer.domain.User
import com.glushko.rentateamtesttask.presentation_layer.ui.about_programm_screen.adapter.UsersAdapter
import com.glushko.rentateamtesttask.presentation_layer.vm.UserViewModel
import com.google.android.material.snackbar.Snackbar

class UsersListFragment : Fragment() {

    private var usersList = listOf<User>()

    lateinit var recycler: RecyclerView
    lateinit var adapter: UsersAdapter
    private lateinit var model: UserViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        model = ViewModelProvider(requireActivity()).get(UserViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragent_users_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = UsersAdapter(callback = object : UsersAdapter.Callback {
            override fun onClickUser(user: User) {
                findNavController().navigate(
                    R.id.action_userListFragment_to_userDetailFragment,
                    bundleOf(UserDetailFragment.EXTRA_USER_KEY to user)
                )
            }

        })
        recycler = view.findViewById(R.id.recycler_users)
        recycler.layoutManager = LinearLayoutManager(activity)
        recycler.adapter = adapter


    }

    override fun onResume() {
        super.onResume()
        model.liveDataUser.observe(requireActivity(), Observer {
            if(it.per_page != -1){
                adapter.setList(it.data, it.per_page, it.data.size)
            }else{

            }
        })
    }

}