package com.kocsistem.arcoresample1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.Button
import android.widget.Toast
import com.google.ar.core.ArCoreApk

class MainActivity : AppCompatActivity() {
    private lateinit var checkArCoreBtn: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        checkArCoreBtn = findViewById<Button> (R.id.check_arcore_btn)
        checkArCoreBtn.setOnClickListener {
            checkAvailableArCore()
        }
    }
    private fun checkAvailableArCore(){
        val availability = ArCoreApk.getInstance().checkAvailability(this)
        if (availability.isTransient) {
            showMessage("ARCore Ready")
            // Continue to query availability at 5Hz while compatibility is checked in the background.
            Handler().postDelayed({
                showMessage("5Hz while compatibility")
            }, 200)
        }else{
            showMessage("ARCore None")
        }

        if (availability.isSupported) {
            showMessage("ARCore isSupported for ARCore")
            val intent = Intent(this, ARActivity::class.java)
            startActivity(intent)
        } else { // .
            showMessage("The device is unsupported or unknown for ARCore")
        }
    }

    private fun showMessage(message: String){
        Toast.makeText(this@MainActivity, message, Toast.LENGTH_LONG).show()
    }
}