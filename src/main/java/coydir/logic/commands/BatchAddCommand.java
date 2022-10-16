package coydir.logic.commands;

import static coydir.commons.util.CollectionUtil.requireAllNonNull;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import coydir.logic.commands.exceptions.CommandException;
import coydir.logic.parser.AddCommandParser;
import coydir.logic.parser.exceptions.ParseException;
import coydir.model.Database;
import coydir.model.Model;
import coydir.model.person.Person;

/**
 * Adds multiple person to the database
 */
public class BatchAddCommand extends Command {
    public static final String COMMAND_WORD = "batchadd";
    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Adds multiple people into the database from a csv file \n"
            + "Parameters: filename (must be in the data folder of the repository and CSV format)\n"
            + "Example: " + COMMAND_WORD + " coydir.csv";
    public static final String MESSAGE_SUCCESS = "Batch Add Success. %d employees were added";

    private static final String[] PREFIX_LIST = { "n/", "p/", "e/", "j/", "a/", "t/"};
    private final String filename;
    private Path filePath;

    /**
     * Creates an BatchAddCommand to add the multiple {@code Person}
     */
    public BatchAddCommand(String filename) {
        requireAllNonNull(filename);
        this.filename = filename;
        this.filePath = Paths.get("data", this.filename);
    }

    public void setFilePath(Path filePath) {
        this.filePath = filePath;
    }

    public List<AddCommand> getInfo() throws CommandException {
        Path file = this.filePath;
        String line = "";
        String splitBy = ",";
        List<AddCommand> addCommandList = new ArrayList<>();
        try {
            // parsing a CSV file into BufferedReader class constructor
            BufferedReader br = new BufferedReader(new FileReader(file.toString()));
            br.readLine();
            while ((line = br.readLine()) != null) {
                String[] data = line.split(splitBy); // use comma as separator
                String arg = " ";
                for (int i = 0; i < data.length; i++) {
                    if (i == (data.length - 1)) {
                        String[] tags = data[i].split("/");
                        for (String tag : tags) {
                            arg += PREFIX_LIST[i] + tag + " ";
                        }
                    } else {
                        arg += PREFIX_LIST[i] + data[i] + " ";
                    }
                }
                addCommandList.add(new AddCommandParser().parse(arg));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            throw new CommandException("File Not Found");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            throw new CommandException(e.getMessage());
        }
        return addCommandList;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        List<AddCommand> addCommandList = this.getInfo();
        List<Person> copyOfPersonList = new ArrayList<>();
        for (Person p : model.getDatabase().getPersonList()) {
            copyOfPersonList.add(p);
        }

        if (addCommandList.isEmpty()) {
            throw new CommandException(String.format("%s does not have any data", this.filename));
        }
        try {
            for (AddCommand item : addCommandList) {
                item.execute(model);
            }
        } catch (CommandException e) {
            Database ab = new Database();
            ab.setPersons(copyOfPersonList);
            model.setDatabase(ab);
            throw new CommandException("One person in the list is found to be a duplicate. Call aborted");
        }
        return new CommandResult(String.format(MESSAGE_SUCCESS, addCommandList.size()));
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof BatchAddCommand)) {
            return false;
        }

        // state check
        BatchAddCommand e = (BatchAddCommand) other;
        return filename.equals(e.filename);
    }
}
