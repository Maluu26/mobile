package com.mobile.app_pokemon

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import com.mobile.app_pokemon.databinding.FragmentSubscribeBinding
import com.mobile.app_pokemon.viewModel.AuthVM
import kotlin.getValue
import androidx.navigation.fragment.findNavController


class SubscribeFragment : Fragment() {

    private  var binding: FragmentSubscribeBinding? = null;

    private val viewModel: AuthVM by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding =
            FragmentSubscribeBinding.inflate(inflater, container, false)
        return binding!!.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeViewModel()
        binding!!.subscribeButton.setOnClickListener {
            viewModel.subscribeUser(binding!!.nome.text.toString(),binding!!.nick.text.toString(),binding!!.email.text.toString(), binding!!.senha.text.toString(),binding!!.senha2.text.toString())
        }
        binding!!.arrowBack.setOnClickListener({
            findNavController().popBackStack()
        })

    }
    fun observeViewModel(){
        viewModel.msg.observe(this){
                result -> result.onSuccess {
            Toast.makeText(requireContext(),"Cadastrado com Sucesso",
                Toast.LENGTH_SHORT).show()
        }.onFailure {
                error-> Toast.makeText(requireContext(),error.message,Toast.LENGTH_SHORT).show() }
        }
    }
    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}
