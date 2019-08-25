package com.tour.hyoram.schedule.repository

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

internal class GoogleSheetRepositoryTest {

    @Test
    fun fetchLastModifiedDateTime() {
        println(GoogleSheetRepository().fetchLastModifiedDateTime())
    }
}