package com.example.sharedprefrences

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var editor: SharedPreferences.Editor

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Initialize SharedPreferences
        sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
        editor = sharedPreferences.edit()

        val editTextName = findViewById<EditText>(R.id.editTextName)
        val buttonSave = findViewById<Button>(R.id.buttonSave)
        val textViewSavedName = findViewById<TextView>(R.id.textViewSavedName)

        // Load the saved name, if available
        val savedName = sharedPreferences.getString("name", "")
        if (savedName != "") {
            textViewSavedName.text = "Saved Name: $savedName"
        }

        // Save the name when the button is clicked
        buttonSave.setOnClickListener {
            val name = editTextName.text.toString()
            if (name.isNotEmpty()) {
                // Save the name in SharedPreferences
                editor.putString("name", name)
                editor.apply()

                // Update the TextView to display the saved name
                textViewSavedName.text = "Saved Name: $name"
            }
            editTextName.setText("")
            editTextName.clearFocus()
        }
    }
}
