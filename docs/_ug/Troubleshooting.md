<!-- markdownlint-disable-file first-line-h1 -->
**Problem:**

The JAR file not launching even after double-clicking the file.

**Solution:**

1. Open your terminal
    * Windows:
        * The default key combination to launch your terminal is <kbd>Ctrl</kbd>+<kbd>Shift</kbd>+<kbd>P</kbd>
    * Mac:
        * Use <kbd>Cmd</kbd>+<kbd>Space</kbd> to open Spotlight Search
        * Search for "terminal" and click it to launch.
1. Navigate to the location where "foodrem.jar" is stored within your terminal.
1. On your terminal, run `java -jar "foodrem.jar"`


**Problem:**

The JAR file not launching in Windows Subsystem for Linux (WSL).

```note
WSL does not support GUI applications by default.
```
**Solution:**

1. Our recommendation is to run FoodRem on Windows and not on WSL.

**Problem:**

The JAR file not launching in Linux machines running Wayland.

**Solution:**

```info
Under the hood, FoodRem uses JavaFX 11 to render the UI for the GUI. 
Unfortunately, JavaFX 11 has poor support for Wayland which is why FoodRem is unable to support Wayland currently.
```

1. FoodRem is only supported on machines with the following operating systems: Windows, macOS and Linux11. Please use a computer running on these operating systems.

**Problem:**

Unable to exit/save FoodRem to data file

**Solution:**

1. This error is due to `foodrem.jar` being started in a protected folder. (Examples of write-protected folders include `C:\WINDOWS\System32` in windows and the `/etc` dir in linux) <br> Please move the `foodrem.jar` file into another folder in your computer and start FoodRem from that folder.
