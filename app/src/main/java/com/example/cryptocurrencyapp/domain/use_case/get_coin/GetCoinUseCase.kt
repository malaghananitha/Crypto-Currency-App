package com.example.cryptocurrencyapp.domain.use_case.get_coin

import com.example.cryptocurrencyapp.common.Resource
import com.example.cryptocurrencyapp.data.remote.dto.toCoin
import com.example.cryptocurrencyapp.data.remote.dto.toCoinDetail
import com.example.cryptocurrencyapp.domain.model.Coin
import com.example.cryptocurrencyapp.domain.model.CoinDetail
import com.example.cryptocurrencyapp.domain.repository.ICoinRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetCoinUseCase @Inject constructor(
    //inject an interface always
    private val repository: ICoinRepository
) {
    operator fun invoke(coinId: String): Flow<Resource<CoinDetail>> = flow{
        try {
            //DISPLAY A PROGRESS BAR
            emit(Resource.Loading())
            val coin = repository.getCoinById(coinId).toCoinDetail()
            emit(Resource.Success(coin))

        }catch(e: HttpException){
            emit(Resource.Error(e.localizedMessage?: "An unexpected error occurred"))
        }catch (e:IOException){
            emit(Resource.Error("Couldn't reach your server. Please check the internet connection"))

        }

    }
}