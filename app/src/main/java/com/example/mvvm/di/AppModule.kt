package com.example.mvvm.di

import com.google.firebase.firestore.FirebaseFirestore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideFirestoreInstance() = FirebaseFirestore.getInstance()

    //tocarlo
    @Provides
    @Singleton
    fun provideUserList(
        fireStore: FirebaseFirestore
    ) = fireStore.collection("users")
}