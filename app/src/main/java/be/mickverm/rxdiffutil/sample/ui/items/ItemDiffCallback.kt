package be.mickverm.rxdiffutil.sample.ui.items

import androidx.recyclerview.widget.DiffUtil
import be.mickverm.rxdiffutil.sample.data.models.Item

class ItemDiffCallback(
        private val current: List<Item>,
        private val next: List<Item>
) : DiffUtil.Callback() {

    override fun getOldListSize() = current.size
    override fun getNewListSize() = next.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int) =
            current[oldItemPosition].id == next[newItemPosition].id

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int) =
            current[oldItemPosition] == next[newItemPosition]
}
