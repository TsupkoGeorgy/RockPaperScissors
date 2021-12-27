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
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.rockpaperscissors.R
import com.example.rockpaperscissors.database.GameResult
import com.example.rockpaperscissors.database.GameResultDatabase
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

        val application = requireNotNull(this.activity).application
        val dataSource = GameResultDatabase.getInstance(application).gameResultDatabaseDao

        gameViewModelFactory = GameViewModelFactory(dataSource, application)
        gameViewModel = ViewModelProvider(this, gameViewModelFactory).get(GameViewModel::class.java)

        binding.gameViewModel = gameViewModel
        binding.lifecycleOwner = this

        val adapter = GameResultAdapter()
        binding.gameResultList.adapter = adapter
        binding.gameResultList.layoutManager = LinearLayoutManager(requireContext())

        binding.gameResultList.addItemDecoration(
            DividerItemDecoration(
                context, LinearLayoutManager.VERTICAL
            )
        )
        gameViewModel.games.observe(viewLifecycleOwner, Observer {
            it?.let {
                adapter.submitList(it) {
                    binding.gameResultList.scrollToPosition(0)
                }
            }
        })
        return binding.root
    }
}
