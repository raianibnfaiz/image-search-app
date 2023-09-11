package com.raian.imagesearchapp.ui.theme.components

import android.util.Log
import com.raian.imagesearchapp.ui.theme.Constant
import com.raian.imagesearchapp.ui.theme.network.ApiInterface
import com.raian.imagesearchapp.ui.theme.network.models.PixabayResponse
import com.raian.imagesearchapp.utils.Resource
import java.lang.Exception
import javax.inject.Inject

private const val TAG = "MainRepository"
class MainRepository @Inject constructor(private val apiResponse: ApiInterface) {

    suspend fun getQueryItems(q:String):Resource<PixabayResponse>{
        return try {
            val result = apiResponse.getQueryImage(query = q, key = Constant.key, imageType = "photo")
            Log.d(TAG, "MainContent: $result")
            Resource.Success(data = result)

        }catch (e:Exception){
            Log.d(TAG, "MainContent: ${e.message}")
            Resource.Error(message = e.message.toString())

        }
    }
}