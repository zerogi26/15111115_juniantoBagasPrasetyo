package com.umby.hello.uas15111115

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Toast
import com.umby.hello.uisample.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        loginButton.isEnabled = false
        loginButton.setOnClickListener {
            val username = usernameField.text.toString()
            val password = passwordField.text.toString()

            Toast.makeText(this, "username: $username \npassword: $password", Toast.LENGTH_LONG).show()
            if (username.length >= 6 && password.length >= 6) {
                val intent = Intent(this, LandingActivity::class.java)
                intent.putExtra("username", username)
                startActivity(intent)
            }
        }

        val textChangeWatcher: TextWatcher = object: TextWatcher {
            override fun afterTextChanged(text: Editable?) {
                text?.let {
                    if (it.toString().length < 6) {
                        usernameField.error = "Username harus lebih dari atau sama dengan 6 karakter"
                        loginButton.isEnabled = false
                    } else {
                        loginButton.isEnabled = true
                    }
                }
            }

            override fun beforeTextChanged(text: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(text: CharSequence?, start: Int, before: Int, count: Int) {

            }

        }

        usernameField.addTextChangedListener(textChangeWatcher)
    }
}
