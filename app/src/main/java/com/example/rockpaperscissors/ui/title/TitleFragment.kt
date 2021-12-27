package com.example.rockpaperscissors.ui.title


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import com.example.rockpaperscissors.R
import com.example.rockpaperscissors.databinding.TitleFragmentBinding

class TitleFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        val binding: TitleFragmentBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.title_fragment,
            container,
            false
        )

        binding.startGameButton.setOnClickListener {
            findNavController().navigate(
                TitleFragmentDirections.actionTitleFragmentToGameFragment()
            )
        }

        binding.scoreButton.setOnClickListener {
            findNavController().navigate(
                TitleFragmentDirections.actionTitleFragmentToScoreFragment()
            )
        }
        return binding.root
    }


}