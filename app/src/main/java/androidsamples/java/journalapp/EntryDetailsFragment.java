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

import java.util.UUID;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link EntryDetailsFragment # newInstance} factory method to
 * create an instance of this fragment.
 */
public class EntryDetailsFragment extends Fragment {

  private JournalEntry mEntry;
  private EntryDetailsViewModel mEntryDetailsViewModel;
  private EditText txtTitle;
  private Button btnSTime;
  private Button btnETime;
  private Button btnDate;
  private Button btnSave;

  @Override
  public void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setHasOptionsMenu(true);
    UUID entryID = EntryDetailsFragmentArgs.fromBundle(getArguments()).getEntryId();
    mEntryDetailsViewModel = new ViewModelProvider(getActivity()).get(EntryDetailsViewModel.class);
    mEntryDetailsViewModel.loadEntry(entryID);
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

    mEntryDetailsViewModel.getEntryLiveData().observe(getActivity(),
            entry -> {
              this.mEntry = entry;
              updateUI();
            });

    btnSave.setOnClickListener(this::saveEntry);

    return view;
  }

  private void updateUI() {
    txtTitle.setText(mEntry.getTitle());
    btnDate.setText(mEntry.getDate());
    btnSTime.setText(mEntry.getSTime());
    btnETime.setText(mEntry.getETime());
  }

  private void saveEntry(View v) {
    mEntry.setTitle(txtTitle.getText().toString());
    mEntry.setDate(btnDate.getText().toString());
    mEntry.setSTime(btnSTime.getText().toString());
    mEntry.setETime(btnETime.getText().toString());

    mEntryDetailsViewModel.saveEntry(mEntry);
  }

  @Override
  public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
  }
}