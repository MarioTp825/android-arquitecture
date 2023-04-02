package com.example.getdata

import com.github.javafaker.Faker
import kotlinx.coroutines.delay
import kotlin.random.Random

typealias UserList = List<UserContainer>

private const val MAX_IMAGE_URL_VALUE = 80
private const val MAX_USER_AGE = 35

suspend fun userService(amount: Short, secondsDelayed: Short = 1): UserList {
    delay(timeMillis = secondsDelayed * 1000L)
    return amount { buildFakeUser(it) }
}

private inline operator fun <T> Short.invoke(map: (Int) -> T): List<T> {
    val ids = mutableSetOf<Int>()
    while (ids.size < this) {
        ids.add(Random.nextInt(until = MAX_IMAGE_URL_VALUE))
    }
    return ids.map(map)
}

private fun buildFakeUser(index: Int): UserContainer {
    val faker = Faker()
    return UserContainer(
        id = index,
        username = faker.name().username(),
        fullName = faker.name().fullName(),
        url = buildPictureUrl(index),
        age = Random.nextInt(until = MAX_USER_AGE).toShort()
    )
}

private fun buildPictureUrl(pictureId: Int): String =
    (if (pictureId % 2 == 0) "men" else "women").let {
        "https://randomuser.me/api/portraits/$it/$pictureId.jpg"
    }

data class UserContainer(
    val id: Int,
    val username: String,
    val fullName: String,
    val url: String,
    val age: Short
)