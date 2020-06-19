package com.mcebotari.moviesapp.data.model

data class MovieDetail(
    val adult : Boolean? = null,
    val backdrop_path : String? = null,
    val belongs_to_collection : String? = null,
    val budget : Int? = null,
    val genres : List<Genres>? = null,
    val homepage : String? = null,
    val id : Int,
    val imdb_id : String? = null,
    val original_language : String? = null,
    val original_title : String? = null,
    val overview : String? = null,
    val popularity : Double? = null,
    val poster_path : String? = null,
    val production_companies : List<ProductionCompanies>? = null,
    val production_countries : List<ProductionCountries>? = null,
    val release_date : String? = null,
    val revenue : Int? = null,
    val runtime : Int? = null,
    val spoken_languages : List<SpokenLanguages>? = null,
    val status : String? = null,
    val tagline : String? = null,
    val title : String? = null,
    val video : Boolean? = null,
    val vote_average : Double? = null,
    val vote_count : Int? = null
)