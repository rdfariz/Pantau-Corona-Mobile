package org.d3if4127.pantaucorona.ui.home

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import org.d3if4127.pantaucorona.data.IndonesiaData
import org.d3if4127.pantaucorona.database.CoronaDAO
import org.d3if4127.pantaucorona.database.CoronaDB
import org.d3if4127.pantaucorona.database.CoronaRepository
import org.d3if4127.pantaucorona.network.CoronaApi

class HomeViewModel(application: Application) : AndroidViewModel(application) {

    private val repository : CoronaRepository
    private val coronaDAO: CoronaDAO

    private val _indonesia: LiveData<List<IndonesiaData>>
    val indonesia : LiveData<List<IndonesiaData>> get() = _indonesia

    private val _loadingRefresh = MutableLiveData<Boolean>()
    val loadingRefresh: LiveData<Boolean> get() = _loadingRefresh

    private var job = Job()
    private val crScope = CoroutineScope(job + Dispatchers.Main)

    init {
        coronaDAO = CoronaDB.getInstance(application).coronaDAO
        repository = CoronaRepository(coronaDAO)

        crScope.launch {
            try{
                refreshData()
            }catch (t: Throwable){

            }finally {

            }
        }
        _indonesia = repository.indonesia
    }

    suspend fun refreshData() {
        _loadingRefresh.value = true
        repository.refreshCorona()
        _loadingRefresh.value = false
    }

    override fun onCleared() {
        super.onCleared()
        job.cancel()
    }
}