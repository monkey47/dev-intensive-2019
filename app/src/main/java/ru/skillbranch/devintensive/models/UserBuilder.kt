package ru.skillbranch.devintensive.models

import java.util.*

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
        avatar = s
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
    fun build():User{
        if (id == null){
            throw IllegalArgumentException()
        }
        return User(
            id=id!!,
            firstName = firstName,
            lastName = lastName,
            avatar = avatar,
            rating = rating,
            respect = respect,
            lastVisit = lastVisit,
            isOnline = isOnline
        )
    }
}