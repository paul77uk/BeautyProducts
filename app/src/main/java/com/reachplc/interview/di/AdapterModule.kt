package com.reachplc.interview.di

import com.reachplc.interview.ui.list.ListAdapter
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class AdapterModule {

    @Provides
    fun provideListAdapter() = ListAdapter()

}