package com.example.kkirikkiri.view

import android.app.Activity
import android.content.ComponentName
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.browser.customtabs.CustomTabColorSchemeParams
import androidx.browser.customtabs.CustomTabsClient
import androidx.browser.customtabs.CustomTabsIntent
import androidx.browser.customtabs.CustomTabsServiceConnection
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import com.example.kkirikkiri.R
import com.example.kkirikkiri.databinding.ActivityLoginBinding
import com.example.kkirikkiri.viewmodel.LoginModel
import com.google.android.gms.auth.api.signin.*
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.OnSuccessListener
import com.google.android.gms.tasks.Task


class LoginActivity: AppCompatActivity() {

    private lateinit var binding:ActivityLoginBinding
    private var mGoogleSignInClient : GoogleSignInClient? = null
    private val model = LoginModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login)

        binding.gitLogin.setOnClickListener {
            // https://accounts.google.com/o/oauth2/v2/auth/oauthchooseaccount?client_id=1065774488617-bqqnvgv8fi2ghqgq17pk3tshpmdalur9.apps.googleusercontent.com&redirect_uri=http%3A%2F%2Flocalhost%3A3000%2Fredirect&response_type=code&scope=https%3A%2F%2Fwww.googleapis.com%2Fauth%2Fuserinfo.profile&flowName=GeneralOAuthFlow
            model.signInResult("4/0ARtbsJrdfrXvI8lEgpYIacqWenoCX2MrpLm7K75VPOOnkTWNScdJfLGSKZOg_kK-0HVXwg")
            startActivity(Intent(applicationContext, MainActivity::class.java))
        }

        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestServerAuthCode(getString(R.string.server_client_id))
            .requestEmail()
            .build()

        mGoogleSignInClient = GoogleSignIn.getClient(this, gso)


//        val launcher = registerForActivityResult(ActivityResultContracts.StartActivityForResult())
//        { result ->
//            if (result.resultCode == Activity.RESULT_OK){
//                val task = GoogleSignIn.getSignedInAccountFromIntent(result.data)
//                val code = task.result?.serverAuthCode
//                code!!.replace("%2F", "/")
//                model.signInResult(code)
//                Intent(this, MainActivity::class.java).run { startActivity(this) }
//            }else {
//                mGoogleSignInClient!!.silentSignIn().addOnCompleteListener {
//                    handleSignInResult(it)
//                }
//                Log.e("asd", result.data!!.data.toString() + " " + result.resultCode + " " + result.toString())
//            }
//        }

        binding.googleLogin.setOnClickListener {
//            val signInIntent: Intent = mGoogleSignInClient!!.signInIntent
//            launcher.launch(signInIntent)
            mGoogleSignInClient!!.silentSignIn().addOnCompleteListener {
                handleSignInResult(it)
            }
        }

    }

    private fun handleSignInResult(completedTask: Task<GoogleSignInAccount>) {
        try {
            val account = completedTask.getResult(ApiException::class.java)
            val idToken = account?.serverAuthCode
            Log.e("id", idToken.toString())
        } catch (e: ApiException) { Log.w("exception", "handleSignInResult:error, " + e.printStackTrace() + ", " + e.message + ", " + e.cause); }
    }
}