package xyz.iterus.chuckles.jokes.ui

import androidx.recyclerview.widget.DiffUtil
import xyz.iterus.chuckles.jokes.domain.model.Joke

class JokeItemDiff(
    private val oldJokeList: List<Joke>,
    private val newJokeList: List<Joke>
): DiffUtil.Callback() {

    override fun getOldListSize(): Int = oldJokeList.size

    override fun getNewListSize(): Int = newJokeList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
        oldJokeList[oldItemPosition].id == newJokeList[newItemPosition].id

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
        oldJokeList[oldItemPosition] == newJokeList[newItemPosition]
}
