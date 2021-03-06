package com.zhboy.retrofit2_coroutine

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.zhboy.retrofit2_coroutine.network.request.RequestStatus
import com.zhboy.retrofit2_coroutine.viewmodel.UserViewModel

class MainActivity : AppCompatActivity() {

    private val mViewModel by viewModels<UserViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        for (i in 0..1000) {
            getGoods()
            getUserInfo("小明同学${i}", "123456${i}")
        }

    }

    /**
     * 获取商品信息
     */
    private fun getGoods() {
        mViewModel.getGoods().observe(this, Observer {
            when (it.requestStatus) {
                RequestStatus.START -> {
                }
                RequestStatus.SUCCESS -> {
                    val d = it.data?.data
                    Log.d("VVV:", " ${d?.get(0)?.title}")
                }
                RequestStatus.COMPLETE -> {
                    Toast.makeText(this, "网络请求完成了", Toast.LENGTH_SHORT).show()
                }
                RequestStatus.ERROR -> {
                    Toast.makeText(this, "网络出错了", Toast.LENGTH_SHORT).show()
                }
            }
        })
    }

    /**
     * 获取用户信息
     */
    private fun getUserInfo(userName: String, password: String) {
        mViewModel.getUserInfo(userName, password).observe(this, Observer {
            when (it.requestStatus) {
                RequestStatus.START -> {
                }
                RequestStatus.SUCCESS -> {
                    Log.d("VVV:", " ${it.data}")
                }
                RequestStatus.COMPLETE -> {
                    Toast.makeText(this, "网络请求完成了", Toast.LENGTH_SHORT).show()
                }
                RequestStatus.ERROR -> {
                    Toast.makeText(this, "网络出错了", Toast.LENGTH_SHORT).show()
                }
            }
        })
    }

}