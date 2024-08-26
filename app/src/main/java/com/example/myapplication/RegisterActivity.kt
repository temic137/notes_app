//package com.example.myapplication
//
//import android.content.Intent
//import android.os.Bundle
//import android.widget.Button
//import android.widget.EditText
//import androidx.activity.ComponentActivity
//import android.widget.Toast
//
//
//class RegisterActivity: ComponentActivity() {
//    private lateinit var usernameInput: EditText
//    private lateinit var emailInput: EditText
//    private lateinit var passwordInput: EditText
//    private lateinit var registerButton: Button
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_register)
//
//        usernameInput = findViewById(R.id.username_input)
//        emailInput = findViewById(R.id.email_input)
//        passwordInput = findViewById(R.id.password_input)
//        registerButton = findViewById(R.id.register_button)
//
//        registerButton.setOnClickListener {
//            val username = usernameInput.text.toString()
//            val email = emailInput.text.toString()
//            val password = passwordInput.text.toString()
//
//            // Validate input and create a new user account
//            if (isValidInput(username, email, password)) {
//                createUserAccount(username, email, password)
//                // Navigate to login screen or main app screen
//            } else {
//                // Display error message
//            }
//        }
//        // After successful registration
//        val intent = Intent(this, LoginActivity::class.java)
//        startActivity(intent)
//        finish()
//    }
//
//    private fun isValidInput(username: String, email: String, password: String): Boolean {
//        // Implement validation logic
//        return username.isNotEmpty() && email.isNotEmpty() && password.isNotEmpty()
//    }
//
//    private fun createUserAccount(username: String, email: String, password: String) {
//        // Implement account creation logic
//        // Save user data to database or shared preferences
//        val user = User(username, email, password)
//
//        Toast.makeText(this, "Account created successfully", Toast.LENGTH_SHORT).show()
//        // Optionally, navigate back to login
//    }
//}
//

package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch

class RegisterActivity : AppCompatActivity() {
    private lateinit var usernameInput: EditText
    private lateinit var emailInput: EditText
    private lateinit var passwordInput: EditText
    private lateinit var registerButton: Button
    private lateinit var userDao: UserDao
    private lateinit var loginLink: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        usernameInput = findViewById(R.id.username_input)
        emailInput = findViewById(R.id.email_input)
        passwordInput = findViewById(R.id.password_input)
        registerButton = findViewById(R.id.register_button)
        loginLink = findViewById(R.id.login_link)

        // Initialize Room database
        val db = AppDatabase.getDatabase(this)
        userDao = db.userDao()

        registerButton.setOnClickListener {
            val username = usernameInput.text.toString()
            val email = emailInput.text.toString()
            val password = passwordInput.text.toString()

            // Validate input and create a new user account
            if (username.isNotEmpty() && email.isNotEmpty() && password.isNotEmpty()) {
                val newUser = User(username = username, email = email, password = password)
                lifecycleScope.launch {
                    userDao.insert(newUser)
                    Toast.makeText(this@RegisterActivity, "Account created successfully", Toast.LENGTH_SHORT).show()
                    finish() // Go back to login screen
                }
            } else {
                Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show()
            }
        }
        // Set up register link click listener
        loginLink.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }
    }
}