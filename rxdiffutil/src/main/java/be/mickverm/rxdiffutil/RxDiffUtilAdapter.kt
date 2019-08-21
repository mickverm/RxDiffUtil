package be.mickverm.rxdiffutil

import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import io.reactivex.functions.Consumer

abstract class RxDiffUtilAdapter<T, VH : RecyclerView.ViewHolder> : RecyclerView.Adapter<VH>(),
        Consumer<Pair<List<T>, DiffUtil.DiffResult>> {

    var items: List<T> = listOf()
        private set

    final override fun getItemCount() = items.size

    fun getItem(position: Int): T = items[position]

    final override fun accept(pair: Pair<List<T>, DiffUtil.DiffResult>) {
        items = pair.first
        pair.second.dispatchUpdatesTo(this)
    }
}
