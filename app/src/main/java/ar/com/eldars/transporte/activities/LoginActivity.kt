package ar.com.eldars.transporte.activities

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
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
        const val KEY_IS_LOGIN: Boolean = false
    }

    val listEmpresas = listOf<String>("Eldar", "Prisma", "Fiserv")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val textViewUser = findViewById<EditText>(R.id.editTextName)
        val textViewPassword = findViewById<EditText>(R.id.editTextPassword)
        val buttonLogin = findViewById<Button>(R.id.buttonLogin)
        val spinnerEmpresas = findViewById<Spinner>(R.id.spinnerEmpresas)

        val adapter = ArrayAdapter(
            this,
            android.R.layout.simple_spinner_dropdown_item,
            listEmpresas
        )
        adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line)
        spinnerEmpresas.adapter = adapter

        val prefUser = getSharedPreferences("pref_login", Context.MODE_PRIVATE)

        if (prefUser?.getString("active", "") == "true") {
            val intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
            finish()
        } else {
            if (prefUser?.getString("remember", "") == "true") {
                textViewUser.setText(prefUser.getString("user", ""))
                textViewPassword.setText(prefUser.getString("pass", ""))
            }
        }

        buttonLogin.setOnClickListener {

            val user: String = textViewUser.text.toString()
            val password: String = textViewPassword.text.toString()

            if (user.isNotEmpty() && password.isNotEmpty()) {
                if (findViewById<CheckBox>(R.id.checkBox).isChecked) {
                    loginCheck(user, password, prefUser)
                } else {
                    loginNoCheck(user, password, prefUser)
                }
            } else {
                val alertLogin = AlertDialog.Builder(this)
                    .setTitle("Login Alert")
                    .setMessage("Empty Fields")
                    .setPositiveButton("Accept") { _, _ -> }
                    .show()
            }
        }
    }

    private fun loginNoCheck(
        user: String,
        password: String,
        prefUser: SharedPreferences
    ) {
        API.login(user, password) { response ->
            if (response) {
                prefUser.edit {
                    putString("active", "true")
                    putString("remember", "false")
                    apply()
                }
                val intent = Intent(this, HomeActivity::class.java)
                startActivity(intent)
                finish()
            } else {
                prefUser.edit {
                    putString("active", "false")
                    apply()
                }
                //show invalid user
                val alertLogin = AlertDialog.Builder(this)
                    .setTitle("Login Alert")
                    .setMessage("Invalid User")
                    .setPositiveButton("Accept") { _, _ -> }
                    .show()
            }

        }
    }

    private fun loginCheck(
        user: String,
        password: String,
        prefUser: SharedPreferences
    ) {
        API.login(user, password) { response ->
            if (response) {
                prefUser.edit {
                    putString("user", user)
                    putString("pass", password)
                    putString("active", "true")
                    putString("remember", "true")
                    apply()
                }
                val intent = Intent(this, HomeActivity::class.java)
                startActivity(intent)
                finish()
            } else {
                prefUser.edit {
                    putString("active", "false")
                    apply()
                }
                //show invalid user
                val alertLogin = AlertDialog.Builder(this)
                    .setTitle("Login Alert")
                    .setMessage("Invalid User")
                    .setPositiveButton("Accept") { _, _ -> }
                    .show()
            }
        }
    }

}

