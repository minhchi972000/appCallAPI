package com.example.appmovie.fragment

import android.content.Context
import android.os.Bundle
import android.provider.SyncStateContract
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.appmovie.MySharedPreferences
import com.example.appmovie.R
import com.example.appmovie.databinding.FragmentWelcomBinding
import com.example.appmovie.model.DataAccount

class WelcomFragment : Fragment() {

    lateinit var binding : FragmentWelcomBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentWelcomBinding.inflate(inflater,container,false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        

        binding.buttonSkip.setOnClickListener {
            var controler = findNavController()
            controler.navigate(R.id.action_welcomFragment_to_signUpFragment)
        }
        binding.buttonLogin3.setOnClickListener {
            var controler = findNavController()
            controler.navigate(R.id.action_signInFragment_to_signUpFragment)
        }
        binding.buttonSignin.setOnClickListener {
            var controler = findNavController()
            controler.navigate(R.id.action_welcomFragment_to_signInFragment)
        }
    }




}