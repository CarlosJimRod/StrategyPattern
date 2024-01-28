package com.cjimenezro.strategypattern.features.superHeroes.data.local

import android.content.Context
import com.cjimenezro.strategypattern.app.data.serialization.JsonSerialization
import com.cjimenezro.strategypattern.app.domain.Either
import com.cjimenezro.strategypattern.app.domain.ErrorApp
import com.cjimenezro.strategypattern.app.domain.left
import com.cjimenezro.strategypattern.app.domain.right
import com.cjimenezro.strategypattern.features.superHeroes.domain.SuperHeroe

class SuperHeroeLocalDataSource(context: Context, private val jsonSerialization: JsonSerialization) {

    private val sharedPref=context.getSharedPreferences("SuperHeroes",Context.MODE_PRIVATE)

    fun saveSuperHeroe(superHeroe: SuperHeroe): Either<ErrorApp, Boolean> {
        return try {
            with(sharedPref.edit()) {
                val jsonSuperHeroe = jsonSerialization.toJson(superHeroe, SuperHeroe::class.java)
                putString(superHeroe.id.toString(), jsonSuperHeroe)
                apply()
            }
            true.right()
        }catch (ex:Exception){
            return ErrorApp.UnknownError.left()
        }
    }

    fun getSuperHeroe(): Either<ErrorApp, List<SuperHeroe>> {
        return try {
            val jsonSuperHeroe=sharedPref.all as Map<String,String>

            val hotels=jsonSuperHeroe.values.map {
                jsonSerialization.fromJson(it,SuperHeroe::class.java)
            }

            hotels.right()
        }catch (ex:Exception){
            ErrorApp.UnknownError.left()
        }
    }

    fun deletePrincipalData():Either<ErrorApp, Boolean>{
        return try {
            with(sharedPref.edit()) {
                clear()
                apply()
            }
            true.right()
        } catch (ex: Exception) {
            return ErrorApp.UnknownError.left()
        }
    }
}