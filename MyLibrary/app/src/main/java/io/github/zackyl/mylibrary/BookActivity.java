package io.github.zackyl.mylibrary;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class BookActivity extends AppCompatActivity {
    public static final String BOOK_ID_KEY = "bookId";
    private TextView txtBookName, txtAuthor, txtPages, txtDescription;
    private Button btnAddCurrentlyReading, btnAddWantToRead, btnAddAlreadyRead, btnAddFavorites;
    private ImageView bookImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book);

        initViews();

        Intent intent = getIntent();
        if (intent != null) {
            int bookID = intent.getIntExtra(BOOK_ID_KEY, -1);
            if (bookID != -1) {
                Book book = Utils.getInstance(this).getBookById(bookID);
                if (book != null) {
                    setData(book);  // TODO add more error handling in real app?
                    handleAlreadyRead(book);
                    handleWantToReadBooks(book);
                    handleCurrentlyReadingBook(book);
                    handleFavoriteBooks(book);
                }
            }
        }
    }

    // TODO NOTE, I would make each adding to group a helper function
    private void handleFavoriteBooks(Book book) {
        ArrayList<Book> favoriteBooks = Utils.getInstance(this).getFavoriteBooks();
        if (favoriteBooks.contains(book)) {
            btnAddFavorites.setEnabled(false);
        } else {
            btnAddFavorites.setOnClickListener(v -> {
                if (Utils.getInstance(BookActivity.this).addToFavoriteBooks(book)) {
                    Toast.makeText(this, "book added", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(BookActivity.this, FavoriteBooksActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(this, "Something wrong happened, try again.", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    private void handleCurrentlyReadingBook(Book book) {
        ArrayList<Book> currentlyReadingBooks = Utils.getInstance(this).getCurrentlyReadingBooks();
        if (currentlyReadingBooks.contains(book)) {
            btnAddCurrentlyReading.setEnabled(false);
        } else {
            btnAddCurrentlyReading.setOnClickListener(v -> {
                if (Utils.getInstance(BookActivity.this).addToCurrentlyReading(book)) {
                    Toast.makeText(this, "book added", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(BookActivity.this, CurrentlyReadingActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(this, "Something wrong happened, try again.", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    private void handleWantToReadBooks(final Book book) {
        ArrayList<Book> wantToReadBooks = Utils.getInstance(this).getWantToReadBooks();
        if (wantToReadBooks.contains(book)) {
            btnAddWantToRead.setEnabled(false);
        } else {
            btnAddWantToRead.setOnClickListener(v -> {
                if (Utils.getInstance(BookActivity.this).addToWantToRead(book)) {
                    Toast.makeText(this, "book added", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(BookActivity.this, WantToReadActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(this, "Something wrong happened, try again.", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    /**
     * enable and disable button
     * add book the Already Book ArrayList
     * @param book
     */
    private void handleAlreadyRead(Book book) {
        ArrayList<Book> alreadyReadBooks = Utils.getInstance(this).getAlreadyReadBooks();
        if (alreadyReadBooks.contains(book)) {
            btnAddAlreadyRead.setEnabled(false);
        } else {
            btnAddAlreadyRead.setOnClickListener(v -> {
                if (Utils.getInstance(BookActivity.this).addToAlreadyRead(book)) {
                    Toast.makeText(this, "book added", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(BookActivity.this, AlreadyReadBookActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(this, "Something wrong happened, try again.", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    private void setData(Book book) {
        txtBookName.setText(book.getName());
        txtAuthor.setText(book.getAuthor());
        txtPages.setText(String.valueOf(book.getPages()));

        txtDescription.setText(book.getLongDesc());
        Glide.with(this).asBitmap().load(book.getImageUrl()).into(bookImage);
    }

    private void initViews() {
        txtBookName = findViewById(R.id.txtBookActName);
        txtAuthor = findViewById(R.id.txtBookActAuthor);
        txtPages = findViewById(R.id.txtBookActPages);
        txtDescription = findViewById(R.id.LongDescriptionText);

        btnAddCurrentlyReading = findViewById(R.id.btnAddCurrentlyReading);
        btnAddWantToRead = findViewById(R.id.btnAddWantToRead);
        btnAddAlreadyRead = findViewById(R.id.btnAddAlreadyRead);
        btnAddFavorites = findViewById(R.id.btnAddToFavorites);

        bookImage = findViewById(R.id.imageView);
    }
}