package com.example.kkirikkiri.view

import android.app.Activity
import android.app.AlertDialog
import android.content.ComponentName
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.support.customtabs.ICustomTabsService
import android.util.Log
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.activity.result.contract.ActivityResultContracts
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
import com.google.android.gms.common.Scopes
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.common.api.Scope
import com.google.android.gms.tasks.Task

class LoginActivity: AppCompatActivity() {

    private lateinit var binding:ActivityLoginBinding
    private var mGoogleSignInClient : GoogleSignInClient? = null
    private val model = LoginModel()
    private val USER_AGENT = "Mozilla/5.0 (Linux; Android 4.1.1; Galaxy Nexus Build/JRO03C) AppleWebKit/535.19 (KHTML, like Gecko) Chrome/18.0.1025.166 Mobile Safari/535.19"

    private val redirect : Uri = Uri.parse("https://accounts.google.com/o/oauth2/v2/auth/oauthchooseaccount?client_id=1065774488617-bqqnvgv8fi2ghqgq17pk3tshpmdalur9.apps.googleusercontent.com&redirect_uri=http%3A%2F%2Flocalhost%3A3000%2Fredirect&response_type=code&scope=https%3A%2F%2Fwww.googleapis.com%2Fauth%2Fuserinfo.profile&flowName=GeneralOAuthFlow")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login)

        binding.gitLogin.setOnClickListener {
            startActivity(Intent(applicationContext, MainActivity::class.java))
        }

        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestServerAuthCode(getString(R.string.server_client_id))
            .requestEmail()
            .build()

//        val launcher = registerForActivityResult(ActivityResultContracts.StartActivityForResult())
//        { result ->
//            if (result.resultCode == Activity.RESULT_OK){
//                val task = GoogleSignIn.getSignedInAccountFromIntent(result.data)
//                val code = task.result.serverAuthCode
//                code!!.replace("%2F", "/")
//                print(code)
//                model.signInResult(code)
//                Intent(this, MainActivity::class.java).run { startActivity(this) }
//            }else {
//                Log.e("asd", result.data!!.data.toString() + " " + result.resultCode + " " + result.toString())
//            }
//        }


        mGoogleSignInClient = GoogleSignIn.getClient(this, gso)

        binding.googleLogin.setOnClickListener {
//            val signInIntent: Intent = mGoogleSignInClient!!.signInIntent
//            val intent = Intent(Intent.ACTION_VIEW, redirect)
//            launcher.launch(intent)
            webViewDialog()
        }

    }

    private fun webViewDialog() {
        val connection = object : CustomTabsServiceConnection() {
            override fun onServiceDisconnected(p0: ComponentName?) {
                Log.e("URL", intent.data.toString())
            }

            override fun onCustomTabsServiceConnected(
                name: ComponentName,
                client: CustomTabsClient
            ) {
                val intent = CustomTabsIntent.Builder()
                val params = CustomTabColorSchemeParams.Builder()
                params.setToolbarColor(ContextCompat.getColor(this@LoginActivity, R.color.kkirikkiri))
                intent.setDefaultColorSchemeParams(params.build())

                val builder = intent.build()
                client.warmup(0L)
                builder.launchUrl(this@LoginActivity, redirect)
            }
        }
        CustomTabsClient.bindCustomTabsService(this@LoginActivity, "com.android.chrome",connection)
    }
}