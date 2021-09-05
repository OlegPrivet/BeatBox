package com.example.beatbox

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.GridLayoutManager
import com.example.beatbox.adapters.SoundAdapter
import com.example.beatbox.databinding.ActivityMainBinding
import com.example.beatbox.sounds.BeatBox

class MainActivity : AppCompatActivity() {

    private lateinit var beatBox: BeatBox
    private lateinit var adapter: SoundAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        beatBox = BeatBox(assets)

        val binding: ActivityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        adapter = SoundAdapter()
        adapter.setBeatBox(beatBox = beatBox)
        binding.recyclerView.apply {
            layoutManager = GridLayoutManager(this@MainActivity, 3)
            adapter = this@MainActivity.adapter
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        beatBox.release()
    }

}