

package com.zybooks.pizzaparty

//import android.os.Build.VERSION_CODES.R
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.EditText
import android.widget.RadioGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlin.math.ceil

const val SLICES_PER_PIZZA = 8
var stateNum = 1 //a variable to prevent infinite loop

class MainActivity : AppCompatActivity() {
    //variable to store text box
    private lateinit var numAttendEditText: EditText

    //variable to store result display
    private lateinit var numPizzasTextView: TextView

    //variable to store radio buttons to indicate voraciousness
    private lateinit var howHungryRadioGroup: RadioGroup


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        numAttendEditText = findViewById(R.id.num_attend_edit_text)
        numAttendEditText.addTextChangedListener(object: TextWatcher {
         override fun afterTextChanged(s: Editable){}
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int){}
         override fun onTextChanged(s: CharSequence, start:Int,before:Int,count:Int){
             if (stateNum == 1) {
                 numAttendEditText.setText("")
                 stateNum = 0
             }
         }
        })
        numPizzasTextView = findViewById(R.id.num_pizzas_text_view)
        howHungryRadioGroup = findViewById(R.id.hungry_radio_group)
    }

    //function to calculate slices needed
    fun calculateClick(view: View) {
        //convert text in text box to int
        val numAttendStr = numAttendEditText.text.toString()
        val numAttend = numAttendStr.toInt()

        //determine which button is selected
        val slicesPerPerson = when (howHungryRadioGroup.checkedRadioButtonId)
        {
            R.id.light_radio_button -> 2
            R.id.medium_radio_button -> 3
            else -> 4
        }

        //calculate number of pizza
        val totalPizzas = ceil(
            numAttend * slicesPerPerson /
                    SLICES_PER_PIZZA.toDouble()
        ).toInt()

        //output number of pizzas
        numPizzasTextView.text = "Total pizzas: $totalPizzas"

        stateNum = 1
    }
}