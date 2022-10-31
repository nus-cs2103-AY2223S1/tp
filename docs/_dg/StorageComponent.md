<!-- markdownlint-disable-file first-line-h1 -->
The Storage Component reads data from, and writes data to, the hard disk.

**API** : [Storage.java]({{ page.master_branch }}/{{ page.main_src }}/storage/Storage.java)

![](images/StorageClassDiagram.png)

The `Storage` component,

* can save both FoodRem data and user preference data in json format, and read them back into corresponding objects.
* inherits from both `FoodRemStorage` and `UserPrefStorage`, which means it can be treated as either one (if only the functionality of only one is needed).
* depends on some classes in the `Model` component (because the `Storage` component's job is to save/retrieve objects that belong to the `Model`)
