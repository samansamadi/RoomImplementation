package samadi.saman.roomimplementation.models.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import samadi.saman.roomimplementation.models.entities.Memo

@Dao
interface MemoDao {

    @Insert
    suspend fun addMemo(memo: Memo)

    @Query("SELECT * from memo ORDER BY id DESC")
    fun getMemos(): Flow<List<Memo>>

    @Query("DELETE from memo where id = :id")
    suspend fun deleteMemo(id: Int)

    @Query("DELETE from memo")
    suspend fun deleteAllMemos()
}