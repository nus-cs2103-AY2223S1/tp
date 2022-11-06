---
layout: page
title: Steven's Project Portfolio Page
---

### Project: Gim

Gim is a **desktop app for managing gym exercises, optimized for use via a Command Line Interface** (CLI) while still having the benefits of a Graphical User Interface (GUI). Gim builds on the commands of Vim so if you can type fast and are an avid Vim user, Gim can optimize your exercise routines to a much greater capacity than traditional GUI apps.

Given below are my contributions to the project.

* **New Feature**: `:range start/START_DATE end/END_DATE` command (PR #92, #119) 
  * What it does: Displays a list of exercises within the time period defined by the start date and end date.
  * Justification: This allows users to easily view exercise within a time period, which is a common operation.
  * Highlights: Tracing of the original AB3 code base is necessary to understand how a new command can be added. The integration
  of Java's LocalDate APIs is crucial to this command as well.


* **New Feature**: `:range last/NUMBER_OF_DAYS` (PR #92, #119)
  * What it does: Displays a list of exercises the last N days, where N is the number of days the user inputs.
  * Justification: This allows users to easily view exercise within a time period, without inputting the specific dates.
  * Highlights: Tracing of the original AB3 code base is necessary to understand how a new command can be added. The integration
    of Java's LocalDate APIs is crucial to this command as well.

* **Code contributed**: [RepoSense Link](https://nus-cs2103-ay2223s1.github.io/tp-dashboard/?search=stevenlimhw&breakdown=true&sort=groupTitle&sortWithin=title&since=2022-09-16&timeframe=commit&mergegroup=&groupSelect=groupByRepos&checkedFileTypes=docs~functional-code~test-code~other)

* **Enhancements to existing features**:
  * Allow date to be optional when adding an exercise using `:add` (PR #86, #108)
    * What I did:
      * When the date field for `:add` is empty, the date will be set as today's date by default.
    * Justification:
      * This is to make the `:add` command shorter and increases speed of adding an exercise
      when one wants to add exercises for today.
  * Allow a variety of common date formats (PR #108, #119)
    * What I did:
      * Allow different separators, such as `15/05/2002`, `15 05 2002`,
      `15-05-2022`.
      * Allow Day-Month-Year and Year-Month-Day formats, such as
      `15/05/2022` or `2022/05/15`.
    * Justification:
      * Improve user experience by allowing more input variations.

* **Testing**:
  * Add unit tests for `Date`, `FormatterList`, `RegexList`, `RangeCommand`, `RangeCommandParser`,
  `AddCommandParser`, `DateWithinRangePredicate` to improve code coverage. (PR #217 etc.)

* **Documentation**:
  * User Guide:
    * Common date formats accepted for command inputs (PR #130)
    * Explanation on `:range start/START_DATE end/END_DATE` command (PR #105, #128)
    * Explanation on `:range last/NUMBER_OF_DAYS` command (PR #128)

  * Developer Guide:
    * Class diagram showing the relationship between `Date`, `RegexList` and `FormatterList`
    * Sequence diagram for `:range` command
    * Explanation on the implementation of `:range start/START_DATE end/END_DATE` command
    * Explanation on the implementation of `:range last/NUMBER_OF_DAYS` command
    * Explanation on the implementation of the `Date` class

* **Community**:
  * Add a guide for the team to refer to on how to use Gradle to run tests and checkstyle for
  CI checks locally before making a pull request. (PR #29)
