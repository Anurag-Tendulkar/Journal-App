package androidsamples.java.journalapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;
//import androidsamples.java.journalapp.EntryDetailsFragmentDirections.PutEntry;


import java.util.Date;
import java.util.UUID;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link EntryDetailsFragment # newInstance} factory method to
 * create an instance of this fragment.
 */
public class EntryDetailsFragment extends Fragment implements OnDialogCloseListener {

  private static final String TAG = "EntryDetailsFragment";
  private JournalEntry mEntry;
  private EntryDetailsSharedViewModel mEntryDetailsSharedViewModel;
  private EditText txtTitle;
  private Button btnSTime;
  private Button btnETime;
  private Button btnDate;
  private Button btnSave;

  @Override
  public void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setHasOptionsMenu(true);
    mEntryDetailsSharedViewModel = new ViewModelProvider(getActivity()).get(EntryDetailsSharedViewModel.class);
    UUID entryID = EntryDetailsFragmentArgs.fromBundle(getArguments()).getEntryId();
    if(entryID != null) {
      mEntryDetailsSharedViewModel.loadEntry(entryID);
      mEntryDetailsSharedViewModel.setOldEntry(true); // for updating old entry on save
    }
    else {
      mEntry = new JournalEntry();
      mEntryDetailsSharedViewModel.setOldEntry(false); // for creating new entry on save
    }
  }


  @Nullable
  @Override
  public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.fragment_entry_details, container, false);

    txtTitle = view.findViewById(R.id.edit_title);
    btnDate = view.findViewById(R.id.btn_entry_date);
    btnSTime = view.findViewById(R.id.btn_start_time);
    btnETime = view.findViewById(R.id.btn_end_time);
    btnSave = view.findViewById(R.id.btn_save);

    mEntryDetailsSharedViewModel.getEntryLiveData().observe(getActivity(),
            entry -> {
              this.mEntry = entry;
              if(this.mEntry != null)
                updateUI();
            });

    btnSave.setOnClickListener(mEntryDetailsSharedViewModel.isOldEntry()? this::saveOldEntry: this::saveNewEntry);
    btnDate.setOnClickListener(this::setDate);
    btnSTime.setOnClickListener(this::setStartTime);
    btnETime.setOnClickListener(this::setEndTime);

    return view;
  }

  private void setEndTime(View view) {
    TimePickerFragment newFragment = TimePickerFragment.newInstance(new Date(), this, 'e');
    newFragment.show(getParentFragmentManager(), "endTimePicker");
  }

  private void setStartTime(View view) {
    TimePickerFragment newFragment = TimePickerFragment.newInstance(new Date(), this, 's');
    newFragment.show(getParentFragmentManager(), "startTimePicker");
  }

  // for menu
  @Override
  public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
    super.onCreateOptionsMenu(menu, inflater);
    inflater.inflate(R.menu.entry_details_fragment_menus, menu);
  }

  @Override
  public boolean onOptionsItemSelected(@NonNull MenuItem item) {
    if(item.getItemId() == R.id.delete_entry) {
      new DeleteEntryFragment(this).show(getParentFragmentManager(), "DELETE");
      return true;
    }
    return super.onOptionsItemSelected(item);
  }

  @Override
  public void onDateDialogClose() {
    btnDate.setText(mEntryDetailsSharedViewModel.setDate());
  }

  @Override
  public void onStartTimeDialogClose() {
    setSTime();
  }

  private void setSTime() {
    btnSTime.setText(mEntryDetailsSharedViewModel.getSTime());
  }

  @Override
  public void onEndTimeDialogClose() {
    setETime();
  }

  @Override
  public void onDeleteEntryDialogClose() {
    mEntryDetailsSharedViewModel.deleteEntry(mEntry);
    NavDirections action = EntryDetailsFragmentDirections.onDeleteToEntryList();
    Navigation.findNavController(requireView()).navigate(action);
  }

  private void setETime() {
    btnETime.setText(mEntryDetailsSharedViewModel.getETime());
  }

  private void setDate(View view) {
    DatePickerFragment newFragment = DatePickerFragment.newInstance(new Date(), this);
    newFragment.show(getParentFragmentManager(), "datePicker");
  }


  private void updateUI() {
    txtTitle.setText(mEntry.getMTitle());
    btnDate.setText(mEntry.getMDate());
    btnSTime.setText(mEntry.getMsTime());
    btnETime.setText(mEntry.getMeTime());
  }

  private void saveNewEntry(View v) {
    mEntry.setMTitle(txtTitle.getText().toString());
    mEntry.setMDate(btnDate.getText().toString());
    mEntry.setMsTime(btnSTime.getText().toString());
    mEntry.setMeTime(btnETime.getText().toString());
    mEntryDetailsSharedViewModel.insertEntry(mEntry);

    Navigation.findNavController(v).navigate(EntryDetailsFragmentDirections.putEntry());
  }

  private void saveOldEntry(View v) {
    mEntry.setMTitle(txtTitle.getText().toString());
    mEntry.setMDate(btnDate.getText().toString());
    mEntry.setMsTime(btnSTime.getText().toString());
    mEntry.setMeTime(btnETime.getText().toString());
    mEntryDetailsSharedViewModel.updateEntry(mEntry);

    Navigation.findNavController(v).navigate(EntryDetailsFragmentDirections.putEntry());
  }
}