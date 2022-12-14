package com.ivanzulyan.andchallenge5.adapter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.ivanzulyan.andchallenge5.R
import com.ivanzulyan.andchallenge5.databinding.ItemMovieBinding
import com.ivanzulyan.andchallenge5.model.ResponsePopularMovieItem

class MovieAdapter(var listMovie: List<ResponsePopularMovieItem>): RecyclerView.Adapter<MovieAdapter.ViewHolder>() {
    class ViewHolder(var binding: ItemMovieBinding): RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var view =ItemMovieBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.cvMovie.setOnClickListener {
            var bundle = Bundle()
            bundle.putSerializable("datadetail",listMovie[position])
            it.findNavController().navigate(R.id.action_homeFragment_to_detailMovieFragment, bundle)

        }
        holder.binding.tvTitle.text = listMovie[position].originalTitle
        Glide.with(holder.itemView)
            .load("https://image.tmdb.org/t/p/w500"+listMovie[position].posterPath)
            .into(holder.binding.imgPoster)
        holder.binding.tvVote.text = listMovie[position].voteAverage.toString()
    }

    override fun getItemCount(): Int {
        return listMovie.size
    }
}