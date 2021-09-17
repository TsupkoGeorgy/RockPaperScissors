package com.example.rockpaperscissors.game

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.rockpaperscissors.R
import com.example.rockpaperscissors.databinding.GameFragmentBinding


class GameFragment : Fragment() {

    private lateinit var gameViewModel: GameViewModel
    private lateinit var gameViewModelFactory: GameViewModelFactory

    private lateinit var binding: GameFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.game_fragment,
            container,
            false
        )

        gameViewModelFactory = GameViewModelFactory()
        gameViewModel = ViewModelProvider(this, gameViewModelFactory).get(GameViewModel::class.java)





        binding.gameViewModel = gameViewModel
        binding.setLifecycleOwner(this)

        val adapter = GameResultAdapter()
        binding.gameResultList.adapter = adapter

        gameViewModel.tempList.observe(viewLifecycleOwner, Observer {
            it?.let {
                adapter.data = it
                Log.i("adapter", "adapter data = ${adapter.data}")
            }
        })






        // Inflate the layout for this fragment
        return binding.root
    }


}