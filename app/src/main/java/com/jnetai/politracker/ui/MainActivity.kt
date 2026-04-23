package com.jnetai.politracker.ui

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.jnetai.politracker.PoliTracker
import com.jnetai.politracker.R
import com.jnetai.politracker.databinding.ActivityMainBinding
import com.jnetai.politracker.model.Bill
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val app get() = application as PoliTracker

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)
        supportActionBar?.title = getString(R.string.app_name)

        val adapter = BillAdapter { item -> startActivity(Intent(this, DetailActivity::class.java).putExtra("item_id", item.id)) }
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = adapter

        refreshList()
        binding.fab.setOnClickListener { startActivity(Intent(this, AddActivity::class.java)) }
    }

    override fun onResume() { super.onResume(); refreshList() }

    private fun refreshList() {
        lifecycleScope.launch {
            val items = app.database.dao().getAllBills()
            (binding.recyclerView.adapter as BillAdapter).items = items
            (binding.recyclerView.adapter as BillAdapter).notifyDataSetChanged()
            binding.emptyView.visibility = if (items.isEmpty()) android.view.View.VISIBLE else android.view.View.GONE
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean { menuInflater.inflate(R.menu.menu_main, menu); return true }
    override fun onOptionsItemSelected(item: MenuItem): Boolean = when (item.itemId) {
        R.id.action_about -> { startActivity(Intent(this, AboutActivity::class.java)); true }
        else -> super.onOptionsItemSelected(item)
    }
}