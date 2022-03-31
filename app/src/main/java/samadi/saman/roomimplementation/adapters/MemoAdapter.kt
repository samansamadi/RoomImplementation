package samadi.saman.roomimplementation.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import samadi.saman.roomimplementation.R
import samadi.saman.roomimplementation.models.entities.Memo

class MemoAdapter : RecyclerView.Adapter<MemoAdapter.ViewHolder>() {

    companion object {
        const val TAG = "MemoAdapter"
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val title: TextView = itemView.findViewById(R.id.txtTitle)
        private val description: TextView = itemView.findViewById(R.id.txtDescription)

        fun bind(title: String, description: String) {
            this.title.text = title
            this.description.text = description
        }

        companion object {
            fun create(parent: ViewGroup): ViewHolder {
                val view: View = LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_memo, parent, false)
                return ViewHolder(view)
            }
        }
    }

    val differ = AsyncListDiffer(this, MemoDiffUtil())

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder.create(parent)

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = differ.currentList[position]
        holder.bind(item.title, item.description)
    }

    override fun getItemCount(): Int = differ.currentList.size
}

class MemoDiffUtil : DiffUtil.ItemCallback<Memo>() {
    override fun areItemsTheSame(oldItem: Memo, newItem: Memo): Boolean =
        oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: Memo, newItem: Memo): Boolean =
        oldItem == newItem

}