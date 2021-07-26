package com.lovys.assignment.ui.viewmodels

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.lovys.assignment.domain.localdb.Beer
import com.lovys.assignment.domain.userflow.GetBeerList
import com.lovys.assignment.domain.userflow.GetBeersById
import com.lovys.assignment.domain.userflow.GetSearchBeer
import com.lovys.assignment.domain.utils.Results
import com.lovys.assignment.ui.utils.Data
import com.lovys.assignment.ui.utils.SharedPreferences
import com.lovys.assignment.ui.utils.Status
import com.lovys.assignment.ui.viewmodels.base.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class PunkViewModel(
    private val sharedPreferences: SharedPreferences,
    val getBeersById: GetBeersById,
    val getBeerList: GetBeerList,
    val getSearchBeer: GetSearchBeer
) : BaseViewModel() {

    private var mutableMainStateList = MutableLiveData<Data<List<Beer>>>()
    val mainStateList: LiveData<Data<List<Beer>>>
        get() {
            return mutableMainStateList
        }

    private var mutableMainStateDetail = MutableLiveData<Data<List<Beer>>>()
    val mainStateDetail: LiveData<Data<List<Beer>>>
        get() {
            return mutableMainStateDetail
        }

    fun onStartHome(page: Int, perPage: Int) = launch {
        mutableMainStateList.value = Data(responseType = Status.LOADING)
        when (val result = withContext(Dispatchers.IO) { getBeerList(page, perPage) }) {
            is Results.Failure -> {
                mutableMainStateList.value =
                    Data(responseType = Status.ERROR, error = result.exception)
            }
            is Results.Success -> {
                mutableMainStateList.value =
                    Data(responseType = Status.SUCCESSFUL, data = result.data)
            }
        }
    }

    fun onClickToBeerDetails(id: Int, context: Context) = launch {
        mutableMainStateDetail.value = Data(responseType = Status.LOADING)
        when (val result = withContext(Dispatchers.IO) { getBeersById(id) }) {
            is Results.Failure -> {
                mutableMainStateDetail.value =
                    Data(responseType = Status.ERROR, error = result.exception)
            }
            is Results.Success -> {
                mutableMainStateDetail.value =
                    Data(responseType = Status.SUCCESSFUL, data = result.data)
                result.data?.get(0)
                    ?.let { sharedPreferences.saveCurrentBeerData(it) }
            }
        }
    }

    fun onSearchClick(beerName: String, page: Int, perPage: Int) = launch {
        mutableMainStateList.value = Data(responseType = Status.LOADING)
        when (val result = withContext(Dispatchers.IO) { getSearchBeer(beerName, page, perPage) }) {
            is Results.Failure -> {
                mutableMainStateList.value =
                    Data(responseType = Status.ERROR, error = result.exception)
            }
            is Results.Success -> {
                mutableMainStateList.value =
                    Data(responseType = Status.SUCCESSFUL, data = result.data)
            }
        }
    }
}