package com.example.kkirikkiri.view

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.kkirikkiri.R
import com.example.kkirikkiri.databinding.ActivityLoginBinding
import com.example.kkirikkiri.viewmodel.LoginModel
import com.google.android.gms.auth.api.signin.*
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task


class LoginActivity: AppCompatActivity() {

    private val binding by lazy{ ActivityLoginBinding.inflate(layoutInflater) }
    private var mGoogleSignInClient : GoogleSignInClient? = null
    private val model = LoginModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.gitLogin.setOnClickListener {

        }

        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestServerAuthCode(getString(R.string.test))
            .build()

        mGoogleSignInClient = GoogleSignIn.getClient(this, gso)


        val launcher = registerForActivityResult(ActivityResultContracts.StartActivityForResult())
        { result ->
            if (result.resultCode == Activity.RESULT_OK){
                val task = GoogleSignIn.getSignedInAccountFromIntent(result.data)
                val code = task.result?.serverAuthCode
                code!!.replace("%2F", "/")
                model.signInResult(code)
                Intent(this, MainActivity::class.java).run { startActivity(this) }
            }else {
                mGoogleSignInClient!!.silentSignIn().addOnCompleteListener {
                    handleSignInResult(it)
                }
                Log.e("asd", result.data!!.data.toString() + " " + result.resultCode + " " + result.toString())
            }
        }

        binding.googleLogin.setOnClickListener {
            val signInIntent: Intent = mGoogleSignInClient!!.signInIntent
            launcher.launch(signInIntent)
//            mGoogleSignInClient!!.silentSignIn().addOnCompleteListener {
//                handleSignInResult(it)
//            }
        }

    }

    private fun handleSignInResult(completedTask: Task<GoogleSignInAccount>) {
        try {
            val account = completedTask.getResult(ApiException::class.java)
            val idToken = account?.serverAuthCode
            Log.e("id", idToken.toString())
        } catch (e: ApiException) { Log.w("exception", "handleSignInResult:error, " + e.printStackTrace()); }
    }
}