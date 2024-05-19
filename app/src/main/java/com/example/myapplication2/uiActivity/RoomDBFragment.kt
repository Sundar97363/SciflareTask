package com.example.myapplication2.uiActivity

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication2.UserResponseItem
import com.example.myapplication2.adapter.RoomDbAdapter
import com.example.myapplication2.databinding.FragmentRoomDBBinding
import com.example.myapplication2.helper.BaseClass
import com.example.myapplication2.roomDB.User
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


@AndroidEntryPoint
class RoomDBFragment : Fragment() {
    private var _binding: FragmentRoomDBBinding? = null
    private val binding get() = _binding!!
    var roomDbAdapter : RoomDbAdapter?=null
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
        _binding = FragmentRoomDBBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
    }

    fun initViews(){
        lifecycleScope.launch(Dispatchers.IO) {
            val userList: List<User> = BaseClass.database.userDao().getAllUsers()

            for (i in userList){
                for (j in i.yourModelList){
                    getUserArray.add(j)
                }
            }
            requireActivity().runOnUiThread {
                loadData(getUserArray)

            }
        }
    }
    fun loadData(getUserArray: ArrayList<UserResponseItem>) {
        binding.userrecycler.layoutManager= LinearLayoutManager(requireActivity())
        roomDbAdapter= RoomDbAdapter(getUserArray,requireActivity())
        binding.userrecycler.adapter=roomDbAdapter
    }
    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun onResume() {
        super.onResume()
        SciflareTaskActivity.back =1
    }
}