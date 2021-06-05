package com.dicoding.motive.utils

import com.dicoding.motive.data.source.local.entity.MovieEntity
import com.dicoding.motive.data.source.local.entity.TvEntity

object DataDummy {

    fun generateDummyMovie(): List<MovieEntity> {

        val courses: ArrayList<MovieEntity> = ArrayList()

        return courses.apply {
            add(
                MovieEntity(
                    1,
                    "/A0knvX7rlwTyZSKj8H5NiARb45.jpg",
                    "Cruella",
                    "2021-05-26",
                    "In 1970s London amidst the punk rock revolution, a young grifter named Estella is determined to make a name for herself with her designs. She befriends a pair of young thieves who appreciate her appetite for mischief, and together they are able to build a life for themselves on the London streets. One day, Estella’s flair for fashion catches the eye of the Baroness von Hellman, a fashion legend who is devastatingly chic and terrifyingly haute. But their relationship sets in motion a course of events and revelations that will cause Estella to embrace her wicked side and become the raucous, " +
                            "fashionable and revenge-bent Cruella.",
                )
            )
            add(
                MovieEntity(
                    2,
                    "/bShgiEQoPnWdw4LBrYT5u18JF34.jpg",
                    "The Unholy",
                    "2021-03-31",
                    "Alice, a young hearing-impaired girl who, after a supposed visitation from the Virgin Mary, is inexplicably able to hear, speak and heal the sick. As word spreads and people from near and far flock to witness her miracles, a disgraced journalist hoping to revive his career visits the small New England town to investigate. When terrifying events begin to happen all around, he starts to question if these phenomena are the works of the Virgin Mary or something much more sinister.",
                )
            )

            add(
                MovieEntity(
                    3,
                    "/YxopfHpsCV1oF8CZaL4M3Eodqa.jpg",
                    "Wrath of Man",
                    "2021-04-22",
                    "A cold and mysterious new security guard for a Los Angeles cash truck company surprises his co-workers when he unleashes precision skills during a heist. The crew is left wondering who he is and where he came from. Soon, the marksman's ultimate motive becomes clear as he takes dramatic and irrevocable steps to settle a score.",
                )
            )

            add(
                MovieEntity(
                    4,
                    "/nkayOAUBUu4mMvyNf9iHSUiPjF1.jpg",
                    "Mortal Kombat",
                    "2021-04-07",
                    "Washed-up MMA fighter Cole Young, unaware of his heritage, and hunted by Emperor Shang Tsung's best warrior, Sub-Zero, seeks out and trains with Earth's greatest champions as he prepares to stand against the enemies of Outworld in a high stakes battle for the universe.",
                )
            )

            add(
                MovieEntity(
                    5,
                    "/z8CExJekGrEThbpMXAmCFvvgoJR.jpg",
                    "Army of the Dead",
                    "2021-05-14",
                    "Following a zombie outbreak in Las Vegas, a group of mercenaries take the ultimate gamble: venturing into the quarantine zone to pull off the greatest heist ever attempted.",
                )
            )
            add(
                MovieEntity(
                    6,
                    "/nkayOAUBUu4mMvyNf9iHSUiPjF1.jpg",
                    "Mortal Kombat",
                    "2021-04-07",
                    "Washed-up MMA fighter Cole Young, unaware of his heritage, and hunted by Emperor Shang Tsung's best warrior, Sub-Zero, seeks out and trains with Earth's greatest champions as he prepares to stand against the enemies of Outworld in a high stakes battle for the universe.",
                )
            )

            add(
                MovieEntity(
                    7,
                    "/rEm96ib0sPiZBADNKBHKBv5bve9.jpg",
                    "Tom Clancy's Without Remorse",
                    "2021-04-29",
                    "An elite Navy SEAL uncovers an international conspiracy while seeking justice for the murder of his pregnant wife.",
                )
            )

            add(
                MovieEntity(
                    8,
                    "/m6bUeV4mczG3z2YXXr5XDKPsQzv.jpg",
                    "I Am All Girls",
                    "2021-05-14",
                    "A special crimes investigator forms an unlikely bond with a serial killer to bring down a global child sex trafficking syndicate.",
                )
            )

            add(
                MovieEntity(
                    9,
                    "/pgqgaUx1cJb5oZQQ5v0tNARCeBp.jpg",
                    "Godzilla vs. Kong",
                    "2021-03-24",
                    "In a time when monsters walk the Earth, humanity’s fight for its future sets Godzilla and Kong on a collision course that will see the two most powerful forces of nature on the planet collide in a spectacular battle for the ages.",
                )
            )

            add(
                MovieEntity(
                    10,
                    "/vXHzO26mJaOt4VO7ZFiM6No5ScT.jpg",
                    "The Virtuoso",
                    "2021-03-31",
                    "A lonesome stranger with nerves of steel must track down and kill a rogue hitman to satisfy an outstanding debt. But the only information he's been given is a time and location where to find his quarry. No name. No description. Nothing.",
                )
            )
        }
    }

