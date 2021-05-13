package xyz.iterus.chuckles.jokes.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import xyz.iterus.chuckles.databinding.ListItemJokeBinding
import xyz.iterus.chuckles.jokes.domain.model.Joke

class JokesAdapter(
    private var items: List<Joke>
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    fun submitList(jokes: List<Joke>) {
        val diff = DiffUtil.calculateDiff(
            JokeItemDiff(items, jokes)
        )
        diff.dispatchUpdatesTo(this)

        items = jokes
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ListItemJokeBinding.inflate(inflater, parent, false)

        return JokeViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is JokeViewHolder -> holder.bind(items[position])
            else -> throw NotImplementedError("ViewHolder is not implemented: $holder")
        }
    }

    override fun getItemCount(): Int = items.size


    inner class JokeViewHolder(
        private val binding: ListItemJokeBinding
    ): RecyclerView.ViewHolder(binding.root) {

        fun bind(joke: Joke) {
            binding.joke.text = joke.joke
        }
    }
}
