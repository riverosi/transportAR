package ar.com.eldars.transporte.activities

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.core.content.edit
import ar.com.eldars.transporte.API
import ar.com.eldars.transporte.R

class LoginActivity : AppCompatActivity() {

    companion object {
        const val KEY_IS_LOGIN: Boolean = true
    }

    val listEmpresas = listOf<String>("Eldar", "Prisma" , "Fiserv")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val textViewUser = findViewById<EditText>(R.id.editTextName)
        val textViewPassword = findViewById<EditText>(R.id.editTextPassword)
        val buttonLogin = findViewById<Button>(R.id.buttonLogin)
        val spinnerEmpresas = findViewById<Spinner>(R.id.spinnerEmpresas)

        val adapter = ArrayAdapter(this,
            android.R.layout.simple_spinner_dropdown_item,
            listEmpresas)
        adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line)
        spinnerEmpresas.adapter = adapter

        val prefUser = getSharedPreferences("pref_login", Context.MODE_PRIVATE)

        buttonLogin.setOnClickListener {

            val user: String = textViewUser.text.toString()
            val password: String = textViewPassword.text.toString()

            API.login(user, password)
            { response ->
                if (response) {
                    val intent = Intent(this, HomeActivity::class.java)
                    startActivity(intent)
                    prefUser.getString("user", "")
                    prefUser.edit {
                        putString("user", user)
                        putString("pass", password)
                    }
                } else {
                    val alertLogin = AlertDialog.Builder(this)
                        .setTitle("Login Alert")
                        .setMessage("Usiario Incorrecto")
                        .setPositiveButton("Aceptar") { _, _ -> }
                        .show()
                }
            }

        }
    }

    // extension function to hide soft keyboard programmatically

}

