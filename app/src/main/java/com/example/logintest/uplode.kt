package com.example.logintest

import android.app.Activity
import android.app.ProgressDialog
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.loopj.android.http.AsyncHttpClient
import com.loopj.android.http.JsonHttpResponseHandler
import com.loopj.android.http.RequestParams
import cz.msebera.android.httpclient.Header
import kotlinx.android.synthetic.main.activity_uplode.*
import org.json.JSONObject
import java.io.File
import java.io.FileNotFoundException


class uplode : AppCompatActivity() {
    var selectedImage: Uri? = null
    var picturePath: String? = null
    val REQUEST_CODE = 100
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_uplode)

        setSupportActionBar(toolbar).apply {
            title = "UPLODE TEST"
        }

    }

    fun openGalleryForImage(view: View) {
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        startActivityForResult(intent, REQUEST_CODE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK && requestCode == REQUEST_CODE) {

            selectedImage = data?.data


            val filePathColumn = arrayOf(MediaStore.Images.Media.DATA)
            val cursor = contentResolver.query(
                selectedImage!!,
                filePathColumn, null, null, null
            )
            cursor!!.moveToFirst()

            val columnIndex = cursor.getColumnIndex(filePathColumn[0])
            picturePath = cursor.getString(columnIndex)
            cursor.close()

            imageView.setImageURI(selectedImage)

        }
    }

    fun uploefile(view: View) {

        val pathFromUri = selectedImage?.let { URIPathHelper().getPath(this@uplode, it) }


        Log.d(null,pathFromUri!!)
        val photo = File(pathFromUri)


        val params = RequestParams()
        try {
            params.put("file", photo)
        } catch (e: FileNotFoundException) {
        }


        val client = AsyncHttpClient()
        client.post("http://10.0.2.2:8000/", params, object : JsonHttpResponseHandler() {
            var pd: ProgressDialog? = null
            override fun onStart() {
                val uploadingMessage = "uploading"
                pd = ProgressDialog(this@uplode)
                pd!!.setTitle("please wait")
                pd!!.setMessage(uploadingMessage)
                pd!!.isIndeterminate = false
                pd!!.show()
            }

            override fun onSuccess(statusCode: Int, headers: Array<Header?>?, response: JSONObject?) {
//                val status: String = JsonUtil.getJsonItem(response, "status")
//                val photoID: String = JsonUtil.getJsonItem(response, "photoID")
                Log.d("", response.toString())
                Toast.makeText(this@uplode, "success", Toast.LENGTH_SHORT).show()
            }


            override fun onFinish() {
                pd!!.dismiss()
            }
        })
    }
}


