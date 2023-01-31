package io.github.zackyl.mylibrary;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.MenuItem;

import java.util.ArrayList;

public class AllBooksActivity extends AppCompatActivity {
    private RecyclerView booksRecView;
    private BooksRecViewAdapter booksAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_books);

        booksRecView = findViewById(R.id.booksRecyclerView);
        booksAdapter = new BooksRecViewAdapter(this, "allBooks");

        booksRecView.setAdapter(booksAdapter);
        booksRecView.setLayoutManager(new LinearLayoutManager(this));

        booksAdapter.setBooks(Utils.getInstance(this).getAllBooks());

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    //    @Override
//    public void finish() {
//        super.finish();
//        overridePendingTransition(R.anim.slide_in, R.anim.slide_out);
////        overridePendingTransition(R.anim.slide_out, R.anim.slide_in);
//    }
}