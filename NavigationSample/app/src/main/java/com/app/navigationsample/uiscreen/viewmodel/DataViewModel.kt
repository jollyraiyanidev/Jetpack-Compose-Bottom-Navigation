package com.app.navigationsample.uiscreen.viewmodel

import android.app.Activity
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.navigationsample.uiscreen.model.sliding.SlidingResponse
import com.app.navigationsample.uiscreen.model.State
import com.app.navigationsample.utils.AppUtils
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class DataViewModel  @Inject constructor(): ViewModel() {
     val data = MutableLiveData<State<SlidingResponse>>()
    //private val users : MutableLiveData<State<SlidingResponse>> = data

    fun fetchData(activity:Activity) {
        data.value = State.loading()

        viewModelScope.launch {
            val response = withContext(Dispatchers.IO) {
                AppUtils.getSlider(activity, SlidingResponse::class.java) as SlidingResponse
               //                UserRepository().callApi(activity, Constant.LOGIN,body,null)
            }
            if(response.isNotEmpty()){
                data.value = State.success(response)}
            else{
                data.value = State.error("exception")

            }
        }
    }
}