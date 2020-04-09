package org.d3if4127.pantaucorona.database

import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.d3if4127.pantaucorona.network.CoronaApi

class CoronaRepository (private val coronaDAO: CoronaDAO) {
    val indonesia = coronaDAO.getIndonesiaData()

    suspend fun refreshCorona() {
        withContext(Dispatchers.IO) {
//            coronaDAO.deleteAllData()
            val indonesia = CoronaApi.retrofitService.getDataIndonesia()
            coronaDAO.insertIndonesia(indonesia)
        }
    }
}