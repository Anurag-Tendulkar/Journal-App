# JournalApp

<b/>Name of Project:</b> JournalEntry <br/> 
<b/>Name:</b>            Anurag Tendulkar<br/>
<b/>BITS ID:</b>         2020A7PS1010G<br/>
<b/>BITS Email:</b>      f20201010@goa.bits-pilani.ac.in<br/>

<h2/> About the app </h2>
The JournalApp is an Android app that allows the user to record daily activities like that in a journal. The user can add new entries by clicking on the 'plus' button, which opens a details journal entry view where the user can enter the title of the app and select the date, start time, and end time. These entries are saved in a room database and can be viewed in a scrollable list view. The user can also edit, delete, or share entries via the app.

<h2/> Flow of development </h2>
I began development of JournalApp with an overview of the navigation graph and the various fragments. Actions were implemented to move between the EntryList fragment and the Details fragment. For navigating to the dialogs, the Fragment manager was used to create the dialogs and pass the required listeners to be called after dismissal. I changed the conditions for navigating to different fragments. For example, navigation to the Entry Details Fragment was different for a new or existing entry, with the UID being passed based on the condition. Data was passed between fragments using a SharedViewModel.

<h2/> Database development </h2>
To implement the room database for the JournalApp, the following steps were taken:

The entity class JournalEntry was created to define the data types of the columns, including UUID for the column id, string for the title, and date and time for the start and end times.
The JournalEntryDao and JournalRoomDatabase were created, with additional functionality for insert and delete added.
The JournalRepository was created to connect to the JournalRoomDatabase, and TypeConverters were created for UUID and time.
The JournalEntryAdapter was created to connect the data and viewmodel. Separate viewmodels were created for the EntryDetails and EntryList fragments.
The RecyclerView was added to the EntryListFragment and connected to the viewmodel.
The addEntry and saveEntry methods were called when clicking on a new or existing entry, respectively.
Dialogs were appropriately called and data was passed back using a SharedViewModel and listeners to update the UI on dismissal.
The Save button called the appropriate add or save method for new or existing entries.

<h2/> delete menu </h2>
I began by adding a delete option to the EntryDetailsFragment menu. I then created a confirmation dialog using an AlertDialog and displayed it when the delete option was selected. I used a SharedViewModel to determine if the user pressed the Yes button for delete. If yes, I used the deleteEntry method to delete the entry, which called an SQL DELETE query underneath.

<h2/> accessibility and testing </h2>
I ran talkback on my app and found that when using the app for the first time the users do not know that selecting the entry alows the user to update it
Accessibility scanner gave a few issuses.
I tried testing my database by reading docs online but there were errors which i could not resolve when i tried to implement it

<h2/> Time and difficulty </h2>
I took me about 30 hours to do this assignment and I rate it 9.5 / 10
