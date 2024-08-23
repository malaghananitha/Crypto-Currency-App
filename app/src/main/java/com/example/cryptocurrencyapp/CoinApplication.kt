package com.example.cryptocurrencyapp

import android.app.Application
import dagger.hilt.android.HiltAndroidApp


//Helps to give the information to the dagger hilt about the coin application
@HiltAndroidApp
class CoinApplication:Application() {
}