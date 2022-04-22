package ar.com.eldars.transporte.activities

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.Button
import androidx.core.content.edit
import ar.com.eldars.transporte.R
import com.google.android.material.floatingactionbutton.FloatingActionButton

class HomeActivity : AppCompatActivity() {

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = MenuInflater(this)
        inflater.inflate(R.menu.home, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.action_logout){
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()

            val preferences = getSharedPreferences("pref_login", Context.MODE_PRIVATE)
            preferences.edit {
                putBoolean(LoginActivity.KEY_IS_LOGIN.toString(), false)
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        val button = findViewById<Button>(R.id.button)
        button.setOnClickListener {
            val intent = Intent(this, ClimateActivity::class.java)
            startActivity(intent)
        }

        val buttonNotes = findViewById<Button>(R.id.buttonNotes)
        buttonNotes.setOnClickListener {
            val intent = Intent(this, NotesActivity::class.java)
            startActivity(intent)
        }

        val buttonWeb = findViewById<Button>(R.id.buttonWeb)
        buttonWeb.setOnClickListener {
            val intent = Intent(this, WebActivity::class.java)
            startActivity(intent)
        }

        val buttonFindMe = findViewById<FloatingActionButton>(R.id.floatingActionFindMe)
        buttonFindMe.setOnClickListener {
            val intent = Intent(this, FindMeActivity::class.java)
            startActivity(intent)
        }

    }
}