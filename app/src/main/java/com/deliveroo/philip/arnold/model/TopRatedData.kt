package com.deliveroo.philip.arnold.model

data class TopRatedData(
    var page: Int = 0,
    var results: ArrayList<Result> = ArrayList(),
    var total_pages: Int = 0,
    var total_results: Int = 0
) {
//    constructor(page: Int, results: ArrayList<Result>, total_pages: Int, total_results: Int): this() {
//        this.page = page
//        this.results = results
//        this.total_pages = total_pages
//        this.total_results = total_results
//    }
}