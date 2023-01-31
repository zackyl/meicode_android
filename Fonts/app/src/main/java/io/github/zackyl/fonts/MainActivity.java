package io.github.zackyl.fonts;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;

import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private TextView textHello;
    private Button btnChangeFont;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textHello = findViewById(R.id.txtHello);
        btnChangeFont = findViewById(R.id.btnChangeFont);

//        Typeface typeface = getResources().getFont(R.font.big_shoulder);
        Typeface typeface = ResourcesCompat.getFont(this, R.font.pacifico);

        btnChangeFont.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textHello.setTypeface(typeface);
            }
        });

    }
}