package com.example

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.lifecycle.ViewModelProvider
import com.example.data.NgoDatabase
import com.example.data.NgoRepository
import com.example.ui.NextSarathiAppContent
import com.example.ui.NgoViewModel
import com.example.ui.NgoViewModelFactory
import com.example.ui.theme.MyApplicationTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        // Initialize Room Database, DAO and Repository pipeline
        val db = NgoDatabase.getDatabase(this)
        val repo = NgoRepository(db.ngoDao())
        
        // Instantiate state controller with custom factory
        val vmFactory = NgoViewModelFactory(repo)
        val viewModel = ViewModelProvider(this, vmFactory)[NgoViewModel::class.java]
        
        enableEdgeToEdge()
        setContent {
            MyApplicationTheme {
                NextSarathiAppContent(viewModel = viewModel)
            }
        }
    }
}

