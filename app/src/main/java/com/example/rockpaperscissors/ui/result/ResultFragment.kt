package com.example.rockpaperscissors.ui.result

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.rockpaperscissors.R
import com.example.rockpaperscissors.database.GameResultDatabase
import com.example.rockpaperscissors.databinding.ResultFragmentBinding

class ResultFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: ResultFragmentBinding =
            DataBindingUtil.inflate(
                inflater,
                R.layout.result_fragment,
                container,
                false
            )

        val application = requireNotNull(this.activity).application
        val dataSource = GameResultDatabase.getInstance(application).gameResultDatabaseDao

        val viewModelFactory = ResultViewModelFactory( dataSource)
        val viewModel: ResultViewModel =
            ViewModelProvider(this, viewModelFactory).get(ResultViewModel::class.java)

        binding.resultViewModel = viewModel
        binding.lifecycleOwner = this

        
        binding.executePendingBindings()

        viewModel.eventPlayAgain.observe(viewLifecycleOwner, Observer { playAgain ->
            if (playAgain) {
                findNavController().navigate(ResultFragmentDirections.actionResultFragmentToGameFragment())
                viewModel.onRestartGameComplete()
            }

        })

        viewModel.eventCloseGame.observe(viewLifecycleOwner, Observer { close ->
            if (close) {
                findNavController().navigate(ResultFragmentDirections.actionResultFragmentToTitleFragment())
                viewModel.onCloseComplete()
            }
        })

        return binding.root
    }

}