package com.lcwd.loginandsignupwithfirebase

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.firebase.auth.FirebaseAuth
import com.lcwd.loginandsignupwithfirebase.databinding.ActivitySignUpBinding

class SignUpActivity : AppCompatActivity() {

    private val binding :ActivitySignUpBinding by lazy{
        ActivitySignUpBinding.inflate(layoutInflater)
    }

    private lateinit var auth:FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        // Initialize Firebase Auth
        auth= FirebaseAuth.getInstance()


        binding.signInButton.setOnClickListener{
            startActivity(Intent(this,LoginActivity::class.java))
            finish()
        }

        binding.registerButton.setOnClickListener{
//            receiving user details
            val email=binding.email.text.toString()
            val userName=binding.name.text.toString()
            val password=binding.password.text.toString()
            val repeatePassword=binding.repeatPassword.text.toString()

//            checking whther user has provided input or not
            if(email.isEmpty() || userName.isEmpty() || password.isEmpty() || repeatePassword.isEmpty()){
                Toast.makeText(this,"Please fill all  the details",Toast.LENGTH_LONG).show()
            }
            else if(password!=repeatePassword){
                Toast.makeText(this,"Repeat Password Must be same",Toast.LENGTH_LONG).show()

            }
            else{
                auth.createUserWithEmailAndPassword(email,password)
                    .addOnCompleteListener(this){task->
                          if(task.isSuccessful){
                              Toast.makeText(this,"Registration Successful",Toast.LENGTH_LONG).show()
                              startActivity(Intent(this,LoginActivity::class.java))
                              finish()

                          }
                        else{
                              Toast.makeText(this,"Registration Failed: ${task.exception?.message}",Toast.LENGTH_LONG).show()

                          }
                    }
            }

        }

    }
}