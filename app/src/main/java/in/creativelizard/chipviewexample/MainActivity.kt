package `in`.creativelizard.chipviewexample

import android.R.attr.editable
import android.os.Bundle
import android.text.Editable
import android.text.Spanned
import android.text.TextWatcher
import android.text.style.ImageSpan
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.chip.ChipDrawable
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    private var SpannedLength = 0
    private  var chipLength:Int = 4

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        onActionPerform()
    }

    private fun onActionPerform() {
        phone.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(charSequence: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if (charSequence?.length == SpannedLength - chipLength) {
                    SpannedLength = charSequence.length
                }
            }

            override fun afterTextChanged(editable: Editable?) {
                if ((editable?.length!! - SpannedLength) == chipLength) {
                    val chip = ChipDrawable.createFromResource(this@MainActivity, R.xml.chip)
                    chip.text = (editable.subSequence(SpannedLength, editable.length))
                    chip.setBounds(0, 0, chip.intrinsicWidth, chip.intrinsicHeight)
                    val span = ImageSpan(chip)
                    editable.setSpan(
                        span,
                        SpannedLength,
                        editable.length,
                        Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
                    )
                    SpannedLength = editable.length
                }
            }
        })
    }


}