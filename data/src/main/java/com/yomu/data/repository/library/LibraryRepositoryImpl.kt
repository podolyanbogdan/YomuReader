package com.yomu.data.repository.library

import com.yomu.core.model.YomuMangaModel
import com.yomu.domain.repository.library.LibraryRepository
import com.yomu.domain.repository.model.library.tabs.LibraryPagerTabs
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flow

class LibraryRepositoryImpl : LibraryRepository {

    private val mockList = mapOf(
        LibraryPagerTabs.READING to listOf(
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
        LibraryPagerTabs.WILL_READ to listOf(YomuMangaModel(name = "Tokyo Ghoul")),
        LibraryPagerTabs.READ_IT to listOf(YomuMangaModel(name = "Berserk"))
    )

    private val flow = MutableStateFlow(mockList)

    override fun getMangaListByCategory(category: LibraryPagerTabs): Flow<List<YomuMangaModel>> {
        return flow {
            when (category) {
                LibraryPagerTabs.READING -> emit(flow.value[LibraryPagerTabs.READING] ?: listOf())
                LibraryPagerTabs.WILL_READ -> emit(
                    flow.value[LibraryPagerTabs.WILL_READ] ?: listOf()
                )

                LibraryPagerTabs.READ_IT -> emit(flow.value[LibraryPagerTabs.READ_IT] ?: listOf())
            }
        }
    }
}