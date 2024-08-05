package com.example.hello_app

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.hello_app.ui.theme.HelloappTheme
import io.getunleash.android.DefaultUnleash
import io.getunleash.android.UnleashConfig
import io.getunleash.android.data.UnleashContext

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initUnleash(applicationContext)
        enableEdgeToEdge()
        setContent {
            HelloappTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    HelloappTheme {
        Greeting("Android")
    }
}

fun initUnleash(context: Context){
    val unleash = DefaultUnleash(
        context,
        unleashConfig = UnleashConfig.newBuilder(
            appName = "hello-app"
        )
            .proxyUrl("http://todo.後でGCEの方で決めてもらう")
            .clientKey("<client-side SDK API key> 多分これもGCEの方でセットアップ")
            .pollingStrategy.interval(3000) // 3 secs is just for testing purposes, not recommended for production
            .metricsStrategy.interval(3000) // 3 secs is just for testing purposes, not recommended for production
            .build()
    )

    val initialContext = UnleashContext.newBuilder()
        .userId("However you resolve your userid")
        .sessionId("However you resolve your session id")
        .build()
    unleash.setContext(initialContext)
}