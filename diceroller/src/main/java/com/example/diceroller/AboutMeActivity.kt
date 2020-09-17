package com.example.diceroller

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import com.example.diceroller.databinding.ActivityAboutMeBinding

class AboutMeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAboutMeBinding

    private val myName = MyName("Sebastian You Know", "SoyElMejor")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_about_me)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_about_me)

//        findViewById<Button>(R.id.done_button).setOnClickListener {
//            addNickNamet(it);
//        }

        binding.myName = myName
        //binding.nameText.text = "Hey I'm Sebastian You Know"
        binding.doneButton.setOnClickListener {
            addNickNametBinding(it)
        }
    }

    private fun addNickNametBinding(view: View) {

        binding.apply {
            //nicknameText.text = nicknameEdit.text
            myName?.nickname = nicknameEdit.text.toString()
            invalidateAll()
            nicknameEdit.visibility = View.GONE
            doneButton.visibility = View.GONE
            nicknameText.visibility = View.VISIBLE
        }

        // Hide the keyboard.
        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }

    private fun addNickNamet(view: View) {
        val editText = findViewById<EditText>(R.id.nickname_edit)
        val nicknameTextView = findViewById<TextView>(R.id.nickname_text)

        nicknameTextView.text = editText.text
        editText.visibility = View.GONE
        view.visibility = View.GONE
        nicknameTextView.visibility = View.VISIBLE

        // Hide the keyboard.
        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }
}
