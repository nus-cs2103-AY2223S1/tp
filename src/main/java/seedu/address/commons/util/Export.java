package seedu.address.commons.util;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import seedu.address.logic.commands.exceptions.CommandException;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;

public class Export {
    public static <T> void exportAsJSON(T fileToExport, Path filePath) throws CommandException {
        try {
            JsonUtil.saveJsonFile(fileToExport, filePath);
        } catch (IOException e){
            throw new CommandException("File corrupted");
        }
    }
}
