package edu.samgarcia.onepieceapp.di

import androidx.paging.ExperimentalPagingApi
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import edu.samgarcia.onepieceapp.data.local.OnePieceDatabase
import edu.samgarcia.onepieceapp.data.remote.OnePieceApi
import edu.samgarcia.onepieceapp.data.repository.RemoteDataSourceImpl
import edu.samgarcia.onepieceapp.domain.repository.RemoteDataSource
import edu.samgarcia.onepieceapp.utils.Constants.BASE_URL
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.MediaType
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@ExperimentalPagingApi
@ExperimentalSerializationApi
@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    @Provides
    @Singleton
    fun provideHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .readTimeout(16, TimeUnit.MINUTES)
            .connectTimeout(16, TimeUnit.MINUTES)
            .build()
    }

    @Provides
    @Singleton
    fun provideRetrofitInstance(httpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(httpClient)
            .addConverterFactory(
                Json.asConverterFactory(MediaType.get("application/json"))
            )
            .build()
    }

    @Provides
    @Singleton
    fun provideOnePieceApi(retrofit: Retrofit): OnePieceApi {
        return retrofit.create(OnePieceApi::class.java)
    }

    @Provides
    @Singleton
    fun provideRemoteDataSource(
        onePieceApi: OnePieceApi,
        onePieceDatabase: OnePieceDatabase
    ): RemoteDataSource {
        return RemoteDataSourceImpl(
            onePieceApi = onePieceApi,
            onePieceDatabase = onePieceDatabase
        )
    }
}