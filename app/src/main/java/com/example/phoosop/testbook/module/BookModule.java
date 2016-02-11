package com.example.phoosop.testbook.module;

import com.example.phoosop.testbook.BookApp;
import com.example.phoosop.testbook.mvp.model.BookModel;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class BookModule {
    private BookApp mBookApp;

    public BookModule(BookApp bookApp) {
        mBookApp = bookApp;
    }

    @Singleton
    @Provides
    public BookModel provideBookModel(){
        return new BookModel();
    }

}
