package samadi.saman.roomimplementation.repositories

import androidx.annotation.WorkerThread
import kotlinx.coroutines.flow.Flow
import samadi.saman.roomimplementation.models.dao.MemoDao
import samadi.saman.roomimplementation.models.entities.Memo

class MemoRepository(private val memoDao: MemoDao) {

    companion object {
        const val TAG = "MainRepository"
    }

    suspend fun allMemos(): Flow<List<Memo>> = memoDao.getMemos()

    @WorkerThread
    suspend fun addMemo(memo: Memo) = memoDao.addMemo(memo)
}