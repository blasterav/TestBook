package com.example.phoosop.testbook.mvp.presenter;


import com.example.phoosop.testbook.BasePresenter;
import com.example.phoosop.testbook.mvp.model.BookModel;
import com.example.phoosop.testbook.mvp.model.object.Book;
import com.example.phoosop.testbook.mvp.view.ListBookView;

import java.util.Collections;
import java.util.List;

import javax.inject.Inject;


import rx.Observable;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;


/**
 * Created by blasterav on 27.01.16.
 */
public class BookPresenter extends BasePresenter<ListBookView> {
    private BookModel mBookModel;
    private Subscription mSubscription;
    private Observable<List<Book>> observable;

    @Inject
    public BookPresenter(BookModel bookModel) {
        mBookModel = bookModel;
    }

    public void onCreate(){
        observable = mBookModel.getBooks();
        mSubscription = observable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(list -> {
                    getView().showList(list);
                });
    }

    public void sortTitle(){
        mSubscription = observable
                .map(books -> {
                    Collections.sort(books, (lhs, rhs) -> lhs.getTitle().compareToIgnoreCase(rhs.getTitle()));
                    return books;
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(list -> {
                    getView().showList(list);
                });
    }

    public void sortRating(){
        mSubscription = observable
                .map(books -> {
                    Collections.sort(books, (lhs, rhs) -> rhs.getRaiting() - lhs.getRaiting());
                    return books;
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(list -> {
                    getView().showList(list);
                });
    }
}
