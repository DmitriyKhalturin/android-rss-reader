package dmitriykhalturin.data.database.entity

import android.arch.persistence.room.*

/**
 * Created by Dmitriy Khalturin <dmitry.halturin.86@gmail.com>
 * for android-rss-reader on 29.10.18 0:11.
 */
@Entity(
  tableName = "settings",
  foreignKeys = [ForeignKey(
    entity = FeedEntity::class,
    parentColumns = ["id"],
    childColumns = ["feed_id"]
  )],
  indices = [
    Index("feed_id")
  ]
)
data class SettingsEntity (

  @PrimaryKey
  @ColumnInfo(name = "id")
  val mId: Long,

  @ColumnInfo(name = "feed_id")
  val mFeedId: Long?

)
