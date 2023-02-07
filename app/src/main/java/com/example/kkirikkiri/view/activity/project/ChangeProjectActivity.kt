package com.example.kkirikkiri.view.activity.project

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import androidx.core.app.ComponentActivity
import androidx.core.content.ContextCompat
import androidx.core.content.PackageManagerCompat
import com.example.kkirikkiri.R
import com.example.kkirikkiri.databinding.ActivityChangeProjectBinding
import com.example.kkirikkiri.module.network.dto.ContentRequest
import com.example.kkirikkiri.module.network.dto.NameRequest
import com.example.kkirikkiri.view.activity.BaseActivity
import com.example.kkirikkiri.viewmodel.ProjectModel

class ChangeProjectActivity : BaseActivity<ActivityChangeProjectBinding>({ ActivityChangeProjectBinding.inflate(it) }) {

    private val model = ProjectModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val id = intent.getIntExtra("id", 0)
        val name = intent.getStringExtra("name")

        var uri = ""

        val imageResult = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == RESULT_OK) {
                val imageUri = result.data?.data
                binding.projectImage.setImageURI(imageUri)
                uri = imageUri.toString()
            }
        }


        binding.projectImage.setOnClickListener {
            val intent = Intent(Intent.ACTION_PICK)
            intent.setDataAndType(
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                "image/*"
            )

            imageResult.launch(intent)
        }

        binding.getProjectImage.setOnClickListener {

            val intent = Intent(Intent.ACTION_PICK)
            intent.setDataAndType(
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                "image/*"
            )

            imageResult.launch(intent)
        }

        binding.addProjectBack.root.setOnClickListener { finish() }
        binding.completeProjectEdit.setOnClickListener {
            if (binding.editProjectName.text.isEmpty()) {
                Log.e("uri", uri.length.toString())
                model.modifyProjectName(id, NameRequest(name.toString()))
                model.modifyProjectCoverImage(id, ContentRequest("https://" + uri) )
            }else {
                model.modifyProjectName(id, NameRequest(binding.editProjectName.text.toString()))
                model.modifyProjectCoverImage(id, ContentRequest("https://" + uri) )
            }
            finish()
        }
    }


}