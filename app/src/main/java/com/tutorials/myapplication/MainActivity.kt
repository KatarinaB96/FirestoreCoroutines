package com.tutorials.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.google.firebase.FirebaseApp
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.tutorials.myapplication.databinding.ActivityMainBinding
import kotlinx.coroutines.*
import kotlinx.coroutines.tasks.await
import kotlin.system.measureTimeMillis

data class Person(
    val name: String = "",
    val age: Int = -1
)

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)


        setContentView(binding.root)
        val tutorialDocumented = Firebase.firestore.collection("coroutines").document("7FbV6lfrggxJugEFf2xs")
        val kejtica = Person("Kejt", 27)
        GlobalScope.launch(Dispatchers.IO) {
            delay(1000)
            tutorialDocumented.set(kejtica).await()
            val person = tutorialDocumented.get().await().toObject(Person::class.java)
            withContext(Dispatchers.Main) {
                binding.tvData.text = person.toString()
            }
        }

    }

//    suspend fun networkCall1(): String {
//        delay(3000)
//        return "ans 1"
//    }
//
//    suspend fun networkCall2(): String {
//        delay(3000)
//        return "ans 2"
//    }
}