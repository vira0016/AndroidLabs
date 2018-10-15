package com.example.admin.androidlabs

import android.app.Activity
import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import kotlinx.android.synthetic.main.activity_chat_window.*


class ChatWindow : Activity() {
val message="Hi"
    val messages = ArrayList<String>()

    var numItems = 100;



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat_window)

        val dhHelper = ChatDatabaseHelper() //this will open the database
        val db = dhHelper.writableDatabase // you can insert into here

        val results = db.query(dhHelper.TABLE_NAME, arrayOf( dhHelper.KEY_MESSAGE, dhHelper.KEY_ID),
                null, null, null, null, null ,null
                )

        val numRows = results.getCount() //number of rows saved
        results.moveToFirst() //read from the first row

        val numColums =results.getColumnCount()

        for (i in 0..numColums-1)
        {
            Log.i( "column names:", results.getColumnName(i));
        }

        val keyIndex = results.getColumnIndex( dhHelper.KEY_MESSAGE) //which column number is
        while(!results.isAfterLast)
        {
            var thisMessage = results.getString( keyIndex )

            messages.add(thisMessage) //pre load message from database

            results.moveToNext()
        }


        var Adapter3 = MyAdapter1(this)
        myList.setAdapter(Adapter3);
        var myList = findViewById<ListView>(R.id.myList)

        var button = findViewById<Button>(R.id.button2)

        var inputText = findViewById<EditText>(R.id.button4)

        button.setOnClickListener(View.OnClickListener {
            val typed = inputText.getText().toString()
            messages.add(typed)

            Adapter3.notifyDataSetChanged()

            //add to database

            var newRow = ContentValues()

            newRow.put( dhHelper.KEY_MESSAGE , typed)
            db.insert(dhHelper.TABLE_NAME, "", newRow)//insert new row


            inputText.setText("")





        })

//       // var theAdapter = MyAdapter1()
//
//        button3.setOnClickListener(View. OnClickListener{}
//
//
//            //set the result to be a unique number:
//            setResult(88)
//
//            //return to the previous activity:
//            finish()
//
//        }


    }


    inner class MyAdapter1(ctx : Context) : ArrayAdapter<String>(ctx, 0 ) {
        override fun getCount(): Int { //how many items
            return messages.size
        }



        override fun getItem(position: Int): String { //what to show
            return messages.get(position)
        }

        override fun getItemId(position: Int): Long {// what is the ID of item at position
            return 0
        }

        override fun getView(position: Int, convertView: View?, parent: ViewGroup): View? {

            var inflater = LayoutInflater.from(parent.getContext())
            var result = null as View?

            if (position % 2 == 0)
                result = inflater.inflate(R.layout.chat_row_incoming, null)
            else
                result = inflater.inflate(R.layout.chat_row_outgoing, null)

            val result1 = result?.findViewById<TextView>(R.id.messages)

            result1?.setText(getItem(position))

            return result
        }
    }

    var DATABASE_NAME = "Message.db"

    var VERSION_NUM = 2
      inner class ChatDatabaseHelper : SQLiteOpenHelper(this@ChatWindow, DATABASE_NAME, null, VERSION_NUM){

          var TABLE_NAME = "ChatMessage"
          val KEY_ID = "_id"
          val KEY_MESSAGE = "Message"



          override fun onCreate(db: SQLiteDatabase) {

              //db.execSQL("CREAT TABLE" + TABLENAME)

              db.execSQL("CREATE TABLE " + TABLE_NAME + "( _id INTEGER PRIMARY KEY AUTOINCREMENT," + KEY_MESSAGE + " TEXT)")


          }

          override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME)//delete all current data

              onCreate(db)  //create new table
          }
      }





}






