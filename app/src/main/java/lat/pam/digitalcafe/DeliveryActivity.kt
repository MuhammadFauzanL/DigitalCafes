package lat.pam.digitalcafe

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class DeliveryActivity : AppCompatActivity() {
    private var orderList: ArrayList<String>? = null
    private var userName: String? = null

    private lateinit var editNama: EditText
    private lateinit var editAlamat: EditText
    private lateinit var editDetailAlamat: EditText
    private lateinit var spinnerKota: Spinner
    private var deliveryMethod: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_delivery)
        orderList = intent.getStringArrayListExtra("ORDER_LIST") ?: ArrayList()
        userName = intent.getStringExtra("USER_NAME") ?: "Pelanggan"

        editNama = findViewById(R.id.edit_nama)
        editAlamat = findViewById(R.id.edit_alamat)
        editDetailAlamat = findViewById(R.id.edit_detail_alamat)
        spinnerKota = findViewById(R.id.spinner_kota)
        val buttonSubmitOrder: Button = findViewById(R.id.button_submit_order)

       deliveryMethod = getString(R.string.next_day_ground_delivery)

        ArrayAdapter.createFromResource(
            this,
            R.array.cities_array,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinnerKota.adapter = adapter
        }

        buttonSubmitOrder.setOnClickListener {

            val nama = editNama.text.toString()
            val alamat = editAlamat.text.toString()
            if (nama.isEmpty() || alamat.isEmpty()) {
                Toast.makeText(applicationContext, "Nama dan Alamat wajib diisi!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val intent = Intent(this, OrderCompleteActivity::class.java)
            intent.putExtra("USER_NAME", userName)
            startActivity(intent)

            finish()
        }
    }
    fun onRadioButtonClicked(view: View) {
        if (view is RadioButton) {
            val checked = view.isChecked
            when (view.id) {
                R.id.sameday -> if (checked) {
                    deliveryMethod = getString(R.string.same_day_messenger_service)
                    displayToast(deliveryMethod)
                }
                R.id.nextday -> if (checked) {
                    deliveryMethod = getString(R.string.next_day_ground_delivery)
                    displayToast(deliveryMethod)
                }
                R.id.pickup -> if (checked) {
                    deliveryMethod = getString(R.string.pick_up)
                    displayToast(deliveryMethod)
                }
            }
        }
    }

    private fun displayToast(message: String) {
        Toast.makeText(applicationContext, message, Toast.LENGTH_SHORT).show()
    }
}