package com.example.phoosop.testbook.mvp.model;

import com.example.phoosop.testbook.mvp.model.object.Book;
import com.parse.ParseException;
import com.parse.ParseQuery;

import java.util.List;

import rx.Observable;

/**
 * Created by Phoosop on 10.02.2016.
 */
public class BookModel {

    public Observable<List<Book>> getBooks(){
        ParseQuery<Book> query = ParseQuery.getQuery("Books");
        return Observable.create(subscriber -> {
            try {
                subscriber.onNext(query.find());
            } catch (ParseException e) {
                subscriber.onError(e.getCause());
            }
        });

    }



}
