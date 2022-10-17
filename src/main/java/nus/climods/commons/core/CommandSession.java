package nus.climods.commons.core;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

public class CommandSession {
    private final List<String> commands;
    private ListIterator<String> commandScroller;

    public CommandSession() {
        this.commands = new ArrayList<>();
    }

    public void addCommand(String command) {
        this.commands.add(command);
    }

    public String getPreviousCommand() {
        if (commandScroller == null) {
            commandScroller = commands.listIterator(commands.size());
        }

        return commandScroller.hasPrevious() ? commandScroller.previous() : "";
    }
}
