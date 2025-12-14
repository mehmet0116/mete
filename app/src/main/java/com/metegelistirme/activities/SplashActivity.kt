package com.metegelistirme.activities

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import com.metegelistirme.MeteApplication
import com.metegelistirme.R
import com.metegelistirme.database.entities.ProgressEntity
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class SplashActivity : AppCompatActivity() {
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        
        // 2 saniye sonra ana menüye geç
        Handler(Looper.getMainLooper()).postDelayed({
            val intent = Intent(this, MainMenuActivity::class.java)
            startActivity(intent)
            finish()
        }, 2000)

        // Room veritabanını minimal şekilde kullanarak kapt stublarının doğru üretilmesini sağla
        GlobalScope.launch {
            val dao = MeteApplication.instance.database.progressDao()
            dao.insert(
                ProgressEntity(
                    childName = "Test",
                    module = "Splash",
                    level = 1,
                    score = 0,
                    timestamp = System.currentTimeMillis()
                )
            )
        }
    }
}