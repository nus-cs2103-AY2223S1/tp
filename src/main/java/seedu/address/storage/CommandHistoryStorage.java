package seedu.address.storage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Scanner;

public class CommandHistoryStorage {
    private ArrayList<String> previousCommands = new ArrayList<>();
    private int pointer = previousCommands.size();
    private Path filePath;

    public CommandHistoryStorage(Path filePath) {
        this.filePath = filePath;

        File file = new File(filePath.toString());
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                System.out.println("File cannot be created");
            }
        }

        try {
            init();
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }
    }

    public CommandHistoryStorage() {}

    public void init() throws FileNotFoundException {

        Scanner s = new Scanner(new File(this.filePath.toString()));

        while (s.hasNext()) {
            String currLine = s.nextLine();
            previousCommands.add(currLine);
        }
    }

    public void writeCommandHistory() {
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
    }


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
