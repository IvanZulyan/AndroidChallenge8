package com.ivanzulyan.andchallenge5.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.ivanzulyan.andchallenge5.R
import com.ivanzulyan.andchallenge5.adapter.MovieAdapter
import com.ivanzulyan.andchallenge5.adapter.SerialAdapter
import com.ivanzulyan.andchallenge5.databinding.FragmentHomeBinding
import com.ivanzulyan.andchallenge5.model.ResponsePopularMovieItem
import com.ivanzulyan.andchallenge5.model.SerialResponseItem
import com.ivanzulyan.andchallenge5.viewmodel.LoginViewModel
import com.ivanzulyan.andchallenge5.viewmodel.ViewModelFactory
import com.ivanzulyan.andchallenge5.viewmodel.ViewModelPopularMovie

class HomeFragment : Fragment() {
    private lateinit var pref: com.ivanzulyan.andchallenge5.datastore.LoginDataStoreManager
    private lateinit var viewModelLoginPref: LoginViewModel
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        pref = com.ivanzulyan.andchallenge5.datastore.LoginDataStoreManager(this.requireActivity())
        viewModelLoginPref = ViewModelProvider(this, ViewModelFactory(pref))[LoginViewModel::class.java]
        viewModelLoginPref.getUser().observe(this.requireActivity(),{
            binding.tvSayHello.text = "Welcome, " + it.name
        })
        showDataMoviePopoular()
        showDataSerialPopular()

        binding.btnFavorite.setOnClickListener {
            this.findNavController().navigate(R.id.action_homeFragment_to_favoriteFragment)
        }
    }


    fun showDataMoviePopoular() {
        val viewModel = ViewModelProvider(this).get(ViewModelPopularMovie::class.java)
        viewModel.callApiPopularMovie{movies: List<ResponsePopularMovieItem> ->
            binding.rvMovie1.adapter = MovieAdapter(movies)
        }
        binding.rvMovie1.layoutManager = LinearLayoutManager(this.requireActivity(),LinearLayoutManager.HORIZONTAL,false)
        binding.rvMovie1.setHasFixedSize(true)
    }

    fun showDataSerialPopular() {
        val viewModel = ViewModelProvider(this).get(ViewModelPopularMovie::class.java)
        viewModel.callApiTvSerial{serial: List<SerialResponseItem> ->
            binding.rvMovie2.adapter = SerialAdapter(serial)
        }
        binding.rvMovie2.layoutManager = LinearLayoutManager(this.requireActivity(),LinearLayoutManager.HORIZONTAL,false)
        binding.rvMovie2.setHasFixedSize(true)
    }
}