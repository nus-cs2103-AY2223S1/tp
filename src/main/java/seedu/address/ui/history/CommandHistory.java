package seedu.address.ui.history;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Scanner;

public class CommandHistory {
    private ArrayList<String> previousCommands = new ArrayList<>();
    private int pointer = previousCommands.size();

    /* public void writeCommandHistory() {
        try {
            FileWriter writer = new FileWriter(this.filePath.toString());
            for (int i = 0; i < previousCommands.size(); i++) {
                System.out.println(previousCommands.get(i));
                writer.write(previousCommands.get(i));
            }
            writer.close();
        } catch (IOException e) {
            System.out.println(e);
        }
    } */


    public void add(String userInput) {
        previousCommands.add(userInput);
        reset();
    }

    public void reset() {
        pointer = previousCommands.size();
    }

    public String up() {
        pointer--;
        try {
            return previousCommands.get(pointer);
        } catch (IndexOutOfBoundsException e) {
            pointer++;
            return "";
        }
    }

    public String down() {
        pointer++;
        try {
            return previousCommands.get(pointer);
        } catch (IndexOutOfBoundsException e) {
            pointer = previousCommands.size();
            return "";
        }
    }

}
