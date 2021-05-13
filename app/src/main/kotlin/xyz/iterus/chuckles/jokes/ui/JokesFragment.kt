package xyz.iterus.chuckles.jokes.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import org.koin.android.ext.android.get
import org.koin.androidx.viewmodel.ext.android.viewModel
import xyz.iterus.chuckles.R
import xyz.iterus.chuckles.databinding.FragmentJokesBinding

class JokesFragment : Fragment(R.layout.fragment_jokes) {

    private val model: JokesViewModel by viewModel()
    private var jokesAdapter: JokesAdapter? = get()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val binding = FragmentJokesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentJokesBinding.bind(view)

        binding.jokes.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = jokesAdapter
        }

        setupListeners(binding)
        setupViewModelObservers(binding)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        jokesAdapter = null
    }


    private fun setupListeners(binding: FragmentJokesBinding) {
        binding.reloadButton.setOnClickListener {
            val count = binding.jokesCount.text.toString()
            model.reloadJokes(count)
        }
    }

    private fun setupViewModelObservers(binding: FragmentJokesBinding) {
        with (model) {
            jokes.observe(viewLifecycleOwner, {
                jokesAdapter?.submitList(it)
            })

            loading.observe(viewLifecycleOwner, {
                showProgressBar(it, binding)
            })

            jokesError.observe(viewLifecycleOwner, {
                showInputError(it, binding)
            })
        }
    }

    private fun showProgressBar(isDisplayed: Boolean, binding: FragmentJokesBinding) {
        val visibility = if (isDisplayed) View.VISIBLE else View.GONE
        binding.loading.visibility = visibility
    }

    private fun showInputError(error: JokesViewModel.JokesError, binding: FragmentJokesBinding) {
        binding.jokesCount.error = when (error) {
            is JokesViewModel.JokesError.None -> null
            is JokesViewModel.JokesError.EmptyJokesCount -> getString(R.string.error_input_empty)
            is JokesViewModel.JokesError.InvalidJokesCount -> getString(R.string.error_input_invalid)
            else -> getString(R.string.error_unknown)
        }
    }
}
