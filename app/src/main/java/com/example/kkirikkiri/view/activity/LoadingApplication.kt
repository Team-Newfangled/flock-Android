package com.example.kkirikkiri.view.activity

import android.app.Activity
import android.app.Application
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatDialog
import com.example.kkirikkiri.R

class LoadingApplication : Application() {


    companion object {
        private var loadingApplication : LoadingApplication? = null
        lateinit var dialog : AppCompatDialog


        fun getInstance() : LoadingApplication {
            if (loadingApplication == null) {
                loadingApplication = LoadingApplication().getLoading()
            }
            return loadingApplication!!
        }
    }

    private fun getLoading() : LoadingApplication{
        return this
    }

    override fun onCreate() {
        super.onCreate()
        loadingApplication = this
    }

    fun progressOn(activity: Activity) {
        if (activity.isFinishing) {
            return
        }

        dialog = AppCompatDialog(activity)
        dialog.setCancelable(false)
        dialog.window?.setBackgroundDrawable(ColorDrawable(android.graphics.Color.TRANSPARENT))
        dialog.setContentView(R.layout.loding)
        dialog.show()
    }

    fun progressOff() {
        if (dialog.isShowing) { dialog.dismiss() }
    }
}