package com.example.myapplication2.uiActivity

import android.os.Bundle
import android.window.OnBackInvokedDispatcher
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.myapplication2.R
import com.example.myapplication2.databinding.ActivitySciflareTaskBinding
import com.example.myapplication2.retrofitService.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SciflareTaskActivity : AppCompatActivity() {
    companion object{
        var back=0
    }
     lateinit var binding:ActivitySciflareTaskBinding
    private val mainViewModel by viewModels<MainViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
         binding = ActivitySciflareTaskBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initViews()
    }

fun initViews(){
    fragmentHelper(GetUserListFragment())
}



    fun fragmentHelper(fragment: Fragment) {
//        if (savedInstanceState == null) {
            val fragmentManager = supportFragmentManager
            val fragmentTransaction = fragmentManager.beginTransaction()
            fragmentTransaction.replace(R.id.mainframe, fragment)
            fragmentTransaction.commit()
//        }
    }

    override fun onBackPressed() {
        when(back){
            1->{
                fragmentHelper(GetUserListFragment())
            }
            else ->

                super.onBackPressed()

        }
    }
}