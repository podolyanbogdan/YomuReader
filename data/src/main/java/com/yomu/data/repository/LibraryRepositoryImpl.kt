package com.yomu.data.repository

import com.yomu.core.model.MockCategories
import com.yomu.core.model.YomuMangaModel
import com.yomu.domain.repository.LibraryRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flow

class LibraryRepositoryImpl : LibraryRepository {

    private val mockList = mapOf(
        MockCategories.READING to listOf(
            YomuMangaModel(
                name = "Vagabond",
                "https://desu.shikimori.one/uploads/poster/mangas/656/main_alt-aad0307256998f52da3161bd72a16cb9.jpeg"
            ),
            YomuMangaModel(
                name = "Tokyo Ghoul",
                "https://static.wikia.nocookie.net/tokyoghoul/images/7/74/Re_Vol_1.png/revision/latest?cb=20160302120917&path-prefix=ru"
            ),
            YomuMangaModel(
                name = "Vagabond",
                "https://desu.shikimori.one/uploads/poster/mangas/656/main_alt-aad0307256998f52da3161bd72a16cb9.jpeg"
            ),
            YomuMangaModel(
                name = "Tokyo Ghoul",
                "https://static.wikia.nocookie.net/tokyoghoul/images/7/74/Re_Vol_1.png/revision/latest?cb=20160302120917&path-prefix=ru"
            ),
        ),
        MockCategories.WILL_READ to listOf(YomuMangaModel(name = "Tokyo Ghoul")),
        MockCategories.READ_IT to listOf(YomuMangaModel(name = "Berserk"))
    )

    private val flow = MutableStateFlow(mockList)

    override fun getMangaListByCategory(category: MockCategories): Flow<List<YomuMangaModel>> {
        return flow {
            when (category) {
                MockCategories.READING -> emit(flow.value[MockCategories.READING] ?: listOf())
                MockCategories.WILL_READ -> emit(flow.value[MockCategories.WILL_READ] ?: listOf())
                MockCategories.READ_IT -> emit(flow.value[MockCategories.READ_IT] ?: listOf())
            }
        }
    }
}