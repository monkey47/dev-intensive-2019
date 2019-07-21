package ru.skillbranch.devintensive.models

import ru.skillbranch.devintensive.utils.Utils
import java.util.*

data class User(
    val id: String,
    var firstName: String?,
    var lastName: String?,
    var avatar: String?,
    var rating: Int = 0,
    var respect: Int = 0,
    val lastVisit: Date? = null,
    val isOnline: Boolean = false
) {
    var introBit: String

    constructor(id:String, firstName:String?, lastName:String?) : this(
        id,
        firstName = firstName,
        lastName = lastName,
        avatar = null
    )

    constructor(id:String) : this(id, firstName= "John", lastName= "Doe")

    init {
        introBit = getIntro()

        println("It's Alive! \n${if (lastName==="Doe") "His name is $firstName $lastName" else "And his name is $firstName $lastName!!!" }\n")
    }

    private fun getIntro()="""
        tut tutututu
        tutututut
        ttutututut
        ${"\n\n\n"}
        $firstName $lastName
    """.trimIndent()

    fun printMe()  {
        println("""
            id $id
            firstName $firstName
            lastName $lastName
            avatar $avatar
            rating $rating
            respect $respect
            lastVisit $lastVisit
            isOnline $isOnline
        """.trimIndent())
    }

    companion object Factory {
        private var lastId = -1
        fun makeUser(fullname:String?) :User{
            lastId++
            val (firstName, lastName) = Utils.parseFullName(fullname)
            return User(id="$lastId", firstName = firstName, lastName = lastName)
        }
        fun Builder():UserBuilder {
            return UserBuilder()
        }
    }

    class UserBuilder {
        private var id: String? = null
        private var firstName: String? = null
        private var lastName: String? = null
        private var avatar: String? = null
        private var rating: Int = 0
        private var respect: Int = 0
        private var lastVisit: Date? = null
        private var isOnline: Boolean = false

        fun id(s:String):UserBuilder {
            id = s
            return this
        }
        fun firstName(s:String):UserBuilder{
            firstName = s
            return this
        }
        fun lastName(s:String):UserBuilder{
            lastName = s
            return this
        }
        fun avatar(s:String):UserBuilder{
            if (s != null && s.isNotEmpty()) {
                avatar = s
            }
            return this
        }
        fun rating(n:Int):UserBuilder{
            rating = n
            return this
        }
        fun respect(n:Int):UserBuilder{
            respect = n
            return this
        }
        fun lastVisit(d: Date):UserBuilder{
            lastVisit = d
            return this
        }
        fun isOnline(b:Boolean):UserBuilder{
            isOnline = b
            return this
        }
        fun build():User {
            return User(
                id=if (id == null) "" else id!!,
                firstName = firstName,
                lastName = lastName,
                avatar = avatar,
                rating = rating,
                respect = respect,
                lastVisit = if(lastVisit == null) Date() else lastVisit,
                isOnline = isOnline
            )
        }
    }
}