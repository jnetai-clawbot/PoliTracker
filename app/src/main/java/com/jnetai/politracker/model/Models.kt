package com.jnetai.politracker.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.Index

@Entity(tableName = "bills", indices = [Index("billNumber")])
data class Bill(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val billNumber: String,
    val title: String,
    val sponsor: String = "",
    val status: String = "Introduced",
    val summary: String = "",
    val dateIntroduced: String = "",
    val category: String = "",
    val stance: String = "Neutral",
    val notes: String = "",
    val createdAt: Long = System.currentTimeMillis()
)

@Entity(tableName = "representatives", indices = [Index("name")])
data class Representative(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val name: String,
    val party: String = "",
    val position: String = "",
    val district: String = "",
    val email: String = "",
    val phone: String = "",
    val notes: String = "",
    val createdAt: Long = System.currentTimeMillis()
)

@Entity(tableName = "actions", indices = [Index("date")])
data class Action(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val title: String,
    val description: String = "",
    val type: String = "Call",
    val date: String = "",
    val billId: Long = 0,
    val representativeId: Long = 0,
    val isCompleted: Boolean = false,
    val notes: String = "",
    val createdAt: Long = System.currentTimeMillis()
)