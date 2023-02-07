package com.example.kkirikkiri.view.activity

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.result.contract.ActivityResultContracts
import com.example.kkirikkiri.R
import com.example.kkirikkiri.databinding.ActivityLoginBinding
import com.example.kkirikkiri.view.loading.LoadingApplication
import com.example.kkirikkiri.viewmodel.LoginModel
import com.google.android.gms.auth.api.signin.*


class LoginActivity: BaseActivity<ActivityLoginBinding>({ ActivityLoginBinding.inflate(it) }) {

    private var mGoogleSignInClient : GoogleSignInClient? = null
    private val model = LoginModel()

    private val loading = LoadingApplication.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        model.loading = loading
        observe()

        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestServerAuthCode(getString(R.string.test))
            .build()

        mGoogleSignInClient = GoogleSignIn.getClient(this, gso)
        mGoogleSignInClient?.signOut()

        val launcher = registerForActivityResult(ActivityResultContracts.StartActivityForResult())
        { result ->
            if (result.resultCode == Activity.RESULT_OK){
                val task = GoogleSignIn.getSignedInAccountFromIntent(result.data)
                val code = task.result?.serverAuthCode
                code!!.replace("%2F", "/")
                model.signInResult(code)
                loading.progressOn(this)
            }else {
                Log.e("asd", result.data!!.data.toString() + " " + result.resultCode + " " + result.toString())
            }
        }

        binding.googleLogin.setOnClickListener {
            val signInIntent: Intent = mGoogleSignInClient!!.signInIntent
            launcher.launch(signInIntent)
        }
    }

    private fun observe() {
        model.userid.observe(this) {
            Intent(this, MainActivity::class.java).run { startActivity(this) }
        }
    }

    override fun onRestart() {
        super.onRestart()
        mGoogleSignInClient?.signOut()
    }

}