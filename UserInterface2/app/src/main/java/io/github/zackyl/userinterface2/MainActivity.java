package io.github.zackyl.userinterface2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView txtHello;
    private EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = findViewById(R.id.editTextName);
        txtHello = findViewById(R.id.txtHello);

        Button btnHello = findViewById(R.id.btnHello);
        btnHello.setOnClickListener(this);
        editText.setOnClickListener(this);
//        btnHello.setOnLongClickListener(new View.OnLongClickListener() {
//            @Override
//            public boolean onLongClick(View v) {
//                Toast.makeText(MainActivity.this, "long click", Toast.LENGTH_LONG).show();
//                return true;
//            }
//        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnHello:
//                Toast.makeText(this, "Hello button clicked", Toast.LENGTH_SHORT).show();
                txtHello.setText("Hello " + editText.getText().toString());
                break;
            case R.id.editTextName:
                Toast.makeText(this, "Attempting to type something", Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }
    }
}