package ar.com.leandroamarillo.productfinder.productlist

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ar.com.leandroamarillo.productfinder.R
import ar.com.leandroamarillo.productfinder.network.model.Results

class TextItemViewHolder(val textView: TextView) : RecyclerView.ViewHolder(textView)

class ProductAdapter : RecyclerView.Adapter<TextItemViewHolder>() {
    var data = listOf<Results>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: TextItemViewHolder, position: Int) {
        val item = data[position]
        holder.textView.text = item.title
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TextItemViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.text_item_view, parent, false) as TextView
        return TextItemViewHolder(view)
    }
}