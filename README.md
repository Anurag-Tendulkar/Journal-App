# JournalApp

<b/>Name of Project:</b> JournalEntry <br/> 
<b/>Name:</b>            Anurag Tendulkar<br/>
<b/>BITS ID:</b>         2020A7PS1010G<br/>
<b/>BITS Email:</b>      f20201010@goa.bits-pilani.ac.in<br/>

<h2/> About the app </h2>
The JournalApp is an Android app that allows the user to record daily activities like that in a journal. The user can add new entries by clicking on the 'plus' button, which opens a details journal entry view where the user can enter the title of the app and select the date, start time, and end time. These entries are saved in a room database and can be viewed in a scrollable list view. The user can also edit, delete, or share entries via the app.

<h2/> bugs </h2>
Due to time constraints the end time of an activity can be set before the start time
App crashes on deleting an entry

<h2/> Flow of development </h2>
I began development of JournalApp with an overview of the navigation graph and the various fragments. Actions were implemented to move between the EntryList fragment and the Details fragment. For navigating to the dialogs, the Fragment manager was used to create the dialogs and pass the required listeners to be called after dismissal. I changed the conditions for navigating to different fragments. For example, navigation to the Entry Details Fragment was different for a new or existing entry, with the UID being passed based on the condition. Data was passed between fragments using a SharedViewModel.
