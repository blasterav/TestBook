package com.example.phoosop.testbook.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.phoosop.testbook.MainActivity;
import com.example.phoosop.testbook.R;
import com.example.phoosop.testbook.mvp.model.object.Book;
import com.example.phoosop.testbook.mvp.view.custom.RoundedTransformation;
import com.example.phoosop.testbook.mvp.view.fragment.FullBookFragment;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;



public class AdapterBooks extends RecyclerView.Adapter<AdapterBooks.ViewHolder> {
    private Context mContext;
    private List<Book> mBooks;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.book_title)
        TextView bookTitle;
        @Bind(R.id.book_image)
        ImageView bookImage;
        @Bind(R.id.book_rating)
        RatingBar bookRating;
        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public AdapterBooks(Context context, List<Book> posts) {
        this.mContext = context;
        this.mBooks = posts;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent,
                                                       int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_books, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        Book book = mBooks.get(position);

        if (book.getTitle()!=null && !book.getTitle().isEmpty())
            if (book.getTitle().length()>mContext.getResources().getInteger(R.integer.item_book_title_max))
                holder.bookTitle.setText(String.format(mContext.getString(R.string.item_mini_description), book.getTitle().substring(0, mContext.getResources().getInteger(R.integer.item_book_title_max))));
            else
                holder.bookTitle.setText(book.getTitle());

        if (book.getImage()!=null && !book.getImage().isEmpty())
            Picasso.with(mContext).load(book.getImage()).transform(new RoundedTransformation(16, 8)).into(holder.bookImage);

        if (book.getRaiting()!=null) {
            holder.bookRating.setRating(book.getRaiting());
        }

        holder.itemView.setOnClickListener(v -> {
            ((MainActivity)mContext).getSupportFragmentManager().beginTransaction()
                    .replace(R.id.main_container, FullBookFragment.newInstance(book), FullBookFragment.class.getName())
                    .addToBackStack(FullBookFragment.class.getName()).commitAllowingStateLoss();

        });

    }

    @Override
    public int getItemCount() {
        return mBooks.size();
    }

}