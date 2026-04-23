package com.jnetai.politracker.data

import androidx.room.*
import com.jnetai.politracker.model.Bill
import com.jnetai.politracker.model.Representative
import com.jnetai.politracker.model.Action

@Dao
interface PoliTrackerDao {
    @Query("SELECT * FROM bills ORDER BY createdAt DESC") suspend fun getAllBills(): List<Bill>
    @Query("SELECT * FROM bills WHERE id = :id") suspend fun getBill(id: Long): Bill?
    @Insert suspend fun insertBill(bill: Bill): Long
    @Update suspend fun updateBill(bill: Bill)
    @Delete suspend fun deleteBill(bill: Bill)

    @Query("SELECT * FROM representatives ORDER BY createdAt DESC") suspend fun getAllRepresentatives(): List<Representative>
    @Query("SELECT * FROM representatives WHERE id = :id") suspend fun getRepresentative(id: Long): Representative?
    @Insert suspend fun insertRepresentative(representative: Representative): Long
    @Update suspend fun updateRepresentative(representative: Representative)
    @Delete suspend fun deleteRepresentative(representative: Representative)

    @Query("SELECT * FROM actions ORDER BY createdAt DESC") suspend fun getAllActions(): List<Action>
    @Insert suspend fun insertAction(action: Action): Long
    @Update suspend fun updateAction(action: Action)
    @Delete suspend fun deleteAction(action: Action)
}