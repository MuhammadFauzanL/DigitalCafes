package lat.pam.digitalcafe

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomnavigation.BottomNavigationView

class HomeActivity : AppCompatActivity() {

    private var userName: String? = null
    private val orderList = ArrayList<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        userName = intent.getStringExtra("USER_NAME") ?: "Pelanggan"

        val textHalo: TextView = findViewById(R.id.text_halo)
        val bottomNav: BottomNavigationView = findViewById(R.id.bottom_navigation)
        textHalo.text = "Halo $userName,"
        val imageDonuts: ImageView = findViewById(R.id.image_donuts)
        val imageFroyo: ImageView = findViewById(R.id.image_froyo)
        val imageIce: ImageView = findViewById(R.id.image_ice)
        val imageKopiHitam: ImageView = findViewById(R.id.image_kopi_hitam)
        val imageCaffeLatte: ImageView = findViewById(R.id.image_caffe_latte)
        val imageCroissant: ImageView = findViewById(R.id.image_croissant)
        val imageCheesecake: ImageView = findViewById(R.id.image_cheesecake)
        val imageMatchaLatte: ImageView = findViewById(R.id.image_matcha_latte)
        val imageClubSandwich: ImageView = findViewById(R.id.image_club_sandwich)
        val imageRedVelvet: ImageView = findViewById(R.id.image_red_velvet)
        val imageJusJeruk: ImageView = findViewById(R.id.image_jus_jeruk)
        val imageMuffin: ImageView = findViewById(R.id.image_muffin)
        val imageEspresso: ImageView = findViewById(R.id.image_espresso)


        imageDonuts.setOnClickListener {
            val itemName = getString(R.string.donut_name) 
            orderList.add(itemName)
            displayToast("$itemName ditambahkan ke keranjang")
        }

        imageFroyo.setOnClickListener {
            val itemName = getString(R.string.froyo_name) 
            orderList.add(itemName)
            displayToast("$itemName ditambahkan ke keranjang")
        }

        imageIce.setOnClickListener {
            val itemName = getString(R.string.ice_cream_name) 
            orderList.add(itemName)
            displayToast("$itemName ditambahkan ke keranjang")
        }

        imageKopiHitam.setOnClickListener {
            val itemName = getString(R.string.kopi_hitam_name) 
            orderList.add(itemName)
            displayToast("$itemName ditambahkan ke keranjang")
        }

        imageCaffeLatte.setOnClickListener {
            val itemName = getString(R.string.caffe_latte_name) 
            orderList.add(itemName)
            displayToast("$itemName ditambahkan ke keranjang")
        }

        imageCroissant.setOnClickListener {
            val itemName = getString(R.string.croissant_name) 
            orderList.add(itemName)
            displayToast("$itemName ditambahkan ke keranjang")
        }

        imageCheesecake.setOnClickListener {
            val itemName = getString(R.string.cheesecake_name) 
            orderList.add(itemName)
            displayToast("$itemName ditambahkan ke keranjang")
        }

        imageMatchaLatte.setOnClickListener {
            val itemName = getString(R.string.matcha_latte_name) 
            orderList.add(itemName)
            displayToast("$itemName ditambahkan ke keranjang")
        }

        imageClubSandwich.setOnClickListener {
            val itemName = getString(R.string.club_sandwich_name) 
            orderList.add(itemName)
            displayToast("$itemName ditambahkan ke keranjang")
        }

        imageRedVelvet.setOnClickListener {
            val itemName = getString(R.string.red_velvet_name) 
            orderList.add(itemName)
            displayToast("$itemName ditambahkan ke keranjang")
        }

        imageJusJeruk.setOnClickListener {
            val itemName = getString(R.string.jus_jeruk_name) 
            orderList.add(itemName)
            displayToast("$itemName ditambahkan ke keranjang")
        }

        imageMuffin.setOnClickListener {
            val itemName = getString(R.string.muffin_name) 
            orderList.add(itemName)
            displayToast("$itemName ditambahkan ke keranjang")
        }

        imageEspresso.setOnClickListener {
            val itemName = getString(R.string.espresso_name) 
            orderList.add(itemName)
            displayToast("$itemName ditambahkan ke keranjang")
        }
        bottomNav.selectedItemId = R.id.nav_home

        bottomNav.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_home -> true
                R.id.nav_order -> {
                    val intent = Intent(this, OrderSummaryActivity::class.java)
                    intent.putExtra("USER_NAME", userName)
                    intent.putStringArrayListExtra("ORDER_LIST", orderList)
                    startActivity(intent)
                    finish()
                    true
                }
                R.id.nav_profile -> {
                    displayToast("Fitur Profile belum tersedia")
                    true
                }
                else -> false
            }
        }
    }

    private fun displayToast(message: String?) {
        Toast.makeText(applicationContext, message, Toast.LENGTH_SHORT).show()
    }
}