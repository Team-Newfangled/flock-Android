package com.example.kkirikkiri.view

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.result.contract.ActivityResultContracts
import androidx.databinding.DataBindingUtil
import com.example.kkirikkiri.R
import com.example.kkirikkiri.databinding.ActivityLoginBinding
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task

class LoginActivity : AppCompatActivity() {

    private lateinit var binding:ActivityLoginBinding
    private var mGoogleSignInClient : GoogleSignInClient? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login)

        binding.gitLogin.setOnClickListener { startActivity(Intent(applicationContext,
            MainActivity::class.java)) }

        val launcher = registerForActivityResult(ActivityResultContracts.StartActivityForResult())
        { result ->
            if (result.resultCode == Activity.RESULT_OK){
                val task : Task<GoogleSignInAccount> = GoogleSignIn.getSignedInAccountFromIntent(result.data)
                signInResult(task)
            }else {
                Log.e("실패", "ㅇㅇ 실패했다고 멍청아, $result")
            }
        }

        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.web_client_id))
            .requestServerAuthCode(getString(R.string.web_client_id))
            .requestEmail()
            .build()

        mGoogleSignInClient = GoogleSignIn.getClient(this,gso)

        binding.googleLogin.setOnClickListener {
            val signInIntent: Intent = mGoogleSignInClient!!.signInIntent
            launcher.launch(signInIntent)
        }
    }

    private fun signInResult(task : Task<GoogleSignInAccount>) {
        val account = task.getResult(ApiException::class.java)
        val id = account.id
        val ac = account.account
        val idToken = account.idToken
        val email = account.email
        val displayName = account.displayName
        val familyName = account.familyName
        val givenName = account.givenName
        val grantedScopes = account.grantedScopes
        val serverAuthCode = account.serverAuthCode

        Log.e("로그인 시 자료들","id = ${id.toString()}, account = ${ac.toString()}, IdToken = ${idToken.toString()}, email = ${email.toString()}, displayName = ${displayName.toString()}" +
                ", familyName = ${familyName.toString()}, givenName = ${givenName.toString()}, grantedScopes = ${grantedScopes.toString()}, serverAuthCode = ${serverAuthCode.toString()}")
    }
}