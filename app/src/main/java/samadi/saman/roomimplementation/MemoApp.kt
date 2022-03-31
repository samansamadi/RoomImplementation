package samadi.saman.roomimplementation

import android.app.Application
import samadi.saman.roomimplementation.models.MemoDatabase
import samadi.saman.roomimplementation.repositories.MemoRepository

class MemoApp : Application() {

    companion object {
        const val TAG = "MemoApp"
    }

    val database by lazy { MemoDatabase.instance(this) }
    val repository by lazy { MemoRepository(database.memoDao()) }
}