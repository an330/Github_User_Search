package com.example.githubusersearch.di.Network

import com.example.githubusersearch.data.remote.GitHubApi
import com.example.githubusersearch.data.repoImpl.UserRepositoryImpl
import com.example.githubusersearch.data.repository.UserRepository
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    fun provideBaseUrl() = "https://api.github.com/"

    @Provides
    @Singleton
    fun provideOkhttpClient() :OkHttpClient {
        val logging = HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BASIC}
        val headInterceptor = Interceptor{chain ->  
            val req = chain.request().newBuilder()
            chain.proceed(req.build())
        }
        return OkHttpClient().newBuilder().addInterceptor(headInterceptor)
            .addInterceptor(logging).build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(ok:OkHttpClient,baseUrl:String):Retrofit = Retrofit.Builder()
        .client(ok)
        .baseUrl(baseUrl)
        .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
        .build()

    @Provides
    @Singleton
    fun provideGitHubApi(retrofit: Retrofit): GitHubApi = retrofit.create(GitHubApi::class.java)

    @Provides
    @Singleton
    fun provideUserRepository(api: GitHubApi) : UserRepository = UserRepositoryImpl(api)
}