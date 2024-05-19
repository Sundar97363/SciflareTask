package com.example.myapplication2.uiActivity

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.Toast
import com.example.myapplication2.retrofitService.MainViewModel
import com.example.myapplication2.retrofitService.NetworkResult
import dagger.hilt.android.AndroidEntryPoint
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication2.UserResponseItem
import com.example.myapplication2.adapter.UserListAdapter
import com.example.myapplication2.databinding.FragmentGetUserListBinding
import com.example.myapplication2.helper.BaseClass
import com.example.myapplication2.helper.DefaultUtils
import com.example.myapplication2.roomDB.User
import com.example.myapplication2.uiActivity.SciflareTaskActivity.Companion.back
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


@AndroidEntryPoint
class GetUserListFragment : Fragment() {

    var userAdapter : UserListAdapter?=null

      private val mainViewModel: MainViewModel by viewModels()
    private var _binding: FragmentGetUserListBinding? = null
    private val binding get() = _binding!!
    private var progressBar: ProgressBar? = null
    val getUserArray:ArrayList<UserResponseItem> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentGetUserListBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        setOnCLick()
    }

    fun setOnCLick(){
        binding.txtRoom.setOnClickListener {
            (activity as SciflareTaskActivity).fragmentHelper(RoomDBFragment())
        }
    }
    fun initViews(){
        progressBar = binding.progressBar
        progressBar!!.visibility = View.VISIBLE
        if (DefaultUtils.CheckInternet(requireActivity())) {
            fetchData()
        }
        else
        {
            Toast.makeText(requireActivity(), "Check your internet", Toast.LENGTH_SHORT).show()
        }
    }
    fun fetchData(){
        mainViewModel.fetchDogResponse()
        mainViewModel.response.observe(requireActivity()) { response ->
            when (response) {
                is NetworkResult.Success -> {
                    getUserArray.addAll(response.data!!)
                    loadData(getUserArray)
                    progressBar!!.visibility = View.GONE

                    lifecycleScope.launch(Dispatchers.IO) {
                           var  newUser = User(1,response.data)
                        BaseClass.database.userDao().insertUser(newUser!!)
                    }


                }
                is NetworkResult.Error -> {
                    Toast.makeText(requireActivity(), "Something Went Wrong", Toast.LENGTH_SHORT).show()
                }
                is NetworkResult.Loading -> {

                }
            }
        }
    }

    fun loadData(getUserArray: ArrayList<UserResponseItem>) {
        binding.userrecycler.layoutManager=LinearLayoutManager(requireActivity())
        userAdapter= UserListAdapter(getUserArray,requireActivity())
        binding.userrecycler.adapter=userAdapter
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun onResume() {
        super.onResume()
        back=0
    }
}