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
import androidx.navigation.findNavController
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
            //isn't necessary start the activity again
            closeSession()
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
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

        val buttonFindMe = findViewById<Button>(R.id.buttonLocation)
        buttonFindMe.setOnClickListener {
            val intent = Intent(this, FindMeActivity::class.java)
            startActivity(intent)
        }



    }

    override fun onBackPressed() {
        closeSession()
        super.onBackPressed()
    }

    private fun closeSession() {
        val preferences = getSharedPreferences("pref_login", MODE_PRIVATE)
        preferences.edit {
            putString("active", "false")
            apply()
        }
    }
}