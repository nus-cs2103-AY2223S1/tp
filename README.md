[![CI Status](https://github.com/se-edu/addressbook-level3/workflows/Java%20CI/badge.svg)](https://github.com/se-edu/addressbook-level3/actions)

![Ui](docs/images/Ui.png)

- This is **a sample project for Software Engineering (SE) students**.<br>
  Example usages:
  - as a starting point of a course project (as opposed to writing everything from scratch)
  - as a case study
- The project simulates an ongoing software project for a desktop application (called _AddressBook_) used for managing contact details.
  - It is **written in OOP fashion**. It provides a **reasonably well-written** code base **bigger** (around 6 KLoC) than what students usually write in beginner-level SE modules, without being overwhelmingly big.
  - It comes with a **reasonable level of user and developer documentation**.
- It is named `AddressBook Level 3` (`AB3` for short) because it was initially created as a part of a series of `AddressBook` projects (`Level 1`, `Level 2`, `Level 3` ...).
- For the detailed documentation of this project, see the **[Address Book Product Website](https://se-education.org/addressbook-level3)**.
- This project is a **part of the se-education.org** initiative. If you would like to contribute code to this project, see [se-education.org](https://se-education.org#https://se-education.org/#contributing) for more info.

---

## Setting up

- [ ] _TODO: Move this section to the developer guide once everything is finalised in later stages_

### Setting up Git hooks

1. Ensure [`pre-commit`](https://pre-commit.com/) is installed.
   - macOS (Homebrew):
     ```sh
     brew install pre-commit
     ```
   - Windows, Linux (requires python):
     ```sh
     pip install pre-commit
     ```
1. In the root of the repository, run:
   ```sh
   make hooks
   ```
   Simply re-run this command when hooks need to be updated.

Hooks will automatically run on all **staged** files before commits and pushes.

To manually run hooks for **staged** files, run:

```sh
make lint
```

To manually run hooks for all **tracked** files, run:

```sh
make lint-all
```

If your shell supports double-asterisk `**` globbing you can manually run hooks for all files, including untracked files, by running:

```sh
pre-commit run --files **/*
```

Shells that support this include `zsh` and newer versions of `bash` (version &ge; 4.0, with the `globstar` shell option enabled),
