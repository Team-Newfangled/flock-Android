package com.example.kkirikkiri.view

import android.app.Activity
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.result.contract.ActivityResultContracts
import androidx.databinding.DataBindingUtil
import com.example.kkirikkiri.R
import com.example.kkirikkiri.databinding.ActivityLoginBinding
import com.example.kkirikkiri.viewmodel.LoginModel
import com.google.android.gms.auth.api.signin.*
import com.google.android.gms.common.Scopes
import com.google.android.gms.common.api.Scope
import com.google.android.gms.tasks.Task

class LoginActivity : AppCompatActivity() {

    private lateinit var binding:ActivityLoginBinding
    private var mGoogleSignInClient : GoogleSignInClient? = null
    private val model = LoginModel()

    private val redirect : Uri = Uri.parse("https://accounts.google.com/o/oauth2/v2/auth/oauthchooseaccount?client_id=1065774488617-bqqnvgv8fi2ghqgq17pk3tshpmdalur9.apps.googleusercontent.com&redirect_uri=http%3A%2F%2Flocalhost%3A3000%2Fredirect&response_type=code&scope=https%3A%2F%2Fwww.googleapis.com%2Fauth%2Fuserinfo.profile&flowName=GeneralOAuthFlow")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login)

        binding.gitLogin.setOnClickListener {
            model.signInResult()
            startActivity(Intent(applicationContext, MainActivity::class.java))
        }

        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestScopes(Scope(Scopes.DRIVE_APPFOLDER))
            .requestServerAuthCode(getString(R.string.server_client_id))
            .build()

        val launcher = registerForActivityResult(ActivityResultContracts.StartActivityForResult())
        { result ->
            if (result.resultCode == Activity.RESULT_OK){
                Intent(this, MainActivity::class.java).run { startActivity(this) }
            }else {
                Log.e("asd", result.data!!.data.toString() + " " + result.resultCode + " " + result.toString())
            }
        }


        mGoogleSignInClient = GoogleSignIn.getClient(this, gso)

        binding.googleLogin.setOnClickListener {
            val signInIntent: Intent = mGoogleSignInClient!!.signInIntent
            launcher.launch(signInIntent)
        }

    }

}