package com.example.appmovie.fragment.signup

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.appmovie.R
import com.example.appmovie.databinding.FragmentSignUpBinding
import com.example.appmovie.model.DataAccount
import com.example.appmovie.model.User


class SignUpFragment : Fragment() {
    lateinit var binding : FragmentSignUpBinding
    private lateinit var ViewModel: SignupViewModel
    //  lateinit var userManager: Data_Store
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentSignUpBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        ViewModel= ViewModelProvider(this).get(SignupViewModel::class.java)
//        userManager = Data_Store(this)

        binding.buttonLogin2.setOnClickListener {
            val controler = findNavController()
            controler.navigate(R.id.action_signUpFragment_to_signInFragment)
        }
        binding.buttonVeri2.setOnClickListener {
            val email=binding.emailSignup.text.toString().trim()
            val password=binding.passwordSignup.text.toString().trim()

            ViewModel.checkEmailAndPassword(email,password)

        }
        listenerSuccessEvent()
        listenerErrorEvent()

    }

    private fun listenerSuccessEvent(){
        ViewModel.isSuccessEvent.observe(viewLifecycleOwner){
            if(it){
                val fullName = binding.nameSignup.text.toString().trim()
                val email = binding.emailSignup.text.toString().trim()
                val password = binding.passwordSignup.text.toString().trim()
                val user = User(fullName,password,email)
                DataAccount.listUser.add(user)
                Log.d("user","${user.email}-${user.password} -${user.fullname}")
                Toast.makeText( activity,"Successful", Toast.LENGTH_SHORT).show()
                findNavController().navigate(R.id.action_signUpFragment_to_signInFragment)
            }
        }
    }
    private fun listenerErrorEvent(){
        ViewModel.isErrorEvent.observe(viewLifecycleOwner){
                errMsg ->
            val dialog = AlertDialog.Builder(requireContext())
            dialog.setTitle("Invalid information")
            dialog.setMessage(errMsg)
            dialog.show()
        }
    }

}