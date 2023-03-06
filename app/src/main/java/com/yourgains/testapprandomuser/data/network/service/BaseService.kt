package com.yourgains.testapprandomuser.data.network.service

interface BaseService {

    companion object {
        const val API: String = "/api"

        const val PATCH_CONTENT_TYPE = "Content-Type: application/merge-patch+json"

        const val PAGE = "page"
        const val ITEMS_PER_PAGE = "itemsPerPage"
        const val DEFAULT_ITEMS_PER_PAGE = 20L
    }
}