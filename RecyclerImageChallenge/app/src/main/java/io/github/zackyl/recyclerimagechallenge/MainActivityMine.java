//package io.github.zackyl.recyclerimagechallenge;
//
//import android.os.Bundle;
//import android.view.View;
//import android.widget.Button;
//import android.widget.CheckBox;
//import android.widget.EditText;
//import android.widget.RadioGroup;
//import android.widget.Spinner;
//import android.widget.Toast;
//
//import androidx.appcompat.app.AppCompatActivity;
//
//import com.google.android.material.snackbar.Snackbar;
//
//public class MainActivityMine extends AppCompatActivity {
//    Button btnPickImage, btnRegister;
//    EditText editTextName, editTextEmail, editTextPassword, editTextConfirmPassword;
//    RadioGroup rgGender;
//    Spinner spinnerCountry;
//    CheckBox checkboxAgree;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//        btnPickImage = findViewById(R.id.btnPickImage);
//        btnRegister = findViewById(R.id.btnRegister);
//        editTextName = findViewById(R.id.editTextName);
//        editTextEmail = findViewById(R.id.editTextEmail);
//        editTextPassword = findViewById(R.id.editTextPassword);
//        editTextConfirmPassword = findViewById(R.id.editTextPassword2);
//        rgGender = findViewById(R.id.rgGender);
//        spinnerCountry = findViewById(R.id.spinner_countries);
//        checkboxAgree = findViewById(R.id.checkboxAgree);
//
//        View rootlayout = findViewById(R.id.root_layout);
//
//        btnPickImage.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(MainActivityMine.this, "register clicked", Toast.LENGTH_SHORT).show();
//            }
//        });
//        btnRegister.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (editTextName.getText().toString().isEmpty()) {
//                    Toast.makeText(MainActivityMine.this, "Please fill out your name!", Toast.LENGTH_SHORT).show();
//                }
//                else if (editTextEmail.getText().toString().isEmpty()) {
//                    Toast.makeText(MainActivityMine.this, "Please fill out your email!", Toast.LENGTH_SHORT).show();
//                }
//                else if (editTextPassword.getText().toString().isEmpty()) {
//                    Toast.makeText(MainActivityMine.this, "Please fill out your password!", Toast.LENGTH_SHORT).show();
//                }
//                else if (editTextConfirmPassword.getText().toString().isEmpty()) {
//                    Toast.makeText(MainActivityMine.this, "Please confirm your password!", Toast.LENGTH_SHORT).show();
//                }
//                else if (rgGender.getCheckedRadioButtonId() == -1) {
//                    Toast.makeText(MainActivityMine.this, "Please select a gender!", Toast.LENGTH_SHORT).show();
//                }
//                else if (!checkboxAgree.isChecked()) {
//                    Toast.makeText(MainActivityMine.this, "Please check the checkbox!", Toast.LENGTH_SHORT).show();
//                } else {
////                    Toast.makeText(MainActivity.this, "Registered!!!", Toast.LENGTH_SHORT).show();
//                    Snackbar snackbar = Snackbar
//                            .make(rootlayout, "Registered!", Snackbar.LENGTH_LONG);
////                            .setAction("UNDO", new View.OnClickListener() {
////                                @Override
////                                public void onClick(View view) {
////                                    Snackbar snackbar1 = Snackbar.make(coordinatorLayout, "Message is restored!", Snackbar.LENGTH_SHORT);
////                                    snackbar1.show();
////                                }
////                            });
//
//                    snackbar.show();
//
//                }
//            }
//        });
//    }
//}