package com.poklad.obriotest.di

import android.content.Context
import com.poklad.obriotest.data.repositories.balance.WalletRepository
import com.poklad.obriotest.data.repositories.currency.CurrencyRateRepository
import com.poklad.obriotest.domain.mapper.BalanceDomainMapper
import com.poklad.obriotest.domain.mapper.CurrencyDomainMapper
import com.poklad.obriotest.domain.mapper.TransactionDomainMapper
import com.poklad.obriotest.domain.usecases.BalanceMultipleUseCases
import com.poklad.obriotest.domain.usecases.CurrencyMultipleUseCases
import com.poklad.obriotest.domain.usecases.TransactionMultipleUseCases
import com.poklad.obriotest.utils.connectivity.AndroidConnectivityChecker
import com.poklad.obriotest.utils.connectivity.ConnectivityChecker
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DomainModule {

    @Singleton
    @Provides
    fun provideConnectivityChecker(@ApplicationContext context: Context): ConnectivityChecker =
        AndroidConnectivityChecker(context)

    @Provides
    fun provideCurrencyRemoteToCurrencyPresentationDomainMapper() = CurrencyDomainMapper()

    @Provides
    fun provideCurrencyMultipleUseCases(
        repository: CurrencyRateRepository,
        mapper: CurrencyDomainMapper,
        connectivityChecker: ConnectivityChecker
    ) = CurrencyMultipleUseCases(repository, mapper, connectivityChecker)

    @Provides
    fun provideBalanceMultipleUseCases(repository: WalletRepository, mapper: BalanceDomainMapper) =
        BalanceMultipleUseCases(repository, mapper)

    @Provides
    fun provideTransactionMultipleUseCases(repository: WalletRepository, mapper: TransactionDomainMapper) =
        TransactionMultipleUseCases(repository, mapper)
}