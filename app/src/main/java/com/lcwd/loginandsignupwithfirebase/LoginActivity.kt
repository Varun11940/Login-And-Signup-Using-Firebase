package com.lcwd.loginandsignupwithfirebase

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.lcwd.loginandsignupwithfirebase.databinding.ActivityLoginBinding
import com.lcwd.loginandsignupwithfirebase.databinding.ActivitySignUpBinding

class LoginActivity : AppCompatActivity() {

    private val binding: ActivityLoginBinding by lazy {
        ActivityLoginBinding.inflate(layoutInflater)
    }

    private lateinit var auth: FirebaseAuth

    override fun onStart() {
        super.onStart()
        val currentUser :FirebaseUser ? =auth.currentUser
        if(currentUser!=null){
            startActivity(Intent(this, MainActivity::class.java))
            finish()

        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        auth = FirebaseAuth.getInstance()



        binding.loginButton.setOnClickListener {
            val userName = binding.name.text.toString()
            val password = binding.Password.text.toString()
            if (userName.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Please fill all the details", Toast.LENGTH_LONG).show()

            } else {
                auth.signInWithEmailAndPassword(userName, password)
                    .addOnCompleteListener(this) { task ->
                        if (task.isSuccessful) {
                            Toast.makeText(this, "Login Successful", Toast.LENGTH_LONG).show()
                            startActivity(Intent(this, MainActivity::class.java))
                            finish()


                        } else {
                            Toast.makeText(
                                this,
                                "Login Failed: ${task.exception?.message}",
                                Toast.LENGTH_LONG
                            ).show()

                        }

                    }
            }
            binding.signUpButton.setOnClickListener {
                startActivity(Intent(this, SignUpActivity::class.java))
                finish()
            }

        }
    }
}