---
layout: ppp
title: Richard Dominick's Project Portfolio Page
---

<!-- markdownlint-disable-next-line blanks-around-headers -->
### Project: FoodRem
{: .no_toc}

<!-- markdownlint-disable-next-line proper-names -->
{{ site.data.foodrem.about.summary }}

Given below are my contributions to the project.

* PRs Reviewed:

  [[PR#528]] [[PR#527]] [[PR#523]] [[PR#522]] [[PR#521]] [[PR#520]] [[PR#519]] [[PR#518]] [[PR#517]] [[PR#516]] [[PR#513]] [[PR#506]] [[PR#505]] [[PR#466]] [[PR#459]] [[PR#458]] [[PR#450]] [[PR#446]] [[PR#435]] [[PR#434]] [[PR#432]] [[PR#429]] [[PR#428]] [[PR#420]] [[PR#419]] [[PR#376]] [[PR#375]] [[PR#374]] [[PR#372]] [[PR#370]] [[PR#369]] [[PR#368]] [[PR#367]] [[PR#365]] [[PR#362]] [[PR#360]] [[PR#359]] [[PR#358]] [[PR#357]] [[PR#355]] [[PR#353]] [[PR#351]] [[PR#340]] [[PR#335]] [[PR#329]] [[PR#324]] [[PR#319]] [[PR#318]] [[PR#316]] [[PR#310]] [[PR#306]] [[PR#303]] [[PR#302]] [[PR#299]] [[PR#295]] [[PR#292]] [[PR#290]] [[PR#287]] [[PR#286]] [[PR#282]] [[PR#281]] [[PR#278]] [[PR#274]] [[PR#272]] [[PR#264]] [[PR#261]] [[PR#258]] [[PR#257]] [[PR#255]] [[PR#235]] [[PR#234]] [[PR#231]] [[PR#230]] [[PR#229]] [[PR#223]] [[PR#222]] [[PR#211]] [[PR#203]] [[PR#201]] [[PR#198]] [[PR#196]] [[PR#178]] [[PR#176]] [[PR#171]] [[PR#164]] [[PR#163]] [[PR#162]] [[PR#161]] [[PR#160]] [[PR#158]] [[PR#155]] [[PR#102]] [[PR#100]] [[PR#96]] [[PR#95]] [[PR#93]] [[PR#92]] [[PR#91]] [[PR#90]] [[PR#89]] [[PR#88]] [[PR#87]] [[PR#85]] [[PR#83]] [[PR#81]] [[PR#79]] [[PR#78]] [[PR#77]] [[PR#67]] [[PR#65]] [[PR#64]] [[PR#57]] [[PR#35]] [[PR#34]] [[PR#14]] [[PR#2]]

* PRs created (to be categorised later):
  * Add Richard's skeletal PPP and \"About Us\" info [[PR#80]]
  * Add use cases 9 and 10 to DG [[PR#86]]
  * Add skeletal command info for updating items [[PR#95]]
  * Update site config [[PR#97]]
  * Reformat Markdown files using Prettier [[PR#104]]
  * Set up linting for markdown files [[PR#166]]
  * Clean up and fix ToC and other improvements [[PR#170]]
  * Remove tutorials from documentation [[PR#181]]
  * Refactor Glossary [[PR#185]]
  * Redesign glossary and refactor UG/DG [[PR#196]]
  * Add MVP version of FoodRem UI [[PR#198]]
  * Replace static imports where applicable [[PR#203]]
  * Improve tables to minimise HTML usage [[PR#208]]
  * Add missing user stories to DG [[PR#214]]
  * Update miscellaneous items [[PR#215]]
  * Fix markdown links and remove hardcoding [[PR#219]]
  * Refactor codebase [[PR#222]]
  * Renovate website [[PR#238]]
  * Set up CD for JAR file deployment and update DG [[PR#244]]
  * Fix styling-related bugs in markdown files [[PR#251]]
  * Fix broken JAR file CD workflow [[PR#252]]
  * Fix JAR file CD release time [[PR#253]]
  * Fix website compile error [[PR#260]]
  * Clean up code [[PR#263]]
  * Remove duplicate list marker in task lists [[PR#265]]
  * Reformat markdown files [[PR#267]]
  * Fix table markdown parsing [[PR#269]]
  * Restyle website and add quick reference section to glossary [[PR#277]]
  * Support parsing and rendering admonitions in Markdown [[PR#280]]
  * Finalise GUI for the most part [[PR#286]]
  * Add item tests and fix hashcode [[PR#301]]
  * Parse markdown in placeholders table + fix style bug [[PR#309]]
  * Clean up codebase [[PR#310]]
  * Refactor documentation [[PR#313]]
  * Add support to parse HTML in admonitions [[PR#321]]
  * Create glossary entries for \"Placeholders\" values [[PR#322]]
  * Change bullet type for unlinted markdown segments [[PR#323]]
  * Update hash fragment generation for glossary items [[PR#328]]
  * Fix continuous deployment names [[PR#331]]
  * Update website styling [[PR#333]]
  * Fix broken link fragment for GUI [[PR#336]]
  * Fix UG inconsistencies found during peer review [[PR#341]]
  * Disable failing CI on CodeCov error [[PR#352]]
  * Improve UI [[PR#356]]
  * Use custom fonts in GUI for consistency [[PR#361]]
  * Fix find command algorithm [[PR#373]]
  * Reformat markdown files [[PR#422]]
  * Support autoglossary syntax [[PR#424]]
  * Add bidirectional header linking [[PR#426]]
  * Optimise styles for PDF layout [[PR#428]]
  * Add PPP checking workflow [[PR#439]]
  * Always check against PR creator for PPP [[PR#457]]
  * Update documentation [[PR#460]]
  * Fix website index [[PR#461]]
  * Remove raw types [[PR#467]]
  * Remove code duplication and update PPP intro [[PR#468]]
  * Minify HTML output [[PR#469]]
  * Add link checker [[PR#507]]
  * Auto build PDFs together with JAR file [[PR#508]]
  * Make PPP checking more lenient [[PR#510]]
  * Fix extra whitespace in autoglossary items [[PR#511]]
  * Add and style PR badge for PPP [[PR#513]]
  * Fix PDF building script [[PR#514]]

  * Add Issues badge for PPP ([PR#529](https://github.com/AY2223S1-CS2103T-W16-2/tp/pull/529))

<!-- https://nus-cs2103-ay2223s1.github.io/tp-dashboard/?search=richdom2185&breakdown=true -->

<!-- TODO: Categorise everything -->
<!-- * **New Feature**: Added the ability to undo/redo previous commands.

  * What it does: allows the user to undo all previous commands one at a time. Preceding undo commands can be reversed by using the redo command.
  * Justification: This feature improves the product significantly because a user can make mistakes in commands and the app should provide a convenient way to rectify them.
  * Highlights: This enhancement affects existing commands and commands to be added in future. It required an in-depth analysis of design alternatives. The implementation too was challenging as it required changes to existing commands.
  * Credits: _{mention here if you reused any code/ideas from elsewhere or if a third-party library is heavily used in the feature so that a reader can make a more accurate judgement of how much effort went into the feature}_

* **New Feature**: Added a history command that allows the user to navigate to previous commands using up/down keys.

* **Code contributed**: [RepoSense link]()

* **Project management**:

  * Managed releases `v1.3` - `v1.5rc` (3 releases) on GitHub

* **Enhancements to existing features**:

  * Updated the GUI color scheme (Pull requests [\#33](), [\#34]())
  * Wrote additional tests for existing features to increase coverage from 88% to 92% (Pull requests [\#36](), [\#38]())

* **Documentation**:

  * User Guide:
    * Added documentation for the features `delete` and `find` [\#72]()
    * Did cosmetic tweaks to existing documentation of features `clear`, `exit`: [\#74]()
  * Developer Guide:
    * Added implementation details of the `delete` feature.

* **Community**:

  * PRs reviewed (with non-trivial review comments): [\#12](), [\#32](), [\#19](), [\#42]()
  * Contributed to forum discussions (examples: [1](), [2](), [3](), [4]())
  * Reported bugs and suggestions for other teams in the class (examples: [1](), [2](), [3]())
  * Some parts of the history feature I added was adopted by several other class mates ([1](), [2]())

* **Tools**:

  * Integrated a third party library (Natty) to the project ([\#42]())
  * Integrated a new Github plugin (CircleCI) to the team repo

* _{you can add/remove categories in the list above}_ -->
