package com.example.appmovie.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.appmovie.R
import com.example.appmovie.databinding.FragmentOnBoardingThreeBinding


class OnBoardingThreeFragment : Fragment() {
    lateinit var binding : FragmentOnBoardingThreeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentOnBoardingThreeBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnOnBoarding3.setOnClickListener {
            var controler = findNavController()
            controler.navigate(R.id.action_onBoardingThreeFragment_to_welcomFragment)
        }
    }

}