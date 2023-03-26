package com.example.getdata

typealias UserList = List<UserContainer>

data class UserContainer(
    val id: Int,
    val username: String,
    val fullName: String,
    val url: String,
    val age: Short
)

val usersContainers = listOf(
    UserContainer(
        id = 1,
        username = "gab.herrera",
        fullName = "Gabriel Herrera",
        url = "https://images.pexels.com/photos/614810/pexels-photo-614810.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=2",
        age = 23
    ),
    UserContainer(
        id = 2,
        username = "ann.solis",
        fullName = "Ann Solís",
        url = "https://images.pexels.com/photos/712513/pexels-photo-712513.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=2",
        age = 24
    ),
    UserContainer(
        id = 3,
        username = "lucia.conti",
        fullName = "Lucía Conti",
        url = "https://images.pexels.com/photos/1462637/pexels-photo-1462637.jpeg?auto=compress&cs=tinysrgb&w=1600",
        age = 21
    ),
    UserContainer(
        id = 4,
        username = "pedro.pascal",
        fullName = "Pedro Pascal",
        url = "https://images.pexels.com/photos/904276/pexels-photo-904276.jpeg?auto=compress&cs=tinysrgb&w=1600",
        age = 34
    ),
    UserContainer(
        id = 5,
        username = "sofia.cabrera",
        fullName = "Sofía Cabrera",
        url = "https://images.pexels.com/photos/789822/pexels-photo-789822.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=2",
        age = 20
    ),
    UserContainer(
        id = 6,
        username = "jose.torres",
        fullName = "José Torres",
        url = "https://images.pexels.com/photos/927022/pexels-photo-927022.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=2",
        age = 27
    ),

)