package com.example.admin.androidlabs

import android.app.Activity
import android.app.SearchManager
import android.content.Context
import android.content.Intent
import android.media.Image
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText



class LoginActivity : Activity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        var editText = findViewById<EditText>(R.id.editText)

        val sharedPref = getSharedPreferences("SavedData", Context.MODE_PRIVATE)

        editText.setText(sharedPref.getString("LoginName", "email@domain.com"))

        var loginbutton = findViewById<Button>(R.id.login)

        loginbutton?.setOnClickListener(View.OnClickListener {

            // create an Intent to go to the Activity InformationActivity:
            val newActivity = Intent(this, StartActivity::class.java);

            //Get what the user typed in the editText:
            val typedString = editText.getText().toString()


            //get an editor object to saved to SharedPreferences:
            val prefs = sharedPref.edit()

            //Under the name UserInput, save what the user typed:
            prefs.putString("LoginName", typedString)

            prefs.commit()//writes a file

            //Put the user string under the reservation "UserName" to send to the next page
            newActivity.putExtra("UserName", typedString)

            //transition to the new page:
            startActivity(newActivity)
        })
    }

        override fun onResume() {
        super.onResume()

        val ACTIVITY_NAME = "LoginActivity"

        Log.i(ACTIVITY_NAME, "In onResume()");
    }

    override fun onStart() {
        super.onStart()

        val ACTIVITY_NAME = "LoginActivity"

        Log.i(ACTIVITY_NAME, "In onStart()");
    }

    override fun onPause() {
        super.onPause()
        val ACTIVITY_NAME = "LoginActivity"

        Log.i(ACTIVITY_NAME, "In onPause()");
    }

    override fun onStop() {
        super.onStop()

        val ACTIVITY_NAME = "LoginActivity"

        Log.i(ACTIVITY_NAME, "In onStop()");
    }

    override fun onDestroy() {
        super.onDestroy()

        val ACTIVITY_NAME = "LoginActivity"

        Log.i(ACTIVITY_NAME, "In onDestroy()");
    }




}
