package com.lcwd.loginandsignupwithfirebase

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.lcwd.loginandsignupwithfirebase.databinding.ActivityWelcomeScreenBinding

class WelcomeScreen : AppCompatActivity() {

private val binding :ActivityWelcomeScreenBinding by  lazy{
    ActivityWelcomeScreenBinding.inflate(layoutInflater)
}
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        Handler(Looper.getMainLooper()).postDelayed({
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }, 3000)


        val welcomeText="Welcome"
       val spannableString=SpannableString(welcomeText)
        val redColor = ForegroundColorSpan(Color.parseColor("#FF0000"))
        val brownColor= ForegroundColorSpan(Color.parseColor("#312222"))


        spannableString.setSpan(redColor, 0, 5, 0)
        spannableString.setSpan(brownColor, 5, welcomeText.length, 0)

//        spannableString.setSpan(ForegroundColorSpan(Color.parseColor("#FF0000"),0,5,0))
//       spannableString.setSpan(ForegroundColorSpan(Color.parseColor("#FF0000"),5,welcomeText.length,0))

        binding.welcomeText.text=spannableString

    }
}