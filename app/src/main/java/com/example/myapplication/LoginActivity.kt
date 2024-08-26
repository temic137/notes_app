//package com.example.myapplication
//
//import android.content.Intent
//import android.content.SharedPreferences
//import android.os.Bundle
//import android.widget.Button
//import android.widget.EditText
//import android.widget.TextView
//import android.widget.Toast
//import androidx.activity.ComponentActivity
//
//class LoginActivity:ComponentActivity() {
//
//    private lateinit var usernameInput: EditText
//    private lateinit var passwordInput: EditText
//    private lateinit var loginButton: Button
//    private lateinit var sharedPreferences: SharedPreferences
//    private lateinit var registerLink: TextView
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_login)
//
//        usernameInput = findViewById(R.id.username_input)
//        passwordInput = findViewById(R.id.password_input)
//        loginButton = findViewById(R.id.login_button)
//        registerLink = findViewById(R.id.register_link)
//
//        sharedPreferences = getSharedPreferences("user_prefs", MODE_PRIVATE)
//
//        loginButton.setOnClickListener {
//            val username = usernameInput.text.toString()
//            val password = passwordInput.text.toString()
//
//            if (username.isNotEmpty() && password.isNotEmpty()) {
//                // Simple login logic
//                val savedPassword = sharedPreferences.getString(username, null)
//                if (savedPassword == password) {
//                    // Successful login
//                    val intent = Intent(this, MainActivity::class.java)
//                    intent.putExtra("username", username)
//                    startActivity(intent)
//                    finish()
//                } else {
//                    Toast.makeText(this, "Invalid credentials", Toast.LENGTH_SHORT).show()
//                }
//            } else {
//                Toast.makeText(this, "Please enter username and password", Toast.LENGTH_SHORT).show()
//            }
//
//        }
//        registerLink.setOnClickListener {
//            val intent = Intent(this, RegisterActivity::class.java)
//            startActivity(intent)
//        }
//
//    }
//
//}
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

class LoginActivity : AppCompatActivity() {
    private lateinit var usernameInput: EditText
    private lateinit var passwordInput: EditText
    private lateinit var loginButton: Button
    private lateinit var registerLink: TextView
    private lateinit var userDao: UserDao

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        // Initialize UI components
        usernameInput = findViewById(R.id.username_input)
        passwordInput = findViewById(R.id.password_input)
        loginButton = findViewById(R.id.login_button)
        registerLink = findViewById(R.id.register_link)

        // Initialize Room database
        val db = AppDatabase.getDatabase(this)
        userDao = db.userDao()

        // Set up login button click listener
        loginButton.setOnClickListener {
            val username = usernameInput.text.toString()
            val password = passwordInput.text.toString()

            // Perform login using Room database
            lifecycleScope.launch {
                val user = userDao.getUser(username, password)
                if (user != null) {
                    // Navigate to MainActivity or another screen
                    val intent = Intent(this@LoginActivity, MainActivity::class.java)
                    startActivity(intent)
                    finish() // Finish this activity
                } else {
                    Toast.makeText(this@LoginActivity, "Invalid credentials", Toast.LENGTH_SHORT).show()
                }
            }
        }

        // Set up register link click listener
        registerLink.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }
    }
}