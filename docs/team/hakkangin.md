# Luo Tian You's Project Portfolio Page

### Project: Yellow Pages
Yellow Pages is a desktop address book application used for students in universities. User interaction is done through CLI and its GUI is created using JavaFX

Contributions:
- **New Features**:
    - **Create Meetings**
        - *Description*: Create new meetings with other contacts in the address book based on the description, date and time, as well as location provided.
        - *Justifications*: Creating meetings is a function that supports our idea of building an application to help university students in creating and organizing meetings.
          Creating a meeting enables the user to note down past and future meetings on Yellow Pages, making it more convenient to keep track and organise meetings.
        - *Highlights*: The implementation here requires the creation of a new parser and command for the create meeting command,
          as well as a new Meeting object and a UniqueMeetingList (similar to UniquePersonsList) to store the meetings in the software.
          I was able to mirror the other commands for the architecture, but at several crucial parts I was required to make my own
          customisations, such as creating a DateTimeProcessor class to convert the input date and time format from the user
          to the desired date and time format stored in the Meeting. 
          - I have also complemented these modifications with adding the corresponding methods to the Model and ModelManager. 
          - Also, I implemented helper methods to match the names of the person to meet to the actual Persons 
            in the address book, as well as accounting for the different cases that might raise exceptions by creating and handling custom Exception classes.
          - In v1.3 I Optimised the validation process of user input for the create new meeting command, such that all validation will be performed before the creation of a Meeting.
            This enables the storing of Meeting objects to be simplified as the date format does not need to be converted between different formats every time storage is accessed.
        - *Credits*: CS2030S for teaching me the necessary programming skills.
  
    - **Delete Meetings**
       - *Description*: Permanently deletes a meeting created by the user.
       - *Justifications*: As an extension to our idea of enabling users to better organise and manage their meetings, 
         deleting meetings enable users to remove the meetings from Yellow Pages if they are cancelled or completed.
         This provides greater flexibility to the user in managing their meetings.
       - *Highlights*: The user command for deleting meetings implements an index-based system; thus users are only required to 
         input the index of the meetings to delete according to the UI, which makes the process more convenient.
        

- **Code contributed**: [RepoSense link](https://nus-cs2103-ay2223s1.github.io/tp-dashboard/?search=&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2022-09-16&tabOpen=true&tabType=authorship&tabAuthor=HakkaNgin&tabRepo=AY2223S1-CS2103-F13-3%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code&authorshipIsBinaryFileTypeChecked=false&authorshipIsIgnoredFilesChecked=false)


- **Project management**:
    - Created various issues corresponding to project management/submission.
      ([\#9](https://github.com/AY2223S1-CS2103-F13-3/tp/issues/9),
      [\#10](https://github.com/AY2223S1-CS2103-F13-3/tp/issues/10),
      [\#12](https://github.com/AY2223S1-CS2103-F13-3/tp/issues/12),
      [\#61](https://github.com/AY2223S1-CS2103-F13-3/tp/issues/61),
      [\#68](https://github.com/AY2223S1-CS2103-F13-3/tp/issues/68))
    - Created various pull requests to merge different feature branches of personal repository to the team repository. 
      Descriptions for pull requests for major updates are writen in the What-Why-How format:
      ([\#11](https://github.com/AY2223S1-CS2103-F13-3/tp/pull/11),
      [\#19](https://github.com/AY2223S1-CS2103-F13-3/tp/issues/19),
      [\#26](https://github.com/AY2223S1-CS2103-F13-3/tp/issues/26),
      [\#27](https://github.com/AY2223S1-CS2103-F13-3/tp/issues/27),
      [\#29](https://github.com/AY2223S1-CS2103-F13-3/tp/issues/29)
      [\#32](https://github.com/AY2223S1-CS2103-F13-3/tp/issues/32),
      [\#34](https://github.com/AY2223S1-CS2103-F13-3/tp/issues/34),
      [\#56](https://github.com/AY2223S1-CS2103-F13-3/tp/issues/56),
      [\#63](https://github.com/AY2223S1-CS2103-F13-3/tp/issues/63),
      [\#67](https://github.com/AY2223S1-CS2103-F13-3/tp/issues/67)
      [\#69](https://github.com/AY2223S1-CS2103-F13-3/tp/issues/69),
      [\#72](https://github.com/AY2223S1-CS2103-F13-3/tp/issues/72),
      [\#75](https://github.com/AY2223S1-CS2103-F13-3/tp/issues/75),
      [\#124](https://github.com/AY2223S1-CS2103-F13-3/tp/issues/124),
      [\#135](https://github.com/AY2223S1-CS2103-F13-3/tp/issues/135))


- **Enhancements to existing features**:
    - NIL


- **Documentation**:
    - User Guide:
        - Added documentation for the `meet` and `deletemeeting` commands. ([\#72](https://github.com/AY2223S1-CS2103-F13-3/tp/pull/72))
    - Developer Guide:
        - Added implementation details the `meet` and `deletemeeting` commands. ([\#56](https://github.com/AY2223S1-CS2103-F13-3/tp/pull/56))
    - Misc.
        - Updated About Us
          ([\#19](https://github.com/AY2223S1-CS2103-F13-3/tp/pull/19))


- **Community**:
    - Wrote non-trivial comments in various PE-D issues raised. (e.g. [\#111](https://github.com/AY2223S1-CS2103-F13-3/tp/issues/111))


- **Tools**:
    - Implemented a DateTimeProcessor class to validate the input date and time format and convert it to the desired output format.
    - Implemented a UniqueMeetingList class to store and manage Meetings created.
