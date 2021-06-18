package com.example.firebaseauth

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.firebaseauth.databinding.ActivityMainBinding
import com.facebook.CallbackManager
import com.facebook.FacebookCallback
import com.facebook.FacebookException
import com.facebook.login.LoginResult

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var callBackManager: CallbackManager
    private val TAG = "MainActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        callBackManager = CallbackManager.Factory.create()
        binding.loginButton.setPermissions(listOf("email, public_profile"))
        binding.loginButton.registerCallback(
            callBackManager,
            object : FacebookCallback<LoginResult> {
                override fun onSuccess(result: LoginResult?) {
                    Log.d(TAG, "onSuccess: ${result!!.accessToken.userId}")
                }

                override fun onCancel() {

                }

                override fun onError(error: FacebookException?) {
                    if (error != null) {
                        Log.d(TAG, "onError: ${error.localizedMessage}")
                    }
                }

            })
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        callBackManager.onActivityResult(requestCode, resultCode, data)
        super.onActivityResult(requestCode, resultCode, data)
    }

}