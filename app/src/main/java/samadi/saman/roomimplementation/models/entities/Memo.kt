package samadi.saman.roomimplementation.models.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "memo")
data class Memo(
    @ColumnInfo(name = "title") val title: String,
    @ColumnInfo(name = "description") val description: String,
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "id") val id: Int = 0,
) {
    override fun equals(other: Any?): Boolean {
        if (other?.javaClass != javaClass)
            return false

        other as Memo

        if (other.id != id)
            return false
        else if (other.description != description)
            return false
        else if (other.title != title)
            return false

        return true
    }
}
