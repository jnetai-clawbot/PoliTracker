package com.jnetai.politracker.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.jnetai.politracker.PoliTracker
import com.jnetai.politracker.binding.ActivityDetailBinding
import com.jnetai.politracker.model.Bill
import kotlinx.coroutines.launch

class DetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding
    private val app get() = application as PoliTracker
    private var item: Bill? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        
        val itemId = intent.getLongExtra("item_id", -1)
        if (itemId == -1L) { finish(); return }
        
        lifecycleScope.launch {
            item = app.database.dao().getBill(itemId)
            item?.let { displayDetails(it) }
        }
    }
    
    private fun displayDetails(item: Bill) {
        binding.titleText.text = item.title
        binding.detailText.text = item.toString()
    }

    override fun onSupportNavigateUp(): Boolean { finish(); return true }
}