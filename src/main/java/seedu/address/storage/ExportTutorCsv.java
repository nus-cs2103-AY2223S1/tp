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


public class ExportTutorCsv {
    private Path filePath;

    public ExportTutorCsv(Path filePath) {
        this.filePath = filePath;
    }

    public void readJson() throws IOException {
        JsonNode jsonTree = new ObjectMapper().readTree(new File(this.filePath.toUri()));
        CsvMapper csvMapper = new CsvMapper();
        SequenceWriter seqW = csvMapper.writer().writeValues(new File("data/tutors.csv"));
        seqW.write(Arrays.asList("name", "phone", "email", "address", "qualification", "institution", "tagged", "tuitionClasses"));


        ArrayList<JsonNode> list = new ArrayList<>();
        jsonTree.elements().next().elements().forEachRemaining(list::add);
        for (JsonNode item : list) {
            ArrayList<String> arr = new ArrayList<>();
            item.fields().forEachRemaining(header -> {
                if (header.getKey().equals("tagged")) {
                    arr.add((handleTagged(header.getValue())));
                } else if (header.getKey().equals("tuitionClasses")) {
                    arr.add(handleTuitionClass(header.getValue()));
                } else {
                    arr.add(header.getValue().textValue());
                }
            });
            seqW.write(arr);
        }
        seqW.close();
    }

    private String handleTuitionClass(JsonNode fullClassList) {
        ArrayList<String> arr = new ArrayList<>();
        fullClassList.elements().forEachRemaining(classes -> {
            classes.fields().forEachRemaining(id -> {
                if (id.getKey().equals("name")) {
                    arr.add(id.getValue().textValue());
                }
            });
        });
        return arr.toString();
    }

    private String handleTagged(JsonNode tagged) {
        ArrayList<String> arr = new ArrayList<>();
        tagged.elements().forEachRemaining(values -> arr.add(values.textValue()));
        return arr.toString();
    }
}


