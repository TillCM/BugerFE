package com.webid.mcdiabetes

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val et_username = findViewById<EditText>(R.id.et_username)
        val et_password = findViewById<EditText>(R.id.et_password)
        val btn_login = findViewById<Button>(R.id.btn_login)
        val btn_register = findViewById<Button>(R.id.btn_regsiter)

       lateinit var auth: FirebaseAuth
        auth = Firebase.auth

        btn_register.setOnClickListener()
        {
            val username = et_username.text.toString()
            val password= et_password.text.toString()
            auth.createUserWithEmailAndPassword(username,password).addOnCompleteListener(this)
            {
                task->

                if(task.isSuccessful)
                {

                    val intent = Intent(this,BurgerOrder::class.java)
                    startActivity(intent)

                }
                else
                {
                   Toast.makeText(this, "Please ensure that you have entered your details " +
                           "correctly", Toast.LENGTH_LONG).show()
                }

            }
                .addOnFailureListener(this)
                {
                    task->

                    Toast.makeText(this,task.message.toString(),Toast.LENGTH_LONG).show()
                }

        }

        btn_login.setOnClickListener()
        {
            val username = et_username.text.toString()
            val password= et_password.text.toString()

            auth.signInWithEmailAndPassword(username,password).addOnCompleteListener(this)
            {
                task->

                if(task.isSuccessful)
                {
                    val intent = Intent(this,BurgerOrder::class.java)
                    startActivity(intent)
                }
                else
                {
                    Toast.makeText(this,"please check your credentials", Toast.LENGTH_LONG)
                        .show()
                }
            }
                .addOnFailureListener(this)
                {
                    task->
                    Toast.makeText(this,task.message.toString(),Toast.LENGTH_LONG).show()
                }

        }


    }
}