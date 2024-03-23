package com.poklad.obriotest.di

import android.content.Context
import androidx.room.Room
import com.poklad.obriotest.data.common.data_sources.WalletDataSource
import com.poklad.obriotest.data.local.data_source.CacheCurrencyDataSource
import com.poklad.obriotest.data.local.data_source.CacheWalletDataSource
import com.poklad.obriotest.data.local.database.AppDatabase
import com.poklad.obriotest.data.local.database.DefaultAppDatabase
import com.poklad.obriotest.data.remote.CurrencyApi
import com.poklad.obriotest.data.remote.data_source.RemoteCurrencyDataSource
import com.poklad.obriotest.data.repositories.balance.DefaultWalletRepository
import com.poklad.obriotest.data.repositories.balance.WalletRepository
import com.poklad.obriotest.data.repositories.currency.CurrencyRateRepository
import com.poklad.obriotest.data.repositories.currency.DefaultCurrencyRateRepository
import com.poklad.obriotest.utils.ApiConstants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataModule {

    @Singleton
    @Provides
    fun providesStoreApi(retrofit: Retrofit): CurrencyApi = retrofit.create(CurrencyApi::class.java)

    @Singleton
    @Provides
    fun provideOkHttpClient(): OkHttpClient = OkHttpClient.Builder()
        .addInterceptor(HttpLoggingInterceptor().also { it.level = HttpLoggingInterceptor.Level.BODY })
        .build()

    @Singleton
    @Provides
    fun provideRetrofitInstance(okHttpClient: OkHttpClient): Retrofit = Retrofit.Builder()
        .baseUrl(ApiConstants.BASE_URL)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext context: Context): AppDatabase = Room
        .databaseBuilder(context, DefaultAppDatabase::class.java, DefaultAppDatabase.DB_NAME)
        .fallbackToDestructiveMigration()
        .build()

    @Provides
    @Singleton
    fun provideCurrencyRateRepository(remoteDataSource: RemoteCurrencyDataSource, cacheCurrencyDataSource: CacheCurrencyDataSource): CurrencyRateRepository =
        DefaultCurrencyRateRepository(cacheCurrencyDataSource, remoteDataSource)

    @Provides
    @Singleton
    fun provideWalletRepository(dataSource: WalletDataSource) : WalletRepository =
        DefaultWalletRepository(dataSource)

    @Provides
    @Singleton
    fun provideRemoteCurrencyDataSource(api: CurrencyApi): RemoteCurrencyDataSource = RemoteCurrencyDataSource(api)

    @Provides
    @Singleton
    fun provideCacheCurrencyDataSource(appDatabase: AppDatabase): CacheCurrencyDataSource = CacheCurrencyDataSource(appDatabase.getCurrencyDao())

    @Provides
    @Singleton
    fun provideWalletDataSource(appDatabase: AppDatabase): WalletDataSource = CacheWalletDataSource(appDatabase.getTransactionDao(), appDatabase.getBalanceDao())

}