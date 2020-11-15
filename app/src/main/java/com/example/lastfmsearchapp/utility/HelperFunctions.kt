package com.example.lastfmsearchapp.utility

import org.json.JSONObject

object GlobalConstants { // GlobalConstants just an arbitrary name used as a namespace
    const val API_KEY = "2f6d1c3a77d7364fe9c8b7d955a56e11"
}

// It is easier to unit test when this is outside of the ViewModel
fun parseJSONObjectAndExtractDataForDisplaying(jsonObj: JSONObject): MutableList<MutableMap<String, String>> {

    //val artists =  jsonObj.getJSONArray("artist")
    val artists =  jsonObj.getJSONObject("results").getJSONObject("artistmatches").getJSONArray("artist")

    //var allArtistsInfoToShow = mutableListOf<kotlin.collections.mutableMapOf<String, String>()> // TODO REVIEW LATEINIT
    val allArtistsInfoToShow = mutableListOf<MutableMap<String, String>>()
    var infoOfAnArtistToShow: MutableMap<String, String>
    var artistItem: JSONObject
    for (i in 0 until artists.length()) { // until excludes the last value. As there is no iterator for JSONArray, using this approach.
        artistItem = artists.getJSONObject(i)
        infoOfAnArtistToShow = mutableMapOf("name" to artistItem.getString("name"), "listeners" to artistItem.getString("listeners"))
        allArtistsInfoToShow.add(infoOfAnArtistToShow)
    }

    return allArtistsInfoToShow

}