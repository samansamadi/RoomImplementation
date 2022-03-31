package samadi.saman.roomimplementation.viewmodels

import androidx.lifecycle.*
import kotlinx.coroutines.launch
import samadi.saman.roomimplementation.models.entities.Memo
import samadi.saman.roomimplementation.repositories.MemoRepository
import java.lang.IllegalArgumentException

class MainViewModel(private val repository: MemoRepository) : ViewModel() {

    companion object {
        const val TAG = "MainViewModel"
    }

    init {
        getMemos()
    }

    fun getMemos(): LiveData<List<Memo>> {
        var liveData: LiveData<List<Memo>>? = null
        viewModelScope.launch {
            liveData = repository.allMemos().asLiveData()
        }

        return liveData!!
    }

    suspend fun addMemo(memo: Memo) = viewModelScope.launch {
        repository.addMemo(memo)
    }
}

class MainViewModelFactory(private val repository: MemoRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java))
            return MainViewModel(repository) as T

        throw IllegalArgumentException("Unknown ViewModel class")
    }
}