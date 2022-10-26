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

import seedu.address.model.person.tutor.Tutor;

/**
 * A class to export {@link Tutor} to a csv-readable format.
 */
public class ExportTutorCsv {
    private final Path filePath;

    public ExportTutorCsv(Path filePath) {
        this.filePath = filePath;
    }

    /**
     * Reads tutorAddressBook.json and converts it into .csv.
     * @throws IOException if unable to write to csv file.
     */
    public void readJson() throws IOException {
        JsonNode jsonTree = new ObjectMapper().readTree(new File(this.filePath.toUri()));
        CsvMapper csvMapper = new CsvMapper();
        SequenceWriter seqW = csvMapper.writer().writeValues(new File("data/tutors.csv"));
        seqW.write(Arrays.asList("name", "phone", "email", "address", "qualification", "institution", "tagged",
                "tuitionClasses"));

        ArrayList<JsonNode> list = new ArrayList<>();
        jsonTree.elements().next().elements().forEachRemaining(list::add);
        for (JsonNode item : list) {
            ArrayList<String> arr = new ArrayList<>();
            item.fields().forEachRemaining(header -> {
                switch (header.getKey()) {
                case "tagged":
                    arr.add((handleTagged(header.getValue())));
                    break;
                case "tuitionClasses":
                    arr.add(handleTuitionClass(header.getValue()));
                    break;
                default:
                    arr.add(header.getValue().textValue());
                }
            });
            seqW.write(arr);
        }
        seqW.close();
    }

    /**
     * Transforms {@code JsonNode} of tuitionClasses field to a string.
     * @param fullClassList JsonNode of tuitionClasses that cannot be represented by a string properly.
     * @return A string representation of an {@code ArrayList} containing the names of the tuitionClass.
     */
    private String handleTuitionClass(JsonNode fullClassList) {
        ArrayList<String> arr = new ArrayList<>();
        fullClassList.elements().forEachRemaining(classes -> classes.fields().forEachRemaining(id -> {
            if (id.getKey().equals("name")) {
                arr.add(id.getValue().textValue());
            }
        }));
        return arr.toString();
    }

    /**
     * Transforms {@code JsonNode} of tagged field to a string.
     * @param tagged {@code JsonNode} of tagged that cannot be represented by a string properly.
     * @return A string representation of an {@code ArrayList} containing the tags of a tutor.
     */
    private String handleTagged(JsonNode tagged) {
        ArrayList<String> arr = new ArrayList<>();
        tagged.elements().forEachRemaining(values -> arr.add(values.textValue()));
        return arr.toString();
    }
}


