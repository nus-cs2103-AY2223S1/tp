package seedu.address.commons.util;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import com.fasterxml.jackson.dataformat.csv.CsvSchema.Builder;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Email;
import seedu.address.model.person.Gender;
import seedu.address.model.person.GithubUsername;
import seedu.address.model.person.Location;
import seedu.address.model.person.ModuleCode;
import seedu.address.model.person.Name;
import seedu.address.model.person.OfficeHour;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Professor;
import seedu.address.model.person.Rating;
import seedu.address.model.person.Specialisation;
import seedu.address.model.person.Student;
import seedu.address.model.person.TeachingAssistant;
import seedu.address.model.person.Year;
import seedu.address.model.tag.Tag;

/**
 * Converts JSON to CSV and vice versa
 */
public class CsvUtil {
    /**
     * Converts the specified JSON file to CSV format
     *
     * @param fileToExport   the path of the JSON file to be exported
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

    /**
     * Import the CSV file and creates a list of Person
     * @param fileToImport specified file to be parsed
     * @return List of Person in the CSV file
     * @throws CommandException if the specified file cannot be parsed to
     */
    public static List<Person> importCsv(File fileToImport) throws CommandException {
        int lineNo = 1;
        try {
            CsvMapper mapper = new CsvMapper();
            CsvSchema schema = CsvSchema.emptySchema().withHeader();
            MappingIterator<Map<String, String>> it =
                    mapper.readerFor(Map.class).with(schema).readValues(fileToImport);
            List<Person> people = new ArrayList<>();
            while (it.hasNext()) {
                Map<String, String> currentPerson = it.next();
                String type = currentPerson.get("type");
                String name = currentPerson.get("name");
                Name parsedName = ParserUtil.parseName(name);
                String phone = currentPerson.get("phone");
                Phone parsedPhone = ParserUtil.parsePhone(phone);
                String email = currentPerson.get("email");
                Email parsedEmail = ParserUtil.parseEmail(email);
                String gender = currentPerson.get("gender");
                Gender parsedGender = ParserUtil.parseGender(gender);
                String location = currentPerson.get("location");
                Location parsedLocation = ParserUtil.parseLocation(location);
                String username = currentPerson.get("username");
                GithubUsername parsedUsername = ParserUtil.parseGitHubUsername(username,
                        username != null && !username.isEmpty());
                String currTagged = currentPerson.get("tagged").strip();
                String[] tags = currTagged.split(";");
                Set<Tag> tag;
                if (currTagged != null && currTagged.isEmpty()) {
                    tag = ParserUtil.parseTags(Arrays.asList());
                } else {
                    tag = ParserUtil.parseTags(Arrays.asList(tags));
                }
                if (type.equals("s")) {
                    String currModuleCodes = currentPerson.get("moduleCodes");
                    String[] moduleCodes = currModuleCodes.strip().split(";");
                    Set<ModuleCode> parsedModuleCodes = ParserUtil.parseModuleCodes(Arrays.asList(moduleCodes));
                    String year = currentPerson.get("year");
                    Year parsedYear = ParserUtil.parseYear(year, year != null && !year.isEmpty());
                    people.add(new Student(parsedName, parsedPhone, parsedEmail,
                            parsedGender, tag, parsedLocation, parsedUsername, parsedModuleCodes, parsedYear));
                    lineNo += 1;
                } else if (type.equals("t")) {
                    String moduleCode = currentPerson.get("moduleCode");
                    ModuleCode parsedModuleCode = ParserUtil.parseModuleCode(moduleCode);
                    String rating = currentPerson.get("rating");
                    Rating parsedRating = ParserUtil.parseRating(rating, rating != null && !rating.isEmpty());
                    people.add(new TeachingAssistant(parsedName, parsedModuleCode, parsedPhone, parsedEmail,
                            parsedGender, tag, parsedLocation, parsedUsername, parsedRating));
                    lineNo += 1;
                } else if (type.equals("p")) {
                    String moduleCode = currentPerson.get("moduleCode");
                    ModuleCode parsedModuleCode = ParserUtil.parseModuleCode(moduleCode);
                    String rating = currentPerson.get("rating");
                    Rating parsedRating = ParserUtil.parseRating(rating, rating != null && !rating.isEmpty());
                    String specialisation = currentPerson.get("specialisation");
                    Specialisation parsedSpecialisation = new Specialisation(specialisation);
                    String officeHour = currentPerson.get("officeHour");
                    if (officeHour == null || !OfficeHour.isValidOfficeHour(officeHour)) {
                        throw new ParseException("Office hour does not follow DAY, hh:mm a - hh:mm a");
                    }
                    OfficeHour parsedOfficeHour = new OfficeHour(officeHour, !officeHour.isEmpty());
                    people.add(new Professor(parsedName, parsedModuleCode, parsedPhone, parsedEmail, parsedGender,
                            tag, parsedLocation, parsedUsername, parsedRating, parsedSpecialisation, parsedOfficeHour));
                    lineNo += 1;
                } else {
                    throw new ParseException("The type of " + type + " is invalid");
                }
            }
            return people;
        } catch (JsonProcessingException e) {
            throw new CommandException(e.getMessage());
        } catch (IOException e) {
            throw new CommandException(e.getMessage());
        } catch (ParseException e) {
            throw new CommandException(e.getMessage() + ", occurs on line " + lineNo);
        }
    }
}
