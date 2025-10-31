package lat.pam.digitalcafe
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import android.widget.EditText
class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)


        val buttonSubmitLogin: Button = findViewById(R.id.button_submit_login)
        val editUsername: EditText = findViewById(R.id.edit_username)

        buttonSubmitLogin.setOnClickListener {
            val username = editUsername.text.toString()

            val intent = Intent(this, HomeActivity::class.java)
            intent.putExtra("USER_NAME", username)
            startActivity(intent)
            finish()
        }
    }
}