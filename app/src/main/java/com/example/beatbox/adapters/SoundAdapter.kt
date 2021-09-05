package com.example.beatbox.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.beatbox.R
import com.example.beatbox.databinding.BeatItemBinding
import com.example.beatbox.sounds.BeatBox
import com.example.beatbox.sounds.Sound
import com.example.beatbox.viewmodels.SoundViewModel

class SoundAdapter : RecyclerView.Adapter<SoundAdapter.SoundViewHolder>() {

    private val TAG = SoundAdapter::class.simpleName

    private var sounds: MutableList<Sound> = ArrayList()
    private lateinit var beatBox: BeatBox

    @SuppressLint("NotifyDataSetChanged")
    fun setBeatBox(beatBox: BeatBox){
        this.beatBox = beatBox
        this.sounds.clear()
        this.sounds.addAll(this.beatBox.sounds)
        this.notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SoundViewHolder {
        val binding = DataBindingUtil.inflate<BeatItemBinding>(
            LayoutInflater.from(parent.context),
            R.layout.beat_item,
            parent,
            false
        )
        return SoundViewHolder(binding = binding, beatBox = beatBox)
    }

    override fun onBindViewHolder(holder: SoundViewHolder, position: Int) {
        holder.bind(sounds[position])
    }

    override fun getItemCount() = sounds.size

    class SoundViewHolder(private val binding: BeatItemBinding, beatBox: BeatBox) : RecyclerView.ViewHolder(binding.root) {
        init {
            binding.viewModel = SoundViewModel(beatBox = beatBox)
        }
        fun bind(sound: Sound){
            binding.apply {
                viewModel?.sound = sound
                executePendingBindings()
            }
        }
    }
}