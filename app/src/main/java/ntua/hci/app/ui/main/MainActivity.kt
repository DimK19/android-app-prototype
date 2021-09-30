package ntua.hci.app.ui.main

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import ntua.hci.app.R
import ntua.hci.app.ui.camera.PhotoActivity
import ntua.hci.app.ui.list.WatchlistActivity
import ntua.hci.app.ui.search.SearchActivity
import kotlin.system.exitProcess

class MainActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        auth = Firebase.auth
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(findViewById(R.id.toolbar))
        setTitle(R.string.app_name)

        val welcome: TextView = findViewById<TextView>(R.id.txtNames) as TextView
        val welcomeMsg: String = getString(R.string.txtName) + " " + auth.currentUser?.email +"!"
        welcome.text = welcomeMsg
        if (auth.currentUser?.isEmailVerified == false)
            Toast.makeText(baseContext, R.string.verify_email,
                Toast.LENGTH_SHORT).show()

        findViewById<Button>(R.id.btnTerminate).setOnClickListener {
            auth.signOut()
            this.setResult(Activity.RESULT_OK)
            this.finish()
        }

        // facial recognition - camera
        findViewById<Button>(R.id.btnPhotoview).setOnClickListener {
            val list = Intent(this, PhotoActivity::class.java)
            startActivity(list)
            this.setResult(Activity.RESULT_OK)
            this.finish()
        }

        // watchlist
        findViewById<Button>(R.id.btnWatchlistview).setOnClickListener {
            val list = Intent(this, WatchlistActivity::class.java)
            startActivity(list)
            this.setResult(Activity.RESULT_OK)
            this.finish()
        }

        // search content
        findViewById<Button>(R.id.btnSearchview).setOnClickListener {
            val list = Intent(this, SearchActivity::class.java)
            startActivity(list)
            this.setResult(Activity.RESULT_OK)
            this.finish()
        }

    }
}