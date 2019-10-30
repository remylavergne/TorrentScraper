package enums

import repositories.BaseRepository
import repositories.LeetXRepository
import repositories.ThePirateBayRepository
import repositories.YggRepository

enum class AllRepositories(val server: BaseRepository) {
    YGGTORRENT(YggRepository),
    LEETX(LeetXRepository),
    THEPIRATEBAY(ThePirateBayRepository)
}