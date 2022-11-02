# Justin Widodo's Project Portfolio Page

### Project: Yellow Pages
Yellow Pages is a desktop address book application used for students in universities. User interaction is done through CLI and its GUI is created using JavaFX

Contributions:
- **New Features**:
  - **Filter Meetings**
    - *Description*: Filters meetings between two dates based on the date of each meeting.
    - *Justifications*: Filter by date is a function that supports our idea of building an application to help university students in organizing meetings. 
    Filter helps the user to filter through existing meetings by date to find what meetings lay on which date etc... 
    - *Highlights*: In a way "Find Meeting by Date" is also implemented in this feature as if both dates inputted are equal, then it searches for meetings that fall on that day.
    The only implementation hiccups that were faced was the manipulation/conversion of LocalDateTime variables, otherwise it was pretty straightforward.
    Unit Tests for this feature was also implemented.
  - **Sort Meetings**
    - *Description*: Permanently sorts the list of meetings by date in ascending or descending order.
    - *Justifications*: Along with Filter Meetings feature, Sort Meetings goes hand in hand in helping the user manage their meetings. 
    Date was specifically chosen as it is the most crucial and easily overlooked information about meetings. 
    Hence, the ability to sort meetings by the date helps users to see which meeting they have next.   
    - *Highlights*: Primary difficulty was in finding out a way to sort the list, considering its nested within Observable/ReadOnly List, which was made easier with the ObservableList<>'s sort function. 
    The other difficulty was in determining the order of the sort, I utilized a lambda function to act as a comparable Functional Interface to determine the sorting order.
    Furthermore, enhancements had to be made in the Meeting Class, namely I added a "compareTo" function to compare two meetings together. Unit Tests for this feature was also implemented.
    - *Credits*: CS2030S for teaching me how to do comparator lambdas to sort values and compareTo functions.
  - **Find Meetings**
    - *Description*: Finds meetings based on their description, location and the people they contain.
    - *Justifications*: Shares a similar theme with the two other functions, that being Meeting Object Management. 
    Hence, its only natural to have a find meeting feature that searches meetings using other factors than their date. 
    - *Highlights*: This is the only function in the entirety of Yellow Pages to utilize functional interface classes (FindMeetingFunctionalInterface). 
    The use of functional interfaces here allows the command to easily modified in the case where Meeting adds more fields that can be searched from. Unit Tests for this feature was also implemented.
    - *Credits*: Again thanks to CS2030S for teaching me functional interfaces.

- **Code contributed**: [RepoSense link](https://nus-cs2103-ay2223s1.github.io/tp-dashboard/?search=genfusion122&breakdown=true&sort=groupTitle&sortWithin=title&since=2022-09-16&timeframe=commit&mergegroup=&groupSelect=groupByRepos&checkedFileTypes=docs~functional-code~test-code~other)

- **Project management**: 
  - Managed releases of v1.2, v1.3.trial, v1.3. (and potentially v1.4)
  - Created and Closed milestones v1.2, v1.3. (and potentially v1.4)
  - Created various issues corresponding to project management/submission. 
  ([\#76](https://github.com/AY2223S1-CS2103-F13-3/tp/issues/76), 
  [\#74](https://github.com/AY2223S1-CS2103-F13-3/tp/issues/74), 
  [\#73](https://github.com/AY2223S1-CS2103-F13-3/tp/issues/73), 
  [\#45](https://github.com/AY2223S1-CS2103-F13-3/tp/issues/45),
  [\#40](https://github.com/AY2223S1-CS2103-F13-3/tp/issues/40))

- **Enhancements to existing features**: 
  - Implemented the updates of Person Objects belonging to Meeting Objects when those Person Objects are edited/deleted.
  - Changed the link in the "Help Command".

- **Documentation**:
    - User Guide: 
      - Added documentation for the `sort`, `filter` and `find` meeting commands. ([\#70](https://github.com/AY2223S1-CS2103-F13-3/tp/pull/70))
      - Implemented a table of contents with working links to various anchors within the guide. ([\#134](https://github.com/AY2223S1-CS2103-F13-3/tp/pull/134))
      - Fixed a myriad of typos, missing examples and general issues found in the PE-D. (also in [\#134](https://github.com/AY2223S1-CS2103-F13-3/tp/pull/134))
    - Developer Guide: 
      - Added implementation details of `filter`, `sort` and `find` meetings. 
    - Misc.
      - Updated various other miscellaneous documentation files such as Index.md, UI.png, About Us and README. 
	  ([\#22](https://github.com/AY2223S1-CS2103-F13-3/tp/issues/22),
	  [\#23](https://github.com/AY2223S1-CS2103-F13-3/tp/issues/23),
	  [\#24](https://github.com/AY2223S1-CS2103-F13-3/tp/issues/24))

- **Community**:
  - Wrote non-trivial comments in various PE-D issues raised. (e.g. [\#78](https://github.com/AY2223S1-CS2103-F13-3/tp/issues/78)) 

- **Tools**: 
  - Implemented a Util class for a FunctionalInterface and a DateTimeConverter. (not sure if this counts as tools)
