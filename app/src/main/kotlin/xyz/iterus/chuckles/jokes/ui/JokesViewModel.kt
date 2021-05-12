package xyz.iterus.chuckles.jokes.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import xyz.iterus.chuckles.jokes.domain.interactor.JokesInteractor
import xyz.iterus.chuckles.jokes.domain.model.Joke

class JokesViewModel(private val interactor: JokesInteractor) : ViewModel() {

    private val _jokes: MutableLiveData<List<Joke>> = MutableLiveData()
    val jokes: LiveData<List<Joke>> = _jokes
    private val _loading: MutableLiveData<Boolean> = MutableLiveData(false)
    val loading: LiveData<Boolean> = _loading
    private val _jokesError: MutableLiveData<JokesError> = MutableLiveData()
    val jokesError: LiveData<JokesError> = _jokesError

    fun reloadJokes(count: String) {
        if (count.isEmpty()) {
            _jokesError.value = JokesError.EmptyJokesCount
        } else {
            _loading.value = true

            try {
                val n = count.toInt()
                viewModelScope.launch {
                    _jokes.value = interactor.getRandomJokes(n)
                }.invokeOnCompletion {
                    _loading.value = false
                }
            } catch (e: Exception) {
                _loading.value = false
                when (e) {
                    is IllegalArgumentException, is NumberFormatException -> {
                        _jokesError.value = JokesError.InvalidJokesCount
                    }
                    else -> throw e
                }
            }
        }
    }

    sealed class JokesError {
        object EmptyJokesCount : JokesError()
        object InvalidJokesCount : JokesError()
    }
}
