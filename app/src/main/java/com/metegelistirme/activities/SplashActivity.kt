package com.metegelistirme.activities

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.metegelistirme.R
import com.metegelistirme.ui.theme.MeteEgitimUygulamasiTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import timber.log.Timber

@AndroidEntryPoint
class SplashActivity : ComponentActivity() {
    
    private var isAppReady = false
    private var isAnimationComplete = false
    
    override fun onCreate(savedInstanceState: Bundle?) {
        // Install splash screen before super.onCreate
        val splashScreen = installSplashScreen()
        
        super.onCreate(savedInstanceState)
        
        // Keep splash screen visible until app is ready
        splashScreen.setKeepOnScreenCondition { !isAppReady }
        
        // Initialize app in background
        initializeApp()
        
        setContent {
            MeteEgitimUygulamasiTheme {
                SplashScreenContent(
                    onAnimationComplete = {
                        isAnimationComplete = true
                        checkAndNavigate()
                    }
                )
            }
        }
    }
    
    private fun initializeApp() {
        // Perform initialization in background
        Thread {
            try {
                // Simulate initialization tasks
                Thread.sleep(500) // Reduced from typical 2-3 seconds
                
                // Preload essential resources
                preloadResources()
                
                // Check database
                checkDatabase()
                
                // Mark app as ready
                isAppReady = true
                checkAndNavigate()
                
            } catch (e: Exception) {
                Timber.e(e, "Error during app initialization")
                // Even if there's an error, proceed to main screen
                isAppReady = true
                checkAndNavigate()
            }
        }.start()
    }
    
    private fun preloadResources() {
        // Preload common resources to reduce UI lag
        try {
            // Preload theme
            resources.getColor(R.color.primary_color, null)
            resources.getColor(R.color.secondary_color, null)
            
            // Preload common drawables
            resources.getDrawable(R.drawable.ic_launcher_foreground, null)
            
        } catch (e: Exception) {
            Timber.e(e, "Error preloading resources")
        }
    }
    
    private fun checkDatabase() {
        // Check if database needs migration or setup
        try {
            // This will trigger database creation if needed
            // AppDatabase.getInstance(this)
        } catch (e: Exception) {
            Timber.e(e, "Database check failed")
        }
    }
    
    private fun checkAndNavigate() {
        if (isAppReady && isAnimationComplete) {
            navigateToMain()
        }
    }
    
    private fun navigateToMain() {
        runOnUiThread {
            val intent = Intent(this, MainMenuActivity::class.java)
            startActivity(intent)
            finish()
            
            // Apply smooth transition
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
        }
    }
    
    @Composable
    private fun SplashScreenContent(onAnimationComplete: () -> Unit) {
        val coroutineScope = rememberCoroutineScope()
        
        LaunchedEffect(Unit) {
            coroutineScope.launch {
                // Minimum display time for branding
                delay(1500) // Reduced for better UX
                onAnimationComplete()
            }
        }
        
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(
                            Color(0xFF4FC3F7), // Primary color
                            Color(0xFF29B6F6)  // Lighter shade
                        )
                    )
                ),
            contentAlignment = Alignment.Center
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier.padding(24.dp)
            ) {
                // Animated logo
                Image(
                    painter = painterResource(id = R.drawable.ic_launcher_foreground),
                    contentDescription = stringResource(R.string.app_name),
                    modifier = Modifier.size(150.dp),
                    contentScale = ContentScale.Fit
                )
                
                Spacer(modifier = Modifier.height(24.dp))
                
                // App name with animation
                Text(
                    text = stringResource(R.string.app_name),
                    style = MaterialTheme.typography.headlineLarge.copy(
                        fontWeight = FontWeight.Bold,
                        fontSize = 36.sp
                    ),
                    color = Color.White
                )
                
                Spacer(modifier = Modifier.height(8.dp))
                
                // Tagline
                Text(
                    text = "Eğlenceli Eğitim Dünyası",
                    style = MaterialTheme.typography.titleMedium,
                    color = Color.White.copy(alpha = 0.9f)
                )
                
                // Loading indicator (simplified for performance)
                Spacer(modifier = Modifier.height(32.dp))
                Box(
                    modifier = Modifier
                        .width(100.dp)
                        .height(4.dp)
                        .background(Color.White.copy(alpha = 0.3f))
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxHeight()
                            .width(30.dp)
                            .background(Color.White)
                    )
                }
            }
        }
    }
    
    override fun onDestroy() {
        super.onDestroy()
        // Clean up any resources
        Timber.d("SplashActivity destroyed")
    }
}