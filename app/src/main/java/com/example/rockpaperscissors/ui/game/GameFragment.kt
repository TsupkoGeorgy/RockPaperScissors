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
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.rockpaperscissors.R
import com.example.rockpaperscissors.database.GameResult
import com.example.rockpaperscissors.database.GameResultDatabase
import com.example.rockpaperscissors.databinding.GameFragmentBinding
import kotlinx.android.synthetic.main.activity_main.*


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




        gameViewModel.eventGameFinish.observe(viewLifecycleOwner, Observer {
            if (it) {
                findNavController()
                    .navigate(GameFragmentDirections.actionGameFragmentToResultFragment())

                gameViewModel.onGameFinishComplete()
            }
        })
        return binding.root
    }
}
