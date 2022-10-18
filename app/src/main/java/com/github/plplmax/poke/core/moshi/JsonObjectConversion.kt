package com.github.plplmax.poke.core.moshi

import com.squareup.moshi.FromJson
import com.squareup.moshi.JsonReader
import com.squareup.moshi.JsonWriter
import com.squareup.moshi.ToJson
import okio.Buffer
import org.json.JSONException
import org.json.JSONObject

class JsonObjectConversion {
    @Suppress("UNCHECKED_CAST")
    @FromJson
    fun fromJson(reader: JsonReader): JSONObject? {
        return (reader.readJsonValue() as? Map<String, Any>)?.let { data ->
            try {
                JSONObject(data)
            } catch (e: JSONException) {
                return null
            }
        }
    }

    @ToJson
    fun toJson(writer: JsonWriter, value: JSONObject?) {
        value?.let { writer.value(Buffer().writeUtf8(value.toString())) }
    }
}
