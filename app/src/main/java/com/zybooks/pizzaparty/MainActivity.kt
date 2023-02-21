

package com.zybooks.pizzaparty

import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.RadioGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlin.math.ceil

const val SLICES_PER_PIZZA = 8

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
    }
}