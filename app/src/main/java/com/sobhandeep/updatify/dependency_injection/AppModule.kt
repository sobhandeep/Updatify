package com.sobhandeep.updatify.dependency_injection

import android.app.Application
import androidx.room.Room
import com.sobhandeep.updatify.data.manager.LocalUserManager
import com.sobhandeep.updatify.data.remote.data_transfer_object.NewsAPI
import com.sobhandeep.updatify.data.repository.NewsRepository
import com.sobhandeep.updatify.data.room.NewsDao
import com.sobhandeep.updatify.data.room.NewsDatabase
import com.sobhandeep.updatify.data.room.NewsTypeConvertor
import com.sobhandeep.updatify.domain.instances.app_entry.AppEntryInstances
import com.sobhandeep.updatify.domain.instances.app_entry.ReadAppEntry
import com.sobhandeep.updatify.domain.instances.app_entry.SaveAppEntry
import com.sobhandeep.updatify.domain.instances.news.DeleteArticle
import com.sobhandeep.updatify.domain.instances.news.GetNews
import com.sobhandeep.updatify.domain.instances.news.GetNewsByCategory
import com.sobhandeep.updatify.domain.instances.news.GetTopHeadlines
import com.sobhandeep.updatify.domain.instances.news.NewsInstances
import com.sobhandeep.updatify.domain.instances.news.SearchNews
import com.sobhandeep.updatify.domain.instances.news.SelectArticle
import com.sobhandeep.updatify.domain.instances.news.SelectArticles
import com.sobhandeep.updatify.domain.instances.news.UpsertArticle
import com.sobhandeep.updatify.domain.manager.LocalManager
import com.sobhandeep.updatify.domain.repository.NewsRepositoryInterface
import com.sobhandeep.updatify.util.Constants.BASE_URL
import com.sobhandeep.updatify.util.Constants.DATABASE_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideLocalUserManager(
        application: Application
    ): LocalManager = LocalUserManager(context = application)

    @Provides
    @Singleton
    fun provideAppEntryInstances(
        localUserManager: LocalManager
    ):AppEntryInstances = AppEntryInstances(
        readAppEntry = ReadAppEntry(localUserManager),
        saveAppEntry = SaveAppEntry(localUserManager)
    )

    @Provides
    @Singleton
    fun provideNewsAPI(): NewsAPI {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(NewsAPI::class.java)
    }

    @Provides
    @Singleton
    fun provideNewsRepository(
        newsAPI: NewsAPI,
        newsDao: NewsDao
    ): NewsRepositoryInterface {
       return NewsRepository(newsAPI, newsDao)
    }

    @Provides
    @Singleton
    fun provideNewsInstances(
        newsRepository: NewsRepositoryInterface,
    ): NewsInstances{
        return NewsInstances(
            getNews = GetNews(newsRepository),
            searchNews = SearchNews(newsRepository),
            upsertArticle = UpsertArticle(newsRepository),
            deleteArticle = DeleteArticle(newsRepository),
            selectArticles = SelectArticles(newsRepository),
            selectArticle = SelectArticle(newsRepository),
            getNewsByCategory = GetNewsByCategory(newsRepository),
            getTopHeadlines = GetTopHeadlines(newsRepository)
        )
    }

    @Provides
    @Singleton
    fun provideNewsDatabase(
        application: Application
    ): NewsDatabase {
        return Room.databaseBuilder(
            context = application,
            klass = NewsDatabase::class.java,
            name = DATABASE_NAME
        ).addTypeConverter(NewsTypeConvertor())
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    fun provideNewsDao(
        newsDatabase: NewsDatabase
    ): NewsDao = newsDatabase.newsDao
}