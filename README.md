# Galileo home test

Hey everyone!  This took me a while to get around to doing - so sorry; life has a way of getting in the way these days!

I've implementing it 100% in Kotlin using clean architecture, coroutines and Koin.

I imposed an arbitary time restraint on myself so I've implemented most of the features you asked for, but didn't get around to implementing:
- The results screen
  - There is a mechanism designed to request Q&A data from each fragment on pressing the next button - the fragment would be responsible for the creation of this data.  I imagined this data would be stored in the activity view model, then iterated over for the results screen. 
- Tests O_o
- Data continuity when navigating between fragments
  - Once the above results screen data capture mechanism had been developed, it could easily be retrofitted to also provide past answers on the user going back to a previous screen.
- A nice looking UI

Assumptions made:
- The data will be small enough to store in memory.  This could easily be changed by adding a SQL DB (Room) to support persistence in the data layer and adding a repository to support the change.
- There are no network requirements.  This could be changed by implementing a network repository in the data layer which would handle sending & retrieving data from external sources.
- If we implement the two above then we could change the repository to handle a caching strategy to determine where the data comes from (network, in memory storage, longer term persistence, shared prefs etc)

I hope this provides enough of insight to how I'd go about architecting and developing a system.

Thanks!!

M
