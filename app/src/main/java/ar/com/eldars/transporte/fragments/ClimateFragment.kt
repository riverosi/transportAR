package ar.com.eldars.transporte.fragments

import android.app.Activity
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Context.INPUT_METHOD_SERVICE
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.TextView
import androidx.core.content.ContextCompat.getSystemService
import ar.com.eldars.transporte.servicies.ClimateService
import ar.com.eldars.transporte.R

class ClimateFragment : Fragment() {

    private lateinit var progressBar: ProgressBar
    var textTemperature : TextView? = null
    private val receiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            progressBar.visibility = View.INVISIBLE
            val temperature = intent?.extras?.getDouble(ClimateService.EXTRA_TEMPERATURE, -1.0)
            textTemperature?.text = "$temperature °C"

        }
    }

    override fun onResume() {
        val intentFilter = IntentFilter(ClimateService.ACTION_TEMPERATURE)
        activity?.registerReceiver(receiver, intentFilter)
        super.onResume()
    }

    override fun onPause() {
        activity?.unregisterReceiver(receiver)
        super.onPause()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val viewRoot = inflater.inflate(R.layout.fragment_climate, container, false)
        progressBar = viewRoot.findViewById<ProgressBar>(R.id.progressBar)
        progressBar.visibility = View.INVISIBLE
        textTemperature = viewRoot.findViewById(R.id.textViewTemperature)
        val editCity = viewRoot.findViewById<EditText>(R.id.editTextCity)
        val buttonCity = viewRoot.findViewById<Button>(R.id.buttonCity)

        buttonCity.setOnClickListener {
            activity?.hideKeyboard()
            textTemperature?.text = ""
            progressBar.visibility = View.VISIBLE
            val selectedCity = editCity.text.toString()
            val intent = Intent(activity, ClimateService::class.java)
            intent.putExtra("city", selectedCity)
            activity?.startService(intent)
        }
        return viewRoot
    }
    //hide keyboard
    fun Fragment.hideKeyboard() {
        view?.let { activity?.hideKeyboard(it) }
    }

    fun Activity.hideKeyboard() {
        hideKeyboard(currentFocus ?: View(this))
    }

    fun Context.hideKeyboard(view: View) {
        val inputMethodManager = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
    }
}