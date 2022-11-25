package androidsamples.java.journalapp;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Date;
import java.util.UUID;

@Entity(tableName = "journal_table")
public class JournalEntry {
    @PrimaryKey
    @ColumnInfo(name = "id")
    @NonNull
    private UUID mUid;


    @ColumnInfo(name = "title")
    private String mTitle;

    @ColumnInfo(name = "date")
    private String mDate;

    @ColumnInfo(name = "start_time")
    private String msTime;

    @ColumnInfo(name = "end_time")
    private String meTime;

    public JournalEntry(@NonNull String title, @NonNull String date, @NonNull String stime, String etime){
        mUid = UUID.randomUUID();
        mTitle = title;
        mDate = date;
        msTime = stime;
        meTime = etime;
    }

    @NonNull
    public UUID getUid() {
        return mUid;
    }


    public String getTitle() {
        return mTitle;
    }

    public String getDate() {
        return mDate;
    }

    public String getSTime() {
        return msTime;
    }

    public String getETime() {
        return meTime;
    }

    public void setUid(@NonNull UUID mUid) {
        this.mUid = mUid;
    }

    public void setTitle(String mTitle) {
        this.mTitle = mTitle;
    }

    public void setDate(String mDate) {
        this.mDate = mDate;
    }

    public void setSTime(String msTime) {
        this.msTime = msTime;
    }

    public void setETime(String meTime) {
        this.meTime = meTime;
    }
}
