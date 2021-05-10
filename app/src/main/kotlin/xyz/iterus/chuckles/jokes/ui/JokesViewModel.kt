package xyz.iterus.chuckles.jokes.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import xyz.iterus.chuckles.jokes.domain.interactor.JokesInteractor
import xyz.iterus.chuckles.jokes.domain.model.Joke
import java.lang.IllegalArgumentException

class JokesViewModel(private val interactor: JokesInteractor) : ViewModel() {

    private val _jokes: MutableLiveData<List<Joke>> = MutableLiveData()
    val jokes: LiveData<List<Joke>> = _jokes


    fun reloadJokes(count: Int) {
        viewModelScope.launch {
            try {
                _jokes.value = interactor.getRandomJokes(count)
            } catch (e: IllegalArgumentException) {
                // TODO: show error message
            }
        }
    }
}
