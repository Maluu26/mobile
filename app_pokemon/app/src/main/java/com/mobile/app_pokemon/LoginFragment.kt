package com.mobile.app_pokemon

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import com.google.firebase.auth.FirebaseAuth
import com.mobile.app_pokemon.databinding.FragmentLoginBinding
import com.mobile.app_pokemon.viewModel.AuthVM
import kotlin.getValue
import androidx.navigation.fragment.findNavController
import com.mobile.app_pokemon.UI.MainActivity

class LoginFragment : Fragment(){
    private val auth = FirebaseAuth.getInstance()

    private  var binding: FragmentLoginBinding? = null;

    private val viewModel: AuthVM by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding =
            FragmentLoginBinding.inflate(inflater, container, false)
        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeViewModel()
        if(auth.currentUser != null){
            //auth.signOut()
            startActivity(Intent(requireContext(), MainActivity::class.java))

        }
        binding!!.login.setOnClickListener {
            viewModel.loginUser(binding!!.email.text.toString(), binding!!.senha.text.toString())
        }
        binding!!.subscribeHint.setOnClickListener({
            findNavController().navigate(R.id.action_loginFragment_to_subscribeFragment)
        })


    }
    private fun observeViewModel() {
        viewModel.msg.observe(this) { resultado ->
            resultado.onSuccess {
                Toast.makeText(requireContext(), "Login realizado com sucesso", Toast.LENGTH_SHORT).show()
                startActivity(Intent(requireContext(), MainActivity::class.java))

            }.onFailure { erro ->
                Toast.makeText(requireContext(), erro.message, Toast.LENGTH_SHORT).show()
            }
        }
    }
    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}