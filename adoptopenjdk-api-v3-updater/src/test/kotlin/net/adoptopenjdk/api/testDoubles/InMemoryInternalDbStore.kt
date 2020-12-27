package net.adoptopenjdk.api.testDoubles

import net.adoptopenjdk.api.v3.dataSources.mongo.CacheDbEntry
import net.adoptopenjdk.api.v3.dataSources.mongo.InternalDbStore
import java.time.ZonedDateTime
import javax.annotation.Priority
import javax.enterprise.inject.Alternative
import javax.inject.Singleton

@Priority(1)
@Alternative
@Singleton
class InMemoryInternalDbStore : InternalDbStore {
    private val cache: MutableMap<String, CacheDbEntry> = HashMap()
    override suspend fun putCachedWebpage(url: String, lastModified: String?, date: ZonedDateTime, data: String?) {
        cache[url] = CacheDbEntry(url, lastModified, date, data)
    }

    override suspend fun getCachedWebpage(url: String): CacheDbEntry? {
        return cache[url]
    }
}
