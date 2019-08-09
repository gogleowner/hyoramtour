package com.tour.hyoram

import com.google.api.client.auth.oauth2.Credential
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport
import com.google.api.client.http.javanet.NetHttpTransport
import com.google.api.client.json.jackson2.JacksonFactory
import com.google.api.client.util.store.FileDataStoreFactory
import com.google.api.services.sheets.v4.Sheets
import com.google.api.services.sheets.v4.SheetsScopes
import java.io.File
import java.io.InputStreamReader


class GoogleSheetReader {
    val jsonFactory = JacksonFactory.getDefaultInstance()
    val sheetScopes = listOf(SheetsScopes.SPREADSHEETS_READONLY)
    val credentialInputStream = this::class.java.getResourceAsStream("/credentials.json")
    val tokenDirectoryPath = "tokens"

    fun readSampleData() {
        val httpTransport = GoogleNetHttpTransport.newTrustedTransport()
        val credential = getCredential(httpTransport)

        val sheetService = Sheets.Builder(httpTransport, jsonFactory, credential)
            .setApplicationName("sheet api quick start")
            .build()

        val response = sheetService.spreadsheets().values()
//            .get("1BxiMVs0XRA5nFMdKvBdBZjgmUUqptlbs74OgvE2upms", "Class Data!A2:E")
            .get("1KcxC92Xu2B6UeO21uwieKwwYOmKrmnSPFlGeJqEPDbM", "tour-schedule!A4:H")
            .execute()

        val values = response.getValues()

        if (values.isEmpty()) {
            println("No data found.")
        } else {
            println("Name, Major")
            values.forEach {
                println(it)
            }
        }

    }

    private fun getCredential(httpTransport: NetHttpTransport): Credential {
        val clientSecrets = GoogleClientSecrets.load(jsonFactory, InputStreamReader(credentialInputStream))

        val flow = GoogleAuthorizationCodeFlow.Builder(httpTransport, jsonFactory, clientSecrets, sheetScopes)
            .setDataStoreFactory(FileDataStoreFactory(File(tokenDirectoryPath)))
            .setAccessType("offline")
            .build()

        return AuthorizationCodeInstalledApp(
            flow,
            LocalServerReceiver.Builder().setPort(8888).build()
        ).authorize("user")
    }
}


fun main() {
    GoogleSheetReader().readSampleData()
}
