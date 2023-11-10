package com.example.test2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import com.example.test2.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var inputList: MutableList<String> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnSave.setOnClickListener() {
            addInputToContainer()
        }

        binding.btnOutput.setOnClickListener() {
            sortAnagrams()
        }
    }

    private fun addInputToContainer() {
        val input = binding.edInputField.text.toString()

        if (input.isNotEmpty()) {
            inputList.add(input)
        }
    }

    private fun sortAnagrams() {
        val anagramMap: MutableMap<List<Char>, MutableList<String>> = mutableMapOf()
        val charsList: MutableList<List<Char>> = mutableListOf()
        for (word in inputList) {
            anagramMap.getOrPut(word.toCharArray().sorted()) { mutableListOf() }.add(word)
            charsList.add(word.toCharArray().sorted())
        }

        displayAnagrams(anagramMap.values.toString())
    }

    private fun displayAnagrams(anagrams: String) {
        val anagramsTV = TextView(this)

        val layoutParams = LinearLayout.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        layoutParams.setMargins(16, 16, 16, 16)
        anagramsTV.layoutParams = layoutParams
        anagramsTV.text = anagrams
        anagramsTV.textSize = 20F
        binding.layout.addView(anagramsTV)
    }
}