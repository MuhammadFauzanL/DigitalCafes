
package lat.pam.digitalcafe
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import android.widget.EditText
class RegisterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        val buttonSubmitRegister: Button = findViewById(R.id.button_submit_register)
        val editNamaLengkap: EditText = findViewById(R.id.edit_nama_lengkap)

        buttonSubmitRegister.setOnClickListener {
            val nama = editNamaLengkap.text.toString()

            val intent = Intent(this, HomeActivity::class.java)
            intent.putExtra("USER_NAME", nama)
            startActivity(intent)
            finish()
        }
    }
}