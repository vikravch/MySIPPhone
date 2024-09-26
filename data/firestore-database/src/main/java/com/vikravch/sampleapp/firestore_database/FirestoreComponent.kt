package com.vikravch.sampleapp.firestore_database

import android.content.Context
import com.google.firebase.FirebaseApp
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object FirestoreComponent {

    @Provides
    @Singleton
    fun provideFirestore(
        @ApplicationContext context: Context
    ): FirebaseFirestore {
        FirebaseApp.initializeApp(context)
        return Firebase.firestore
    }
}
