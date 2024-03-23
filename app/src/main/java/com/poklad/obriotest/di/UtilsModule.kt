package com.poklad.obriotest.di

import com.poklad.obriotest.presentation.ui.adapter.TransactionsListAdapter
import com.poklad.obriotest.utils.DateConverter
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(ActivityComponent::class)
class PresentationModule {

    @Provides
    fun provideDateConverter(): DateConverter = DateConverter()

    @Provides
    fun provideTransactionsListAdapter(dataConverter: DateConverter): TransactionsListAdapter {
        return TransactionsListAdapter(dataConverter)
    }
}