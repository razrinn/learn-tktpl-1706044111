package id.ac.ui.cs.mobileprogramming.helloworld

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.FragmentActivity

import id.ac.ui.cs.mobileprogramming.helloworld.character.CharacterContent.CharacterItem

/**
 * [RecyclerView.Adapter] that can display a [CharacterItem].
 * TODO: Replace the implementation with code for your data type.
 */
class MyCharacterListRecyclerViewAdapter(
    private val values: List<CharacterItem>,
    private val viewModel: CharacterViewModel
) : RecyclerView.Adapter<MyCharacterListRecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.fragment_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = values[position]
        holder.idView.text = item.id
        holder.contentView.text = item.name
        holder.itemView.setOnClickListener{
            (holder.itemView.context as FragmentActivity).supportFragmentManager.beginTransaction()
                .replace(R.id.character_list_fragment, CharacterFragment())
                .addToBackStack(null).commit()
            viewModel.setCharacter(item)
        }
    }

    override fun getItemCount(): Int = values.size

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val idView: TextView = view.findViewById(R.id.item_number)
        val contentView: TextView = view.findViewById(R.id.content)

        override fun toString(): String {
            return super.toString() + " '" + contentView.text + "'"
        }
    }
}