package com.example.phoosop.testbook.mvp.view.fragment;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.phoosop.testbook.BaseFragment;
import com.example.phoosop.testbook.R;
import com.example.phoosop.testbook.mvp.view.custom.CustomScrollView;
import com.example.phoosop.testbook.mvp.view.custom.RoundedTransformation;
import com.example.phoosop.testbook.mvp.model.object.Book;
import com.squareup.picasso.Picasso;

import butterknife.Bind;
import butterknife.ButterKnife;

public class FullBookFragment extends BaseFragment  {
    @Bind(R.id.full_book_title)
    TextView bookTitle;
    @Bind(R.id.full_book_image)
    ImageView bookImage;
    @Bind(R.id.full_book_rating)
    RatingBar bookRating;
    @Bind(R.id.full_book_description)
    TextView bookDescription;
    @Bind(R.id.full_book_linear)
    LinearLayout mLinearLayout;
    @Bind(R.id.full_book_scroll)
    CustomScrollView mCustomScrollView;

    private float originalX = 0;
    private float originalY = 0;
    private float startMoveX = 0;
    private float startMoveY = 0;
    private static float CARDS_SWIPE_LENGTH = 100;


    public static FullBookFragment newInstance(Book book) {
        FullBookFragment fragment = new FullBookFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable("book", book);
        fragment.setArguments(bundle);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_full_book, container, false);
        ButterKnife.bind(this, v);
        Book book = (Book) getArguments().getSerializable("book");
        if (book.getTitle()!=null && !book.getTitle().isEmpty())
            bookTitle.setText(book.getTitle());
        if (book.getImage()!=null && !book.getImage().isEmpty())
            Picasso.with(getActivity()).load(book.getImage()).transform(new RoundedTransformation(16, 8)).into(bookImage);
        if (book.getRaiting()!=null)
            bookRating.setRating(book.getRaiting());
        if (book.getDescription()!=null && !book.getDescription().isEmpty())
            bookDescription.setText(book.getDescription());

        mLinearLayout.setOnTouchListener((view, event) -> {
            final float X = event.getRawX();
            final float Y =  event.getRawY();
            float deltaX = X - startMoveX;
            float deltaY = Y - startMoveY;
            switch (event.getAction() & MotionEvent.ACTION_MASK) {
                case MotionEvent.ACTION_DOWN:
                    startMoveX = X;
                    startMoveY = Y;
                    break;
                case MotionEvent.ACTION_UP:
                    if ( Math.abs(deltaX) < CARDS_SWIPE_LENGTH ) {
                        mLinearLayout.setX(originalX);
                        mLinearLayout.setY(originalY);
                    } else if ( deltaX > 0 ) {
                        onCardSwipeDown();
                    }
                    break;
                case MotionEvent.ACTION_POINTER_DOWN:
                    break;
                case MotionEvent.ACTION_POINTER_UP:
                    break;
                case MotionEvent.ACTION_MOVE:
                    if (deltaX > 0) {
                        mLinearLayout.setTranslationX(deltaX);
                        mCustomScrollView.setEnableScrolling(false);
                    }
                    else
                        mCustomScrollView.setEnableScrolling(true);
                    break;
            }
            mLinearLayout.invalidate();
            return true;
        });

        return v;
    }

    protected void onCardSwipeDown() {
        getActivity().onBackPressed();

    }


}
