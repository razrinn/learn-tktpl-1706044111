package id.ac.ui.cs.mobileprogramming.helloworld

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.Observer
import com.squareup.picasso.Picasso
import id.ac.ui.cs.mobileprogramming.helloworld.character.CharacterContent

class CharacterFragment : Fragment() {

    companion object {
        fun newInstance() = CharacterFragment()
    }

    private lateinit var viewModel: CharacterViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = ViewModelProviders.of(activity!!).get(CharacterViewModel::class.java)
        val view = inflater.inflate(R.layout.character_fragment, container, false)
        val characterNameView = view.findViewById<TextView>(R.id.character_name)
        val characterDescriptionView = view.findViewById<TextView>(R.id.character_description)
        val characterImage = view.findViewById<ImageView>(R.id.character_image)
        viewModel.character.observe(this,
            Observer<CharacterContent.CharacterItem> { characterItem ->
                characterNameView.text = characterItem.name
                characterDescriptionView.text = characterItem.biography
                Picasso.get().load(characterItem.imageUrl).fit().into(characterImage)
            })
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(CharacterViewModel::class.java)
        // TODO: Use the ViewModel
    }

}