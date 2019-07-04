package be.mickverm.rxdiffutil.sample.ui.items

import androidx.recyclerview.widget.DiffUtil
import be.mickverm.rxdiffutil.sample.data.models.Item

class ItemDiffCallback(
    private val current: List<Item>,
    private val next: List<Item>
) : DiffUtil.Callback() {

    companion object {
        fun create(current: List<Item>, next: List<Item>): ItemDiffCallback {
            return ItemDiffCallback(current, next)
        }
    }

    override fun getOldListSize(): Int {
        return current.size
    }

    override fun getNewListSize(): Int {
        return next.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val currentItem = current[oldItemPosition]
        val nextItem = next[newItemPosition]
        return currentItem.id == nextItem.id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val currentItem = current[oldItemPosition]
        val nextItem = next[newItemPosition]
        return currentItem == nextItem
    }
}