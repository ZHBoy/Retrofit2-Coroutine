package com.zhboy.retrofit2_coroutine.network.request

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData

/**
 * @author zhou_hao
 * @date 2021/1/14
 * @description:
 */
fun <T> MediatorLiveData<ResultData<T>>.addSource(liveData: LiveData<ResultData<T>>) {
    this.addSource(liveData) {
        if (it.requestStatus == RequestStatus.COMPLETE) {
            this.removeSource(liveData)
        }
        this.value = it
    }
}