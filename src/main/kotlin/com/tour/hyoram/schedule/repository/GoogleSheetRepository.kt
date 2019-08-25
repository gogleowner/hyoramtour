package com.tour.hyoram.schedule.repository

import com.google.api.client.googleapis.auth.oauth2.GoogleCredential
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport
import com.google.api.client.json.jackson2.JacksonFactory
import com.google.api.services.drive.Drive
import com.google.api.services.drive.DriveScopes
import com.google.api.services.sheets.v4.Sheets
import com.google.api.services.sheets.v4.SheetsScopes
import org.springframework.stereotype.Repository

@Repository
class GoogleSheetRepository : TourScheduleRepository {
    private val fileId = "1KcxC92Xu2B6UeO21uwieKwwYOmKrmnSPFlGeJqEPDbM"
    private val sheetService: Sheets
    private val driveService: Drive

    init {
        val jsonFactory = JacksonFactory.getDefaultInstance()
        val httpTransport = GoogleNetHttpTransport.newTrustedTransport()
        val credential: GoogleCredential =
            GoogleCredential.fromStream(this::class.java.getResourceAsStream("/google-servicekey.json"))
                            .createScoped(listOf(SheetsScopes.SPREADSHEETS_READONLY, DriveScopes.DRIVE_METADATA_READONLY))

        sheetService = Sheets.Builder(httpTransport, jsonFactory, credential)
            .setApplicationName("hyoramtour")
            .build()

        driveService = Drive.Builder(httpTransport, jsonFactory, credential)
            .setApplicationName("hyoramtour")
            .build()
    }

    override fun fetchTourSchedules(): MutableList<MutableList<Any>> {
        val response = sheetService.spreadsheets().values()
            .get(fileId, "tour-schedule!A4:I")
            .setDateTimeRenderOption("FORMATTED_STRING")
            .execute()

        return response.getValues()
    }

    override fun fetchLastModifiedDateTime(): String {
        val response = driveService.files()
            .get(fileId)
            .setFields("modifiedTime").execute()

        return response.getOrDefault("modifiedTime", "").toString()
    }
}
