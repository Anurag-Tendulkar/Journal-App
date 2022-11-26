package androidsamples.java.journalapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

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
    }
    else {
      mEntry = new JournalEntry();
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
              updateUI();
            });

    btnSave.setOnClickListener(this::saveEntry);


    btnDate.setOnClickListener(v -> {
      DatePickerFragment newFragment = DatePickerFragment.newInstance(new Date(), this);
      newFragment.show(getParentFragmentManager(), "datePicker");
    });


    return view;
  }

  private void setDateOnButton() {
    btnDate.setText(mEntryDetailsSharedViewModel.setDate());
  }

  private void updateUI() {
    txtTitle.setText(mEntry.getMTitle());
    btnDate.setText(mEntry.getMDate());
    btnSTime.setText(mEntry.getMsTime());
    btnETime.setText(mEntry.getMeTime());
  }

  private void saveEntry(View v) {
    mEntry.setMTitle(txtTitle.getText().toString());
    mEntry.setMDate(btnDate.getText().toString());
    mEntry.setMsTime(btnSTime.getText().toString());
    mEntry.setMeTime(btnETime.getText().toString());

    mEntryDetailsSharedViewModel.saveEntry(mEntry);
  }

  @Override
  public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
  }

  @Override
  public void onDialogClose() {
    setDateOnButton();
  }
}