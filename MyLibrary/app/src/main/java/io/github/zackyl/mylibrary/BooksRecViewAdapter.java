package io.github.zackyl.mylibrary;

import static io.github.zackyl.mylibrary.BookActivity.BOOK_ID_KEY;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import androidx.transition.TransitionManager;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.function.Function;

public class BooksRecViewAdapter extends RecyclerView.Adapter<BooksRecViewAdapter.ViewHolder> {
    private static final String TAG = "BooksRecViewAdapter";
    private ArrayList<Book> books = new ArrayList<>();
    private Context mContext;
    private String parentActivity;

    public BooksRecViewAdapter(Context mContext, String parentActivity) {
        this.mContext = mContext;
        this.parentActivity = parentActivity;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_book, parent, false);
        return new ViewHolder(view);
    }

    // TODO NOTE if I was doing this myself, would make helper functions
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Log.d(TAG, "onBindViewHolder: called");
        Book book = books.get(position);
        holder.txtName.setText(book.getName());
        Glide.with(mContext).asBitmap().load(book.getImageUrl()).into(holder.imgBook);
//        Glide.with(holder.parent.getContext()).load(book.getImageUrl()).into(holder.imgBook);  // this works too?

        holder.parent.setOnClickListener(v -> {
            Intent intent = new Intent(mContext, BookActivity.class);
            intent.putExtra(BOOK_ID_KEY, book.getId());
            mContext.startActivity(intent);
        });

        holder.txtAuthor.setText(book.getAuthor());
        holder.txtDescription.setText(book.getShortDesc());

        TransitionManager.beginDelayedTransition(holder.parent);
        if (book.getExpanded()) {
            holder.expandedRelLayout.setVisibility(View.VISIBLE);
            holder.downArrow.setVisibility(View.GONE);

            if (parentActivity.equals("allBooks")) {
                holder.btnDelete.setVisibility(View.GONE);
            } else if (parentActivity.equals("alreadyRead")) {
                setOnClickHolder(holder, book, b-> Utils.getInstance(mContext).removeFromAlreadyRead(b));
            } else if (parentActivity.equals("wantToRead")) {
                setOnClickHolder(holder, book, b-> Utils.getInstance(mContext).removeWantToRead(b));
            } else if (parentActivity.equals("currentlyReading")) {
                setOnClickHolder(holder, book, b-> Utils.getInstance(mContext).removeCurrentlyReading(b));
            } else if (parentActivity.equals("favoriteBooks")) {
                setOnClickHolder(holder, book, b-> Utils.getInstance(mContext).removeFavoriteBooks(b));
            }
        } else {
            holder.expandedRelLayout.setVisibility(View.GONE);
            holder.downArrow.setVisibility(View.VISIBLE);
        }
    }

    private void setOnClickHolder(ViewHolder holder, Book book, Function<Book, Boolean> func) {
        holder.btnDelete.setOnClickListener(v -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
            builder.setMessage("Are you sure you want to delete " + book.getName() + "?");
            builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    if (func.apply(book)) {
                        Toast.makeText(mContext, book.getName() + " removed", Toast.LENGTH_SHORT).show();
                        notifyDataSetChanged();
                    } else {
                        Toast.makeText(mContext, "Something wrong happened, please try again", Toast.LENGTH_SHORT).show();
                    }
                }
            });
            builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                }
            });
            builder.create().show();
        });
    }

    @Override
    public int getItemCount() {
        return books.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private CardView parent;
        private ImageView imgBook;
        private TextView txtName;
        private ImageView downArrow, upArrow;
        private RelativeLayout expandedRelLayout;
        private TextView txtAuthor, txtDescription;
        private TextView btnDelete;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            parent = itemView.findViewById(R.id.parent);
            imgBook = itemView.findViewById(R.id.imgBook);
            txtName = itemView.findViewById(R.id.txtBookName);

            downArrow = itemView.findViewById(R.id.btnDownArrow);
            upArrow = itemView.findViewById(R.id.btnUpArrow);
            expandedRelLayout = itemView.findViewById(R.id.expandedRelativeLayout);
            txtAuthor = itemView.findViewById(R.id.txtAuthor);
            txtDescription = itemView.findViewById(R.id.txtShortDesc);

            btnDelete = itemView.findViewById(R.id.btnDelete);

            downArrow.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Book book = books.get(getAdapterPosition());
                    book.setExpanded(!book.getExpanded());
                    notifyItemChanged(getAdapterPosition());
                }
            });

            upArrow.setOnClickListener(v -> {
                Book book = books.get(getAdapterPosition());
                book.setExpanded(!book.getExpanded());
                notifyItemChanged(getAdapterPosition());
            });
        }
    }

    public void setBooks(ArrayList<Book> books) {
        this.books = books;
        notifyDataSetChanged();
    }
}
