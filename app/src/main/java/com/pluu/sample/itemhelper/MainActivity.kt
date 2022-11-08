package com.pluu.sample.itemhelper

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.ItemTouchHelper
import com.pluu.sample.itemhelper.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val callback = SampleTouchHelperCallback()
        val itemTouchHelper = ItemTouchHelper(callback)
        itemTouchHelper.attachToRecyclerView(binding.listView)

        val adapter = SampleAdapter { holder ->
            itemTouchHelper.startDrag(holder)
        }
        binding.listView.adapter = adapter

        callback.setAdapter(adapter)
    }
}
