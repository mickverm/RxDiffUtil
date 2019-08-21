package be.mickverm.rxdiffutil.sample.ui.items

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import be.mickverm.rxdiffutil.sample.data.repositories.ItemsRepository

class ItemsFragmentViewModelFactory : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>) = ItemsFragmentViewModel(
            ItemsRepository.getInstance()
    ) as T
}
