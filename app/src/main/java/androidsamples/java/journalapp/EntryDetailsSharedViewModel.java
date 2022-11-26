package androidsamples.java.journalapp;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.UUID;

public class EntryDetailsSharedViewModel extends ViewModel {
    private final JournalRepository mRepository;

    private final MutableLiveData<UUID> entryIdLiveData = new MutableLiveData<>();

    private int tYear;
    private int tMonth;
    private int tDayOfMonth;
    private int shr;
    private int sm;
    private int ehr;
    private int em;

    public EntryDetailsSharedViewModel() {
        mRepository = JournalRepository.getInstance();
    }

    void saveEntry(JournalEntry entry) {
        mRepository.update(entry);
    }
    void insertEntry(JournalEntry entry) {
        mRepository.insert(entry);
    }

    LiveData<JournalEntry> getEntryLiveData() {
        return Transformations.switchMap(entryIdLiveData, mRepository::getEntry);
    }

    void loadEntry(UUID entryId) { entryIdLiveData.setValue(entryId); }

    // this method will be called by DatePickerDialog in DatePickerFragment
    public void storeDate(int y, int m, int d) {
        tYear = y;
        tMonth = m;
        tDayOfMonth = d;
    }

    public String setDate() {
        String dayOfWeek = "", month = "";
        GregorianCalendar calendar = new GregorianCalendar(tYear, tMonth, tDayOfMonth);
        switch (tMonth) {
            case 0:
                month = "JAN";
                break;
            case 1:
                month = "FEB";
                break;
            case 2:
                    month = "MAR";
                break;
            case 3:
                month = "MAY";
                break;
            case 4:
                month = "APRIL";
                break;
            case 5:
                month = "JUN";
                break;
            case 6:
                month = "JULY";
                break;
            case 7:
                month = "AUG";
                break;
            case 8:
                month = "SEP";
                break;
            case 9:
                month = "OCT";
                break;
            case 10:
                month = "NOV";
                break;
            case 11:
                month = "DEC";
                break;
        }
        switch (Calendar.DAY_OF_WEEK) {
            case 1:
                dayOfWeek = "SUN";
                break;
            case 2:
                dayOfWeek = "MON";
                break;
            case 3:
                dayOfWeek = "TUE";
                break;
            case 4:
                dayOfWeek = "WED";
                break;
            case 5:
                dayOfWeek = "THURS";
                break;
            case 6:
                dayOfWeek = "FRI";
                break;
            case 7:
                dayOfWeek = "SAT";
                break;
        }
        return "" + dayOfWeek + ", " + month + " " + tDayOfMonth + ", " + tYear;
    }

    public String getSTime() {
        return shr + ":" + sm;
    }

    public String getETime() {
        return ehr + ":" + em;
    }

    public void setStartTime(int hm, int m) {
        shr = hm;
        sm = m;
    }

    public void setEndTime(int hm, int m) {
        ehr = hm;
        em = m;
    }
}
