package seedu.address.commons.util;

import static seedu.address.logic.parser.ParserUtil.DEFAULT_LOC_STRING;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
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
            CsvSchema headerSchema = CsvSchema.emptySchema().withHeader();
            ObjectReader objectReader = mapper.readerFor(GenericPerson.class).with(headerSchema);
            MappingIterator<GenericPerson> it = objectReader.readValues(fileToImport);
            List<Person> people = new ArrayList<>();
            while (it.hasNext()) {
                GenericPerson currentPerson = it.next();
                people.add(toModelType(currentPerson));
                lineNo += 1;
            }
            return people;
        } catch (JsonProcessingException e) {
            throw new CommandException(e.getMessage());
        } catch (IOException e) {
            throw new CommandException(e.getMessage());
        } catch (ParseException e) {
            throw new CommandException(e.getMessage() + ", occurs on line " + lineNo);
        } catch (Exception e) {
            throw new CommandException(e.getCause().getMessage().trim());
        }
    }

    /**
     * Converts a GenericPerson into either a {@code Student}, {@code Professor} or {@code TA}
     * @param gp to convert
     * @return Person
     * @throws ParseException when field is invalid
     */
    public static Person toModelType(GenericPerson gp) throws ParseException {
        Name name = ParserUtil.parseName(gp.getName());
        Phone phone = ParserUtil.parsePhone(gp.getPhone());
        Email email = ParserUtil.parseEmail(gp.getEmail());
        Gender gender = ParserUtil.parseGender(gp.getGender());
        Location location = ParserUtil.parseLocation(gp.getLocation());
        Set<Tag> tagSet = ParserUtil.parseTags(gp.getTagged());
        GithubUsername githubUsername = ParserUtil.parseGitHubUsername(gp.getUsername(),
                !gp.getUsername().equals(GithubUsername.DEFAULT_USERNAME));
        if (gp.type.equals("s")) {
            Year year = ParserUtil.parseYear(gp.getYear(), !gp.getYear().equals(Year.EMPTY_YEAR));
            Set<ModuleCode> moduleCodes = ParserUtil.parseModuleCodes(gp.getModuleCodes());
            return new Student(name, phone, email, gender, tagSet, location, githubUsername, moduleCodes, year);
        } else if (gp.type.equals("p")) {
            ModuleCode moduleCode = ParserUtil.parseModuleCode(gp.getModuleCode());
            Specialisation specialisation = ParserUtil.parseSpecialisation(gp.getSpecialisation(),
                    !gp.getSpecialisation().equals(Specialisation.EMPTY_SPECIALISATION));
            Rating rating = ParserUtil.parseRating(gp.getRating(), !gp.getRating().equals(Rating.EMPTY_RATING));
            OfficeHour officeHour = new OfficeHour(gp.getOfficeHour(),
                    !gp.getOfficeHour().equals(OfficeHour.EMPTY_OFFICE_HOUR));
            return new Professor(name, moduleCode, phone, email, gender, tagSet,
                    location, githubUsername, rating, specialisation, officeHour);
        } else if (gp.type.equals("t")) {
            ModuleCode moduleCode = ParserUtil.parseModuleCode(gp.getModuleCode());
            Rating rating = ParserUtil.parseRating(gp.getRating(), !gp.getRating().equals(Rating.EMPTY_RATING));
            return new TeachingAssistant(name, moduleCode, phone, email, gender, tagSet,
                    location, githubUsername, rating);
        } else {
            throw new ParseException("Invalid type " + gp.type);
        }
    }

    private static class GenericPerson {
        private String type;
        private String moduleCodes;
        private String moduleCode;
        private String name;
        private String phone;
        private String email;
        private String gender;
        private String tagged;
        private String location;
        private String username;
        private String rating;
        private String year;
        private String specialisation;
        private String officeHour;

        public GenericPerson() {}

        public void setSpecialisation(String specialisation) {
            this.specialisation = specialisation;
        }
        public void setOfficeHour(String officeHour) {
            this.officeHour = officeHour;
        }
        public void setYear(String year) {
            this.year = year;
        }
        public void setEmail(String email) {
            this.email = email;
        }
        public void setGender(String gender) {
            this.gender = gender;
        }
        public void setLocation(String location) {
            this.location = location;
        }
        public void setModuleCode(String moduleCode) {
            this.moduleCode = moduleCode;
        }
        public void setModuleCodes(String moduleCodes) {
            this.moduleCodes = moduleCodes;
        }
        public void setName(String name) {
            this.name = name;
        }
        public void setPhone(String phone) {
            this.phone = phone;
        }
        public void setRating(String rating) {
            this.rating = rating;
        }
        public void setTagged(String tagged) {
            this.tagged = tagged;
        }
        public void setType(String type) {
            this.type = type;
        }
        public void setUsername(String username) {
            this.username = username;
        }
        public String getName() throws ParseException {
            if (name == null || name.isEmpty()) {
                throw new ParseException("Name cannot be empty");
            }
            return name;
        }
        public String getPhone() throws ParseException {
            if (phone == null || phone.isEmpty()) {
                throw new ParseException("Phone number cannot be empty");
            }
            return phone;
        }
        public String getEmail() throws ParseException {
            if (email == null || email.isEmpty()) {
                throw new ParseException("Email cannot be empty");
            }
            return email;
        }
        public String getGender() throws ParseException {
            if (gender == null || gender.isEmpty()) {
                throw new ParseException("Gender cannot be empty");
            }
            return gender;
        }
        public List<String> getTagged() {
            if (tagged == null || tagged.isEmpty()) {
                return new ArrayList<>();
            }
            String[] tags = tagged.trim().split(";");
            return Arrays.asList(tags);
        }
        public String getLocation() {
            if (location == null || location.isEmpty()) {
                return DEFAULT_LOC_STRING;
            }
            return location;
        }
        public String getUsername() {
            if (username == null || username.isEmpty()) {
                return GithubUsername.DEFAULT_USERNAME;
            }
            return username;
        }
        public String getModuleCode() throws ParseException {
            if (moduleCode == null || moduleCode.isEmpty()) {
                throw new ParseException("Module code cannot be empty");
            }
            return moduleCode;
        }
        public List<String> getModuleCodes() throws ParseException {
            if (moduleCodes == null || moduleCodes.isEmpty()) {
                throw new ParseException("Module codes cannot be empty");
            }
            return Arrays.asList(moduleCodes.trim().split(";"));
        }
        public String getYear() {
            if (year == null || year.isEmpty()) {
                return Year.EMPTY_YEAR;
            }
            return year;
        }
        public String getOfficeHour() throws ParseException {
            if (officeHour == null || officeHour.isEmpty()) {
                return OfficeHour.EMPTY_OFFICE_HOUR;
            }
            if (!OfficeHour.isValidOfficeHour(officeHour.trim())) {
                throw new ParseException("Invalid office hour, office hour must be in DAY, hh:mm a - hh:mm a");
            }
            return officeHour;
        }
        public String getRating() {
            if (rating == null || rating.isEmpty()) {
                return Rating.EMPTY_RATING;
            }
            return rating;
        }

        public String getSpecialisation() {
            if (specialisation == null || specialisation.isEmpty()) {
                return Specialisation.EMPTY_SPECIALISATION;
            }
            return specialisation;
        }
        @Override
        public boolean equals(Object other) {
            if (other == this) {
                return true;
            }
            if (!(other instanceof GenericPerson)) {
                return false;
            }
            GenericPerson e = (GenericPerson) other;
            return type.equals(e.type)
                    && moduleCodes.equals(e.moduleCodes)
                    && moduleCode.equals(e.moduleCode)
                    && name.equals(e.name)
                    && phone.equals(e.phone)
                    && email.equals(e.email)
                    && gender.equals(e.gender)
                    && tagged.equals(e.tagged)
                    && location.equals(e.location)
                    && username.equals(e.username)
                    && rating.equals(e.rating)
                    && year.equals(e.year)
                    && specialisation.equals(e.specialisation)
                    && officeHour.equals(e.specialisation);
        }
    }
}
