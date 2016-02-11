package com.example.phoosop.testbook.component;

import com.example.phoosop.testbook.module.BookModule;
import com.example.phoosop.testbook.mvp.view.fragment.ListBookFragment;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by Phoosop on 10.02.2016.
 */
@Component(modules = BookModule.class)
@Singleton
public interface BookComponent {
    void inject(ListBookFragment listBookFragment);
}