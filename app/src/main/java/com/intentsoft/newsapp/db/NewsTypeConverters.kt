package com.intentsoft.newsapp.db

import androidx.room.TypeConverter
import com.intentsoft.newsapp.models.Source

/**
 * @author Zokirjon
 * @date 11/27/2021
 */
class NewsTypeConverters {

    @TypeConverter
    fun fromSource(source: Source): String {
        return source.name
    }

    @TypeConverter
    fun toSource(name: String): Source {
        return Source(name, name)
    }
}