---
layout: page
title: Documentation Guide
---
{% include toc.md header=true ordered=true %}

## Setting up and maintaining the project website

* We use [**Jekyll**](https://jekyllrb.com/) to manage documentation.
* The `docs/` folder is used for documentation.
* To learn how set it up and maintain the project website, follow the guide [_[se-edu/guides] **Using Jekyll for project documentation**_](https://se-education.org/guides/tutorials/jekyll.html).
* Note these points when adapting the documentation to a different project/product:
  * The 'Site-wide settings' section of the page linked above has information on how to update site-wide elements such as the top navigation bar.
  * :bulb: In addition to updating content files, you might have to update the config files `docs\_config.yml` and `docs\_sass\minima\_base.scss` (which contains a reference to `AB-3` that comes into play when converting documentation pages to PDF format).
* If you are using Intellij for editing documentation files, you can consider enabling 'soft wrapping' for `*.md` files, as explained in [_[se-edu/guides] **Intellij IDEA: Useful settings**_](https://se-education.org/guides/tutorials/intellijUsefulSettings.html#enabling-soft-wrapping)

## Style Guide

* Follow the [**_Google developer documentation style guide_**](https://developers.google.com/style)

* Also relevant is the [_[se-edu/guides] **Markdown coding standard**_](https://se-education.org/guides/conventions/markdown.html)

## Diagrams

* See the [_[se-edu/guides] **Using PlantUML**_](https://se-education.org/guides/tutorials/plantUml.html)

## Generating PDFs from web pages

In order to support the additional styling made possible by some experimental/non-universal CSS features (such as the `@page` rule), we use [PrinceXML](https://www.princexml.com/) to generate our documentation.

Simply run [`make-pdf.sh`]({{ page.master_branch }}/docs/make-pdf.sh) from the `docs` folder in order to build the PDFs. There is also a GitHub workflow to automatically generate the PDFs for the User Guide and Developer Guide on every release.
