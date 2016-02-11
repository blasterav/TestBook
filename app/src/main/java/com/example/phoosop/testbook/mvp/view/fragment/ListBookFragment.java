package com.example.phoosop.testbook.mvp.view.fragment;


import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.phoosop.testbook.BaseFragment;
import com.example.phoosop.testbook.BookApp;
import com.example.phoosop.testbook.R;
import com.example.phoosop.testbook.adapter.AdapterBooks;
import com.example.phoosop.testbook.mvp.model.object.Book;
import com.example.phoosop.testbook.mvp.presenter.BookPresenter;
import com.example.phoosop.testbook.mvp.view.ListBookView;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ListBookFragment extends BaseFragment implements ListBookView{

    @Inject
    BookPresenter mBookPresenter;

    @Bind(R.id.list_books_recycler)
    RecyclerView mRecyclerView;

    public static ListBookFragment newInstance() {
        ListBookFragment listBookFragment = new ListBookFragment();
        return listBookFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_list_book, container, false);
        ((BookApp)getActivity().getApplication()).getAppComponent().inject(this);
        ButterKnife.bind(this, v);

        mBookPresenter.attachView(this);
        showProgress();
        mBookPresenter.onCreate();
        mRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        return v;
    }

    @Override
    public void showList(List<Book> list) {
        mRecyclerView.setAdapter(new AdapterBooks(getActivity(), new ArrayList<Book>()));
        dismissProgress();
        mRecyclerView.setAdapter(new AdapterBooks(getActivity(), list));

    }

    @OnClick(R.id.list_books_sort_title)
    public void sortTitle(){
        showProgress();
        mBookPresenter.sortTitle();
    }

    @OnClick(R.id.list_books_sort_rating)
    public void sortRating(){
        showProgress();
        mBookPresenter.sortRating();
    }


}
