package seedu.address.storage;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SequenceWriter;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;

import seedu.address.model.tuitionclass.TuitionClass;

/**
 * A class to export {@link TuitionClass} to a csv-readable format.
 */
public class ExportTuitionClassCsv {
    private final Path filePath;

    public ExportTuitionClassCsv(Path filePath) {
        this.filePath = filePath;
    }

    /**
     * Reads tuitionClassAddressBook.json and converts it into .csv.
     * @throws IOException if unable to write to csv file.
     */
    public void readJson() throws IOException {
        JsonNode jsonTree = new ObjectMapper().readTree(new File(this.filePath.toUri()));
        CsvMapper csvMapper = new CsvMapper();
        SequenceWriter seqW = csvMapper.writer().writeValues(new File("data/tuitionClasses.csv"));
        seqW.write(Arrays.asList("name", "subject", "level", "day", "startTime", "endTime", "tagged"));

        ArrayList<JsonNode> list = new ArrayList<>();
        jsonTree.elements().next().elements().forEachRemaining(list::add);
        for (JsonNode item : list) {
            ArrayList<String> arr = new ArrayList<>();
            item.fields().forEachRemaining(header -> {
                if (header.getKey().equals("tagged")) {
                    arr.add((handleTagged(header.getValue())));
                } else {
                    arr.add(header.getValue().textValue());
                }
            });
            seqW.write(arr);
        }
        seqW.close();
    }

    /**
     * Transforms {@code JsonNode} of tagged field to a string.
     * @param tagged {@code JsonNode} of tagged that cannot be represented by a string properly.
     * @return A string representation of an {@code ArrayList} containing the tags of a tuitionClass.
     */
    private String handleTagged(JsonNode tagged) {
        ArrayList<String> arr = new ArrayList<>();
        tagged.elements().forEachRemaining(values -> arr.add(values.textValue()));
        return arr.toString();
    }
}


