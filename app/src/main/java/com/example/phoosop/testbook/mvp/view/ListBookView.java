package com.example.phoosop.testbook.mvp.view;

import com.example.phoosop.testbook.BaseView;
import com.example.phoosop.testbook.mvp.model.object.Book;

import java.util.List;

/**
 * Created by Phoosop on 10.02.2016.
 */
public interface ListBookView extends BaseView {
    void showList(List<Book> list);
}
