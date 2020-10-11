package id.ac.ui.cs.mobileprogramming.helloworld

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import id.ac.ui.cs.mobileprogramming.helloworld.character.CharacterContent

class CharacterViewModel : ViewModel() {
    // TODO: Implement the ViewModel
    val character = MutableLiveData<CharacterContent.CharacterItem>()

    fun setCharacter(newValue:CharacterContent.CharacterItem){
        character.setValue(newValue)
    }
}