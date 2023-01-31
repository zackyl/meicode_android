package io.github.zackyl.uibasics;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void onHelloButtonClicked(View view) {
        TextView txtWelcome = findViewById(R.id.txtWelcome);
        txtWelcome.setText("Hello again");
    }
}