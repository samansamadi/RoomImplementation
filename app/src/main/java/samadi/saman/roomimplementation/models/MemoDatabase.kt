package samadi.saman.roomimplementation.models

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import samadi.saman.roomimplementation.models.dao.MemoDao
import samadi.saman.roomimplementation.models.entities.Memo

@Database(entities = arrayOf(Memo::class), version = 1)
abstract class MemoDatabase : RoomDatabase() {

    abstract fun memoDao(): MemoDao

    companion object {
        const val TAG = "MemoDatabase"

        @Volatile
        private var INSTANCE: MemoDatabase? = null

        fun instance(context: Context): MemoDatabase = INSTANCE ?: synchronized(this) {
            val instance = Room.databaseBuilder(
                context.applicationContext,
                MemoDatabase::class.java,
                "word_database"
            ).build()
            INSTANCE = instance
            // return instance
            instance
        }

    }
}