package xyz.iterus.chuckles.jokes.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import org.koin.androidx.viewmodel.ext.android.viewModel
import xyz.iterus.chuckles.R
import xyz.iterus.chuckles.databinding.FragmentJokesBinding

class JokesFragment : Fragment(R.layout.fragment_jokes) {

    private lateinit var binding: FragmentJokesBinding
    private val model: JokesViewModel by viewModel()
    private lateinit var jokesAdapter: JokesAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentJokesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // TODO: Koin inject
        jokesAdapter = JokesAdapter(requireContext())
        binding.jokes.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = jokesAdapter
        }

        subscribeObservers()
    }

    private fun subscribeObservers() {
        with (model) {
            jokes.observe(viewLifecycleOwner, {
                jokesAdapter.submitList(it)
            })

            loading.observe(viewLifecycleOwner, {
                showProgressBar(it)
            })

            jokesError.observe(viewLifecycleOwner, {
                showInputError(it)
            })
        }

        binding.reloadButton.setOnClickListener {
            val count = binding.jokesCount.text.toString()
            model.reloadJokes(count)
        }
    }

    private fun showProgressBar(isDisplayed: Boolean) {
        val visibility = if (isDisplayed) View.VISIBLE else View.GONE
        binding.loading.visibility = visibility
    }

    private fun showInputError(error: JokesViewModel.JokesError) {
        binding.jokesCount.error = when (error) {
            is JokesViewModel.JokesError.None -> null
            is JokesViewModel.JokesError.EmptyJokesCount -> getString(R.string.error_input_empty)
            is JokesViewModel.JokesError.InvalidJokesCount -> getString(R.string.error_input_invalid)
            else -> getString(R.string.error_unknown)
        }
    }
}
