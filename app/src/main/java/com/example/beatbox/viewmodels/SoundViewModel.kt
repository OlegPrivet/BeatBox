package com.example.beatbox.viewmodels

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import com.example.beatbox.sounds.BeatBox
import com.example.beatbox.sounds.Sound

class SoundViewModel(private val beatBox: BeatBox) : BaseObservable(){

    fun onButtonClicked(){
        sound?.let {
            beatBox.play(it)
        }
    }

    var sound: Sound? = null
        set(sound){
            field = sound
            notifyChange()
        }

    @get:Bindable
    val title: String?
        get() = sound?.name



}