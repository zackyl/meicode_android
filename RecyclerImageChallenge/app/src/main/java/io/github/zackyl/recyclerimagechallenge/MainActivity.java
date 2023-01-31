package io.github.zackyl.recyclerimagechallenge;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    Button btnPickImage, btnRegister;
    TextView txtViewWarnName, txtViewWarnEmail, txtViewWarnPassword, txtViewWarnConfirmPassword;
    EditText editTextName, editTextEmail, editTextPassword, editTextConfirmPassword;
    RadioGroup rgGender;
    Spinner spinnerCountry;
    CheckBox checkboxAgree;
    ConstraintLayout rootlayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
        btnPickImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "register clicked", Toast.LENGTH_SHORT).show();
            }
        });
        btnRegister.setOnClickListener(makeRegisterOnClickListener());
    }

    private View.OnClickListener makeRegisterOnClickListener() {
        Log.d(TAG, "register onclick started");
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!validateInput()) {
                    return;
                }
                txtViewWarnName.setVisibility(View.GONE);
                txtViewWarnEmail.setVisibility(View.GONE);
                txtViewWarnPassword.setVisibility(View.GONE);
                txtViewWarnConfirmPassword.setVisibility(View.GONE);

                String name = editTextName.getText().toString();
                String email = editTextEmail.getText().toString();
                String country = spinnerCountry.getSelectedItem().toString();
                String gender = "";
                switch (rgGender.getCheckedRadioButtonId()) {
                    case R.id.rbMale:
                        gender = "Male";
                        break;
                    case R.id.rbFemale:
                        gender = "Female";
                        break;
                    case R.id.rbOther:
                        gender = "Other";
                        break;
                    default:
                        gender = "Unknown";
                        break;
                }
                String snackText = "Name: " + name + "\nEmail: " + email + "\nGender:" + gender + "\nCountry: " + country;
                Log.d(TAG, "showSnackBar: show snack bar text:\n" + snackText);
                Snackbar snackbar = Snackbar
                        .make(rootlayout, snackText, Snackbar.LENGTH_LONG)
                        .setAction("Dismiss", new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                editTextName.setText("");
                                editTextEmail.setText("");
                                editTextPassword.setText("");
                                editTextConfirmPassword.setText("");
                            }
                        });
                snackbar.show();
            }
        };
    }

    private boolean validateInput() {
        Log.d(TAG, "validateData: started");
        if (editTextName.getText().toString().isEmpty()) {
            txtViewWarnName.setVisibility(View.VISIBLE);
            txtViewWarnName.setText("Enter your name");
        } else if (editTextEmail.getText().toString().isEmpty()) {
            txtViewWarnEmail.setVisibility(View.VISIBLE);
            txtViewWarnEmail.setText("Enter your password");
        } else if (editTextPassword.getText().toString().isEmpty()) {
            txtViewWarnPassword.setVisibility(View.VISIBLE);
            txtViewWarnPassword.setText("Enter your password");
        } else if (editTextConfirmPassword.getText().toString().isEmpty()) {
            txtViewWarnConfirmPassword.setVisibility(View.VISIBLE);
            txtViewWarnConfirmPassword.setText("Confirm your password");
        } else if (!editTextPassword.getText().toString().equals(editTextConfirmPassword.getText().toString())) {
            txtViewWarnConfirmPassword.setVisibility(View.VISIBLE);
            txtViewWarnConfirmPassword.setText("Password doesn't match");
        } else if (rgGender.getCheckedRadioButtonId() == -1) {
            Toast.makeText(MainActivity.this, "Please select a gender!", Toast.LENGTH_SHORT).show();
        } else if (!checkboxAgree.isChecked()) {
            Toast.makeText(MainActivity.this, "Please check the checkbox!", Toast.LENGTH_SHORT).show();
        } else {
            return true;
        }
        return false;
    }

    private void initViews() {
        Log.d(TAG, "initViews: started");
        rootlayout = findViewById(R.id.root_layout);

        btnPickImage = findViewById(R.id.btnPickImage);
        btnRegister = findViewById(R.id.btnRegister);
        editTextName = findViewById(R.id.editTextName);
        editTextEmail = findViewById(R.id.editTextEmail);
        editTextPassword = findViewById(R.id.editTextPassword);
        editTextConfirmPassword = findViewById(R.id.editTextPassword2);
        rgGender = findViewById(R.id.rgGender);
        spinnerCountry = findViewById(R.id.spinner_countries);
        checkboxAgree = findViewById(R.id.checkboxAgree);

        txtViewWarnName = findViewById(R.id.txtWarnName);
        txtViewWarnEmail = findViewById(R.id.txtWarnEmail);
        txtViewWarnPassword = findViewById(R.id.txtWarnPass);
        txtViewWarnConfirmPassword = findViewById(R.id.txtWarnPass2);
    }
}