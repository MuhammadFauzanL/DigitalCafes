package lat.pam.digitalcafe

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class OrderCompleteActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order_complete)
        val userName = intent.getStringExtra("USER_NAME") ?: "Pelanggan"


        val textHalo: TextView = findViewById(R.id.text_halo_complete)
        val buttonSelesai: Button = findViewById(R.id.button_selesai)
        textHalo.text = "Halo $userName,"
        buttonSelesai.setOnClickListener {
            val intent = Intent(this, HomeActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK

            startActivity(intent)
            finish()
        }
    }
}