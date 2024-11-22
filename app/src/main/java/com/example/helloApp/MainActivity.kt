package com.example.helloApp

import android.content.Context
import android.os.Bundle
import android.util.Log
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
import com.example.helloApp.ui.theme.HelloappTheme
import io.getunleash.android.DefaultUnleash
import io.getunleash.android.UnleashConfig
import io.getunleash.android.data.UnleashContext
import io.getunleash.android.events.UnleashReadyListener

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val unleash = initUnleash(applicationContext)

        unleash.start(
            eventListeners = listOf(object : UnleashReadyListener {
                override fun onReady() {
                    Log.d("DEBUG", "unleash ready")
                }
            })
        )

        enableEdgeToEdge()
        setContent {
            HelloappTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    if (unleash.isEnabled("nameFlag")) {
                        Greeting(
                            name = "world",
                            modifier = Modifier.padding(innerPadding)
                        )
                    } else {
                        Greeting(
                            name = "android",
                            modifier = Modifier.padding(innerPadding)
                        )
                    }
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

fun initUnleash(context: Context): DefaultUnleash {
    val unleash = DefaultUnleash(
        context,
        unleashConfig = UnleashConfig.newBuilder(
            appName = "hello-app"
        )
            .proxyUrl("http://34.42.90.78:3000/proxy")
            .clientKey("test-unleash-secrets")
            .pollingStrategy.interval(3000) // 3 secs is just for testing purposes, not recommended for production
            .metricsStrategy.interval(3000) // 3 secs is just for testing purposes, not recommended for production
            .build()
    )

    val initialContext = UnleashContext.newBuilder()
        .userId("admin")
        .build()
    unleash.setContext(initialContext)

    return unleash
}
