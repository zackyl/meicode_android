package io.github.zackyl.mylibrary;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button btnAllBooks, btnAlreadyRead, btnWantToRead, btnCurrentlyReading, btnFavorite, btnAbout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();
        btnAllBooks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AllBooksActivity.class);
                startActivity(intent);
            }
        });

        btnAlreadyRead.setOnClickListener(v-> {
            Intent intent = new Intent(MainActivity.this, AlreadyReadBookActivity.class);
            startActivity(intent);
        });

        btnWantToRead.setOnClickListener(v-> {
            Intent intent = new Intent(MainActivity.this, WantToReadActivity.class);
            startActivity(intent);
        });

        btnCurrentlyReading.setOnClickListener(v-> {
            Intent intent = new Intent(MainActivity.this, CurrentlyReadingActivity.class);
            startActivity(intent);
        });

        btnFavorite.setOnClickListener(v-> {
            Intent intent = new Intent(MainActivity.this, FavoriteBooksActivity.class);
            startActivity(intent);
        });

        btnAbout.setOnClickListener(v->{
            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
            builder.setTitle(getString(R.string.app_name));
            builder.setMessage("Designed and developed with love by Zack at Zackyl.\nCheck my website for more awesome apps");
            builder.setNegativeButton("Dismiss", (dialog, which) -> {});
            builder.setPositiveButton("Visit", ((dialog, which) -> {
                Intent intent = new Intent(MainActivity.this, WebsiteActivity.class);
                intent.putExtra("url", "https://google.com");
                startActivity(intent);
            }));
//            builder.setCancelable(true);
            builder.create().show();
        });

        Utils.getInstance(this);
    }

    private void initViews() {
        btnAllBooks = findViewById(R.id.btnAllBooks);
        btnAlreadyRead = findViewById(R.id.btnAlreadyRead);
        btnWantToRead = findViewById(R.id.btnWantToRead);
        btnCurrentlyReading = findViewById(R.id.btnCurrentlyReading);
        btnFavorite = findViewById(R.id.btnFavorite);
        btnAbout = findViewById(R.id.btnAbout);
    }
}