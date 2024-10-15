import Model.BirdImage
import com.rickclephas.kmp.observableviewmodel.ViewModel
import com.rickclephas.kmp.observableviewmodel.coroutineScope
import com.rickclephas.kmp.observableviewmodel.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class BirdUiState(
    val images: List<BirdImage> = emptyList()
)

class BirdsViewModel: ViewModel() {

    private val _uiState = MutableStateFlow<BirdUiState>(viewModelScope, BirdUiState())
    val uiState = _uiState.asStateFlow()

    init {
        updateImages()
    }

    override fun onCleared() {
        BirdRepository().httpClient.close()
    }

    fun updateImages() {
        viewModelScope.coroutineScope.launch {
            val images = getBirdsFromRepo()
            _uiState.update { it.copy(images = images)
            }
        }
    }

    private suspend fun getBirdsFromRepo(): List<BirdImage> {
        val images = BirdRepository().getImages()
        return images
    }
}