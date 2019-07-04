package be.mickverm.rxdiffutil.sample.ui.items

import androidx.lifecycle.ViewModel
import be.mickverm.rxdiffutil.sample.data.repositories.ItemsRepository
import java.util.concurrent.TimeUnit

class ItemsFragmentViewModel(
    itemsRepository: ItemsRepository
) : ViewModel() {

    val randomItems = itemsRepository.observeItems(3, TimeUnit.SECONDS)
}
