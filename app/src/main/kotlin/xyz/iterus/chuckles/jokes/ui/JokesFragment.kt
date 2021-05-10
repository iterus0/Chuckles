package xyz.iterus.chuckles.jokes.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import org.koin.androidx.viewmodel.ext.android.viewModel
import xyz.iterus.chuckles.R
import xyz.iterus.chuckles.databinding.FragmentJokesBinding

class JokesFragment : Fragment(R.layout.fragment_jokes) {

    private lateinit var binding: FragmentJokesBinding
    private val model: JokesViewModel by viewModel()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentJokesBinding.inflate(inflater, container, false)
        return binding.root
    }
}
