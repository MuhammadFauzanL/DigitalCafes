package lat.pam.digitalcafe

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomnavigation.BottomNavigationView

class OrderSummaryActivity : AppCompatActivity() {

    private var userName: String? = null
    private var orderList: ArrayList<String>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order_summary)
        userName = intent.getStringExtra("USER_NAME") ?: "Pelanggan"
        orderList = intent.getStringArrayListExtra("ORDER_LIST") ?: ArrayList()
        val textHalo: TextView = findViewById(R.id.text_halo_summary)
        val textOrderList: TextView = findViewById(R.id.text_order_list)
        val buttonKirim: Button = findViewById(R.id.button_kirim)
        val bottomNav: BottomNavigationView = findViewById(R.id.bottom_navigation)


        textHalo.text = "Halo $userName,"

        if (orderList!!.isEmpty()) {
            textOrderList.text = "Keranjang Anda kosong."
        } else {
            textOrderList.text = orderList!!.joinToString(separator = "\n")
        }
        buttonKirim.setOnClickListener {

            val intent = Intent(this, DeliveryActivity::class.java)
            intent.putStringArrayListExtra("ORDER_LIST", orderList)
            intent.putExtra("USER_NAME", userName)

            startActivity(intent)
            finish()
        }
        bottomNav.selectedItemId = R.id.nav_order
        bottomNav.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_home -> {
                    val intent = Intent(this, HomeActivity::class.java)
                    intent.putExtra("USER_NAME", userName)
                    startActivity(intent)
                    finish()
                    true
                }
                R.id.nav_order -> true
                R.id.nav_profile -> {
                    Toast.makeText(this, "Fitur Profile belum tersedia", Toast.LENGTH_SHORT).show()
                    true
                }
                else -> false
            }
        }
    }
}