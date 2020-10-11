package id.ac.ui.cs.mobileprogramming.helloworld.character

import java.util.ArrayList

/**
 * Helper class for providing sample content for user interfaces created by
 * Android template wizards.
 *
 * TODO: Replace all uses of this class before publishing your app.
 */
object CharacterContent {

    /**
     * An array of sample (dummy) items.
     */
    val ITEMS: MutableList<CharacterItem> = ArrayList()


    init {
       generateDummyData()
    }

    private fun addItem(item: CharacterItem) {
        ITEMS.add(item)
    }


    private fun generateDummyData(){
        addItem(CharacterItem(
            "1",
            "Spider-Man",
            "https://vignette.wikia.nocookie.net/marvelcinematicuniverse/images/b/b0/Spider-Man_FFH_Profile.jpg",
            "Spider-Man is a fictional superhero created by writer-editor Stan Lee and writer-artist Steve Ditko. He first appeared in the anthology comic book Amazing Fantasy #15 in the Silver Age of Comic Books."
        ))
        addItem(
            CharacterItem(
            "2",
            "Iron Man",
            "https://upload.wikimedia.org/wikipedia/id/thumb/e/e0/Iron_Man_bleeding_edge.jpg/250px-Iron_Man_bleeding_edge.jpg",
            "Iron Man is a fictional superhero appearing in American comic books published by Marvel Comics. The character was co-created by writer and editor Stan Lee, developed by scripter Larry Lieber, and designed by artists Don Heck and Jack Kirby."
        )
        )
        addItem(CharacterItem(
            "3",
            "Thanos",
            "https://asset.kompas.com/crops/JYQp1VhMdXJDztDpiq20hm4ax6M=/86x0:746x440/750x500/data/photo/2017/11/29/545998801.jpg",
            "Thanos is a fictional supervillain appearing in American comic books published by Marvel Comics. The character was created by writer-artist Jim Starlin, and made his first appearance in The Invincible Iron Man #55."
        ))
        addItem(CharacterItem(
            "4",
            "Black Widow",
            "https://www.wowkeren.com/display/images/photo/2019/12/03/00286041.jpg",
            "Natalia Alianovna \"Natasha Romanoff\" Romanova, colloquial: Black Widow is a fictional superhero appearing in American comic books published by Marvel Comics. Created by editor and plotter Stan Lee, scripter Don Rico, and artist Don Heck, the character debuted in Tales of Suspense #52."
        ))
        addItem(CharacterItem(
            "5",
            "Captain America",
            "https://economictimes.indiatimes.com/thumb/height-450,width-600,imgsize-220108,msid-69139984/captainamerica.jpg",
            "Captain America is a superhero appearing in American comic books published by Marvel Comics. Created by cartoonists Joe Simon and Jack Kirby, the character first appeared in Captain America Comics #1 from Timely Comics, a predecessor of Marvel Comics."
        ))
    }

    /**
     * A dummy item representing a piece of content.
     */
    data class CharacterItem(val id: String, val name: String, val imageUrl: String, val biography: String) {
        override fun toString(): String = name
    }
}