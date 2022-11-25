package androidsamples.java.journalapp;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;

import java.util.UUID;

public class EntryDetailsViewModel extends ViewModel {
    private final JournalRepository mRepository;

    private final MutableLiveData<UUID> entryIdLiveData = new MutableLiveData<>();

    public EntryDetailsViewModel() {
        mRepository = JournalRepository.getInstance();
    }

    void saveEntry(JournalEntry entry) {
        mRepository.update(entry);
    }

    LiveData<JournalEntry> getEntryLiveData() {
        return Transformations.switchMap(entryIdLiveData, mRepository::getEntry);
    }
}
