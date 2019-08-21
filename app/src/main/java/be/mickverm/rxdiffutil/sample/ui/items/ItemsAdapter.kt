package be.mickverm.rxdiffutil.sample.ui.items

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import be.mickverm.rxdiffutil.RxDiffUtilAdapter
import be.mickverm.rxdiffutil.sample.R
import be.mickverm.rxdiffutil.sample.data.models.Item

class ItemsAdapter : RxDiffUtilAdapter<Item, ItemsAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
            LayoutInflater.from(parent.context).inflate(
                    R.layout.item_item,
                    parent,
                    false
            )
    )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val textView: TextView = itemView.findViewById(R.id.text)

        fun bind(item: Item) = with(item) {
            itemView.setBackgroundColor(color)
            textView.text = text
        }
    }
}
