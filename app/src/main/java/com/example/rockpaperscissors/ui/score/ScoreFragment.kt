package com.example.rockpaperscissors.ui.score

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.rockpaperscissors.R
import com.example.rockpaperscissors.database.GameResultDatabase
import com.example.rockpaperscissors.databinding.ScoreFragmentBinding
import com.example.rockpaperscissors.game.GameResultAdapter

class ScoreFragment : Fragment() {




    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        var binding: ScoreFragmentBinding= DataBindingUtil.inflate(
            inflater,
            R.layout.score_fragment,
            container,
            false
        )

        val application = requireNotNull(this.activity).application
        val dataSource = GameResultDatabase.getInstance(application).gameResultDatabaseDao

        val viewModelFactory: ScoreViewModelFactory =
            ScoreViewModelFactory(dataSource, application)

        val viewModel: ScoreViewModel = ViewModelProvider(this, viewModelFactory).get(ScoreViewModel::class.java)

        binding.scoreViewModel = viewModel
        binding.lifecycleOwner = this

        val adapter = GameResultAdapter()
        binding.gameScoreList.adapter = adapter
        binding.gameScoreList.layoutManager = LinearLayoutManager(requireContext())

        binding.gameScoreList.addItemDecoration(
            DividerItemDecoration(
                context, LinearLayoutManager.VERTICAL
            )
        )

        viewModel.games.observe(viewLifecycleOwner, Observer {
            it?.let {
               adapter.submitList(it) {
                   binding.gameScoreList.scrollToPosition(0)
               }
            }
        })

        viewModel.eventBack.observe(viewLifecycleOwner, Observer {
            if(it) {
                findNavController().navigate(ScoreFragmentDirections.actionScoreFragmentToTitleFragment())
                viewModel.onBackComplete()
            }
        })


        return binding.root
    }
}