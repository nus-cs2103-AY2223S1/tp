# Su Peigeng's Project Portfolio Page

## Project: Travelr
Travelr allows you to plan trips around the activities in your bucket list. You will be able to keep track of your 
bucket list, travel dates, locations, and itineraries, all within the same app!

## Contributions
Given below are my contributions to the project:
- **New feature:** Added the ability to sort trips in Travelr.
  - **What it does:** Allows the user to sort the trips within Travelr based off of a given factor. 
    The finished and unfinished trips are sorted separately.
  - **Justification:** This will help the user better organise the trips by sorting them in an orderly fashion.
  - **Highlights:** This command forms a key part of how the app helps the user stay organised with the numerous trips 
    within the app by allowing them to sort their trips in an orderly fashion. The implementation was relatively simple
    as it mainly involves the passing down of the trip comparator all the way to the list to sort.
- **New feature:** Added `sort-e` command to sort the events in Travelr.
  - **What it does:** Allow the user to sort the events within Travelr by alphabetical order.
  - **Justification:** This will help the user better organise the events by sorting them in an orderly fashion.
  - **Highlights:** This command is a part of how the app helps the user stay organised with multiple events within the
    app by giving them a mean to sort their events. The implementation was semi difficult as the events are all stored 
    on 1 list, hence although the initial idea was to only sort the events within 1 trip at a time, it was changed to 
    sorting every event in the app instead.
- **Enhancements**
  - Created Event model for use in project
  - Added a class that stores various Comparators for trips
  - Modified the AddCommand to automatically sort the trips by completion,
    with unfinished trips above the finished trips
  - Modified the models to allow sorting of elements in their ObservableList
  - Modified the UI to highlight the event count
- **Code contributed:** https://nus-cs2103-ay2223s1.github.io/tp-dashboard/?search=bacon-strips&breakdown=true


### Testing
- Refactored the original JUnit tests
- Refactored example trips use in JUnit tests

### Documentation
#### User Guide
- Added documentation for `sort` command
- Added documentation for `sort-e` command
#### Developer Guide
- Added use cases for `sort` command