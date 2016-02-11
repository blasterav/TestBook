package com.example.phoosop.testbook;

import android.app.Application;
import android.content.Context;

import com.example.phoosop.testbook.component.BookComponent;
import com.example.phoosop.testbook.component.DaggerBookComponent;
import com.example.phoosop.testbook.module.BookModule;
import com.example.phoosop.testbook.mvp.model.object.Book;
import com.parse.Parse;
import com.parse.ParseInstallation;
import com.parse.ParseObject;

/**
 * Created by Phoosop on 10.02.2016.
 */
public class BookApp extends Application {

    private BookComponent mBookComponent;

    public static BookApp get(Context context){
        return (BookApp) context.getApplicationContext();
    }
    @Override
    public void onCreate() {
        super.onCreate();
        ParseObject.registerSubclass(Book.class);


        Parse.initialize(this, getString(R.string.parse_app_id), getString(R.string.parse_client_key));
        ParseInstallation.getCurrentInstallation().saveInBackground();

        mBookComponent = DaggerBookComponent.builder()
                .bookModule(new BookModule(this))
                .build();

    }

    public BookComponent getAppComponent(){
        return mBookComponent;
    }


}