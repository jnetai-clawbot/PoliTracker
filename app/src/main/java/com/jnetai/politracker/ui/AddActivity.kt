package com.jnetai.politracker.ui

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.jnetai.politracker.PoliTracker
import com.jnetai.politracker.R
import com.jnetai.politracker.binding.ActivityAddBinding
import com.jnetai.politracker.model.Bill
import kotlinx.coroutines.launch

class AddActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAddBinding
    private val app get() = application as PoliTracker

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.title = "Add New Bill"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        binding.saveButton.setOnClickListener {
            val title = binding.editTitle.text.toString().trim()
            if (title.isEmpty()) {
                Toast.makeText(this, "Title is required", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            lifecycleScope.launch {
                val item = Bill(billNumber = title, title = title, sponsor = binding.editSubtitle.text.toString().trim())
            app.database.dao().insertBill(item)
                finish()
            }
        }
    }

    override fun onSupportNavigateUp(): Boolean { finish(); return true }
}