package io.github.zackyl.helloworld

import android.content.Context
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout

class MainActivity : AppCompatActivity() {
    private lateinit var mainLayout: ConstraintLayout
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mainLayout = findViewById(R.id.constraintLayout)
    }

    fun onBtnClick(view: View) {
        val firstName: EditText = findViewById(R.id.firstNametext)
        val lastName: EditText = findViewById(R.id.lastNameText)
        val emailText: EditText = findViewById(R.id.emailText)

        val firstNameView = findViewById<TextView>(R.id.firstNameView)
        firstNameView.text = "First Name: " + firstName.text.toString()

        val lastNameView = findViewById<TextView>(R.id.lastNameView)
        lastNameView.text = "Last Name: " + lastName.text.toString()

        val emailView = findViewById<TextView>(R.id.emailView)
        emailView.text = "Email: " + emailText.text.toString()
        val imm: InputMethodManager =
            getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(mainLayout.windowToken, 0)
    }
}