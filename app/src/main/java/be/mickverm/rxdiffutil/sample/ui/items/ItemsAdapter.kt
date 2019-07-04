package be.mickverm.rxdiffutil.sample.ui.items

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import be.mickverm.rxdiffutil.sample.R
import be.mickverm.rxdiffutil.sample.data.models.Item
import io.reactivex.functions.Consumer

class ItemsAdapter : RecyclerView.Adapter<ItemsAdapter.ViewHolder>(), Consumer<Pair<List<Item>, DiffUtil.DiffResult>> {

    private var items = listOf<Item>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        LayoutInflater.from(parent.context).inflate(
            R.layout.item_item,
            parent,
            false
        )
    )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount() = items.size

    override fun accept(pair: Pair<List<Item>, DiffUtil.DiffResult>) {
        items = pair.first
        pair.second.dispatchUpdatesTo(this)
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val textView: TextView = itemView.findViewById(R.id.text)

        fun bind(item: Item) = with(item) {
            itemView.setBackgroundColor(color)
            textView.text = text
        }
    }
}