    fun generateDummyDetailMovie(id: Int): MovieEntity? {

        for (t in generateDummyMovie()) {
            if (id == t.id)
                return t
        }
        return null
    }

    fun generateDummyTv(): List<TvEntity> {

        val courses: ArrayList<TvEntity> = ArrayList()

        return courses.apply {
            add(
                TvEntity(
                    1,
                    "/4EYPN5mVIhKLfxGruy7Dy41dTVn.jpg",
                    "Lucifer",
                    "2016-01-25",
                    "Bored and unhappy as the Lord of Hell, Lucifer Morningstar abandoned his throne and retired to Los Angeles, where he has teamed up with LAPD detective Chloe Decker to take down criminals. But the longer he's away from the underworld, the greater the threat that the worst of humanity could escape.",
                )
            )

            add(
                TvEntity(
                    2,
                    "/xUtOM1QO4r8w8yeE00QvBdq58N5.jpg",
                    "Ragnarok",
                    "2020-01-31",
                    "A small Norwegian town experiencing warm winters and violent downpours seems to be headed for another Ragnarök -- unless someone intervenes in time.",
                )
            )

            add(
                TvEntity(
                    3,
                    "/lJA2RCMfsWoskqlQhXPSLFQGXEJ.jpg",
                    "The Flash",
                    "2014-10-07",
                    "After a particle accelerator causes a freak storm, CSI Investigator Barry Allen is struck by lightning and falls into a coma. Months later he awakens with the power of super speed, granting him the ability to move through Central City like an unseen guardian angel. Though initially excited by his newfound powers, Barry is shocked to discover he is not the only \"meta-human\" who was created in the wake of the accelerator explosion -- and not everyone is using their new powers for good. Barry partners with S.T.A.R. Labs and dedicates his life to protect the innocent. For now, only a few close friends and associates know that Barry is literally the fastest man alive, but it won't be long before the world learns what Barry Allen has become...The Flash.",
                    )
            )

            add(
                TvEntity(
                    4,
                    "/dYvIUzdh6TUv4IFRq8UBkX7bNNu.jpg",
                    "The Good Doctor",
                    "2017-09-25",
                    "A young surgeon with Savant syndrome is recruited into the surgical unit of a prestigious hospital. The question will arise: can a person who doesn't have the ability to relate to people actually save their lives",
                    )
            )

            add(
                TvEntity(
                    5,
                    "/o7uk5ChRt3quPIv8PcvPfzyXdMw.jpg",
                    "¿Quién mató a Sara?",
                    "2021-03-24",
                    "Hell-bent on exacting revenge and proving he was framed for his sister's murder, Álex sets out to unearth much more than the crime's real culprit.",
                    )
            )

            add(
                TvEntity(
                    6,
                    "/vlv1gn98GqMnKHLSh0dNciqGfBl.jpg",
                    "Superman & Lois",
                    "2021-02-23",
                    "After years of facing megalomaniacal supervillains, monsters wreaking havoc on Metropolis, and alien invaders intent on wiping out the human race, The Man of Steel aka Clark Kent and Lois Lane come face to face with one of their greatest challenges ever: dealing with all the stress, pressures and complexities that come with being working parents in today's society."
                    )
            )

            add(
                TvEntity(
                    3,
                    "/clnyhPqj1SNgpAdeSS6a6fwE6Bo.jpg",
                    "Grey's Anatomy",
                    "2005-03-27",
                    "Follows the personal and professional lives of a group of doctors at Seattle’s Grey Sloan Memorial Hospital.",
                    )
            )

            add(
                TvEntity(
                    8,
                    "/6kbAMLteGO8yyewYau6bJ683sw7.jp",
                    "The Falcon and the Winter Soldier",
                    "2021-03-19",
                    "Following the events of “Avengers: Endgame”, the Falcon, Sam Wilson and the Winter Soldier, Bucky Barnes team up in a global adventure that tests their abilities, and their patience.",
                    )
            )

            add(
                TvEntity(
                    9,
                    "/fuVuDYrs8sxvEolnYr0wCSvtyTi.jpg",
                    "La Reina del Flow",
                    "2018-06-12",
                    "After spending seventeen years in prison unfairly, a talented songwriter seeks revenge on the men who sank her and killed her family."
                    )
            )

            add(
                TvEntity(
                    10,
                    "/yDWJYRAwMNKbIYT8ZB33qy84uzO.jpg",
                    "Invincible",
                    "2021-03-26",
                    "Mark Grayson is a normal teenager except for the fact that his father is the most powerful superhero on the planet. Shortly after his seventeenth birthday, Mark begins to develop powers of his own and enters into his father’s tutelage.",
                    )
            )
        }
    }

    fun generateDummyTvDetail(id: Int): TvEntity? {

        for (t in generateDummyTv()) {
            if (id == t.id)
                return t
        }
        return null
    }

}
