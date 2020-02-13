package com.example.contentproviderdemo

// Contract file stores all constants

const val DATABASE_NAME = "database"
const val DATABASE_VERSION = 1
const val TABLE_NAME = "person_table"

// Columns
const val COL_FIRST_NAME = "first_name"
const val COL_LAST_NAME = "last_name"
const val COL_JOB = "job"
const val COL_FAVORITE = "favorite"
const val COL_ID = "id"

// Create Table

const val CREATE_CELEBRITY_TABLE =
    "CREATE TABLE $TABLE_NAME (" +
            "$COL_FIRST_NAME String," +
            "$COL_LAST_NAME String," +
            "$COL_JOB String," +
            "$COL_FAVORITE String," +
            "$COL_ID String PRIMARY_KEY)"

