package seedu.address.commons.util;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import com.fasterxml.jackson.dataformat.csv.CsvSchema.Builder;

import seedu.address.logic.commands.exceptions.CommandException;

/**
 * Converts JSON to CSV and vice versa
 */
public class CsvUtil {
    /**
     * Converts the specified JSON file to CSV format
     * @param fileToExport the path of the JSON file to be exported
     * @param exportLocation the path where the exported file will be stored at
     * @throws CommandException if the specified file cannot be parsed to
     */
    public static void exportAsCsv(Path fileToExport, Path exportLocation) throws CommandException {
        try {
            FileUtil.createIfMissing(exportLocation);
            JsonNode jsonFile = new ObjectMapper().readTree(new File(fileToExport.toUri()));
            JsonNode jsonTree = jsonFile.get("persons");

            Builder csvSchemaBuilder = CsvSchema.builder();
            csvSchemaBuilder
                    .addColumn("type")
                    .addColumn("moduleCodes")
                    .addColumn("moduleCode")
                    .addColumn("name")
                    .addColumn("phone")
                    .addColumn("email")
                    .addColumn("gender")
                    .addColumn("tagged")
                    .addColumn("location")
                    .addColumn("username")
                    .addColumn("rating")
                    .addColumn("year")
                    .addColumn("specialisation")
                    .addColumn("officeHour");
            CsvSchema csvSchema = csvSchemaBuilder.build().withHeader();
            CsvMapper csvMapper = new CsvMapper();
            csvMapper.writerFor(JsonNode.class)
                    .with(csvSchema)
                    .writeValue(new File(exportLocation.toUri()), jsonTree);
        } catch (IOException e) {
            throw new CommandException(e.getMessage());
        }
    }
}
