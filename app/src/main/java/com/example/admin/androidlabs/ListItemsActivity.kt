package com.example.admin.androidlabs

import android.app.Activity
import android.app.AlertDialog
import android.content.Intent
import android.graphics.Bitmap

import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.View
import android.widget.*


class ListItemsActivity : Activity() {
    val ACTIVITY_NAME = "ListItemsActivity"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_items)
        Log.i(ACTIVITY_NAME, "In onCreate()");
        var imagebutton = findViewById<ImageView>(R.id.imageView2)
        var swtCheck = findViewById<Switch>(R.id.switch1)
        var chkbox=findViewById<CheckBox>(R.id.checkBox)
        chkbox?.setOnCheckedChangeListener(CompoundButton.OnCheckedChangeListener(){checkbx,isChecked->
            val builder = AlertDialog.Builder(this)
            builder.setMessage(R.string.dialog_message)
                    .setTitle(R.string.dialog_title)
                    .setPositiveButton(R.string.ok) { dialog, id ->
                        val resultIntent = Intent( )
                        resultIntent.putExtra("Response",getResources().getString(R.string.res_msg) )
                        setResult(1, resultIntent);
                        finish()
                    }.setNegativeButton(R.string.cancel, { dialog, id ->
                // User cancelled the dialog
            }).show()
        })

        swtCheck?.setOnCheckedChangeListener(CompoundButton.OnCheckedChangeListener { buttonView, isChecked ->
            var text = "Switch is On" // "Switch is Off"

            if (isChecked) {
                Log.i(ACTIVITY_NAME, "In switch On()")
                text = "Switch is On"
            } else {
                Log.i(ACTIVITY_NAME, "In switch Off()")
                text = "Switch is Off"
            }
            val duration = Toast.LENGTH_SHORT //= Toast.LENGTH_LONG if Off
            val toast = Toast.makeText(this, text, duration) //this is the ListActivity
            toast.show()
        })
        imagebutton?.setOnClickListener(View.OnClickListener {
            val REQUEST_IMAGE_CAPTURE = 1
            val photoIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            if (photoIntent.resolveActivity(packageManager) != null) {
                startActivityForResult(photoIntent, 555)
            }

        })

    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent) {
        if (requestCode == 555 && resultCode == RESULT_OK) {
            val imageBitmap = data.extras.get("data") as Bitmap
            val imgButton = findViewById<ImageButton>(R.id.imageView)

            imgButton.setImageBitmap(imageBitmap)
        }
    }

    override fun onResume() {
        super.onResume()

        val ACTIVITY_NAME = "ListItemsActivity"

        Log.i(ACTIVITY_NAME, "In onResume()");
    }

    override fun onStart() {
        super.onStart()

        val ACTIVITY_NAME = "ListItemsActivity"

        Log.i(ACTIVITY_NAME, "In onStart()");
    }

    override fun onPause() {
        super.onPause()
        val ACTIVITY_NAME = "ListItemsActivity"

        Log.i(ACTIVITY_NAME, "In onPause()");
    }

    override fun onStop() {
        super.onStop()

        val ACTIVITY_NAME = "ListItemsActivity"

        Log.i(ACTIVITY_NAME, "In onStop()");
    }

    override fun onDestroy() {
        super.onDestroy()

        val ACTIVITY_NAME = "ListItemsActivity"

        Log.i(ACTIVITY_NAME, "In onDestroy()");
    }




}
