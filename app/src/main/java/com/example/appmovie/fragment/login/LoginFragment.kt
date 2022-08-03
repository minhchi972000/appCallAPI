package com.example.appmovie.fragment.login

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.appmovie.R
import com.example.appmovie.app.MyApp
import com.example.appmovie.app.ViewModelFactory
import com.example.appmovie.databinding.FragmentLoginBinding
import com.example.appmovie.model.DataAccount


class LoginFragment: Fragment() {
    lateinit var binding : FragmentLoginBinding
    private lateinit var ViewModel: LoginViewModel
    //  private lateinit var userManager: Data_Store
    var checkShare = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        ViewModel = ViewModelProvider(this, ViewModelFactory(activity?.application as MyApp)).get(
            LoginViewModel::class.java
        )

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentLoginBinding.inflate(inflater,container,false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        binding = DataBindingUtil.setContentView(this,R.layout.activity_login)
      //  ViewModel = ViewModelProvider(this).get(LoginViewModel::class.java)

        binding.buttonSignup.setOnClickListener {
            val controller = findNavController()
            controller.navigate(R.id.action_signInFragment_to_signUpFragment)
        }
        binding.buttonVeri.setOnClickListener {
            val email = binding.email.text.toString().trim()
            val password = binding.password.text.toString().trim()
            ViewModel.checkEmailAndPassword(email, password)
            if (check()) {
                checkShare = true
                ViewModel.saveUserInfo(email,password,checkShare)
                val controller = findNavController()
                controller.navigate(R.id.action_signInFragment_to_homeScreenFragment)
            } else
            {
                Toast.makeText(activity, "Email and password incorrect !", Toast.LENGTH_SHORT).show()
            }
        }
        listenerErrorEvent()
        listenerSuccessEvent()
    }
    private fun listenerSuccessEvent(){
        ViewModel.isSuccessEvent.observe(viewLifecycleOwner){
            if(it){
                Toast.makeText(activity, "Successful login !", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private  fun get() : String
    {
        return binding.email.text.toString().trim()
    }
    private fun check(): Boolean {
        val email = binding.email.text.toString().trim()
        val password = binding.password.text.toString().trim()
        println("-------------------")
        println(email)
        println(password)
        println("-------------------")
        for (user in DataAccount.listUser){
            if(email.equals(user.email) && password.equals(user.password)){
                // val user1 : User

                return true
            }
        }
        return false
    }
    private fun listenerErrorEvent(){
        ViewModel.isErrorEvent.observe(viewLifecycleOwner){
            Toast.makeText(activity,it, Toast.LENGTH_SHORT).show()
        }
    }
}
