package com.harin.entities

import org.ktorm.schema.Table
import org.ktorm.schema.int
import org.ktorm.schema.varchar

/**
 * create object which refer to the database table
 * here <Nothing> is the relation entity,
 * if this table has relation with any other table then we have to add it here
 */
object NoteEntity: Table<Nothing>("note"){
    // column id
    val id = int("id").primaryKey()
    // column note
    val note = varchar("note")
}