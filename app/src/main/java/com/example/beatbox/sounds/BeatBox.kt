package com.example.beatbox.sounds

import android.content.res.AssetFileDescriptor
import android.content.res.AssetManager
import android.media.SoundPool
import android.util.Log
import java.io.IOException

private const val TAG = "BeatBox"
private const val SOUNDS_FOLDER = "sample_sounds"
private const val MAX_SOUNDS = 5

class BeatBox(private val assets: AssetManager) {

    val sounds: MutableList<Sound>
    private val soundPool = SoundPool.Builder()
        .setMaxStreams(MAX_SOUNDS)
        .build()

    init {
        sounds = loadSounds()
    }

    fun play(sound: Sound){
        sound.soundId?.let {
            soundPool.play(it, 1.0f, 1.0f, 1, 0,1.0f)
        }
    }

    fun release(){
        soundPool.release()
    }

    private fun loadSounds():MutableList<Sound>{
        val soundNames: Array<String>
        try {
            soundNames = assets.list(SOUNDS_FOLDER)!!
        }catch (e: Exception){
            Log.e(TAG, "Cloud not list assets ", e)
            return emptyList<Sound>().toMutableList()
        }
        val sounds = mutableListOf<Sound>()

        soundNames.forEach { filename ->
            val assetPath = "$SOUNDS_FOLDER/$filename"
            val sound = Sound(assetPath)
            try {
                load(sound)
                sounds.add(sound)
            }catch (ioe: IOException){
                Log.e(TAG, "Cloud not load sound $filename ", ioe)
            }
        }
        return sounds
    }

    private fun load(sound: Sound){
        val afd: AssetFileDescriptor = assets.openFd((sound.assetsPath))
        val soundId = soundPool.load(afd, 1)
        sound.soundId = soundId
    }

}