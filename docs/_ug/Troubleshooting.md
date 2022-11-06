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
1. On your terminal, run `java -jar "foodrem.jar"`

If the issue still persists it might be due to the following errors:

**Problem:**

The JAR file not launching in Windows Subsystem for Linux (WSL).

```note
WSL does not support GUI applications by default. WSL users have to manually install and set up a X-server to run such apps on WSL. 

Options include:
* VcXsrv Windows X Server 
* gWSL (which can be installed from the Microsoft Store and helps handle the setting up of X-server)

An exception is windows 11. There seems to be some support for running GUI applications in WSL 2 for windows 11 though it's limited to Windows 11 Build 22000 or later.
```
**Solution:**

1. Our recommendation is to run FoodRem in windows natively and not on WSL.

**Problem:**

The JAR file not launching in Linux machines running Wayland.


To check if the Linux machine is running Wayland, run the following command:

```Bash
echo $XDG_SESSION_TYPE
```

If the output is `wayland`, then the Linux machine is indeed running Wayland.

**Solution:**

```info
Under the hood, FoodRem uses JavaFX 11 to render the UI for the GUI. 
Unfortunately, JavaFX 11 has poor support for Wayland which is why FoodRem is unable to support Wayland currently.
```

1. Install Xorg if it is not present on the machine.
   * Log out of the current session running Wayland and switch to a session running Xorg. The app should now launch properly in Xorg.
2. Install any virtual machine that is not running Wayland on a hypervisor such as VirtualBox or VMware. 
   * The app should also launch properly in the virtual machine if configured properly. 

**Problem:**

Unable to exit/save FoodRem to data file

**Solution:**

1. Verify that the JAR file `foodrem.jar` is not placed in a write-protected folder where administrator privileges are required to write/save FoodRem to the data file. E.g of write-protected folders include `C:\WINDOWS\System32` in windows and the `/etc` dir in linux.

1. If the JAR file is located in a write-protected folder, move it to a folder that is not write-protected.
