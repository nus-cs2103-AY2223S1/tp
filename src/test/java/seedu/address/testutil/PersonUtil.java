package seedu.address.testutil;

import static seedu.address.logic.parser.CliSyntax.FLAG_ADDRESS_STR;
import static seedu.address.logic.parser.CliSyntax.FLAG_EMAIL_STR;
import static seedu.address.logic.parser.CliSyntax.FLAG_NAME_STR;
import static seedu.address.logic.parser.CliSyntax.FLAG_PHONE_STR;
import static seedu.address.logic.parser.CliSyntax.FLAG_TAG_STR;

import java.util.ArrayList;
import java.util.List;

import seedu.address.model.person.Person;
import seedu.address.model.tag.Tag;

/**
 * Utility class to convert {@code Person} to String arguments for testing .
 */
public class PersonUtil {

    /**
     * Converts {@code Person} to required arguments.
     */
    public static String[] convertPersonToArgs(Person person) {
        List<String> argList = new ArrayList<>();
        argList.add(FLAG_NAME_STR);
        argList.add(person.getName().fullName);
        argList.add(FLAG_PHONE_STR);
        argList.add(person.getPhone().value);
        argList.add(FLAG_EMAIL_STR);
        argList.add(person.getEmail().value);
        argList.add(FLAG_ADDRESS_STR);
        argList.add(person.getAddress().value);
        if (!person.getTags().isEmpty()) {
            argList.add(FLAG_TAG_STR);
            for (Tag t : person.getTags()) {
                argList.add(t.tagName);
            }
        }
        return argList.toArray(new String[0]);
    }

    /**
     * Converts {@code Team} to required arguments, along with person index.
     */
    public static String[] convertEditPersonToArgs(Person person, int personIndex) {
        List<String> argList = new ArrayList<>();
        argList.add(String.valueOf(2));
        argList.add(FLAG_NAME_STR);
        argList.add(person.getName().fullName);
        argList.add(FLAG_PHONE_STR);
        argList.add(person.getPhone().value);
        argList.add(FLAG_EMAIL_STR);
        argList.add(person.getEmail().value);
        argList.add(FLAG_ADDRESS_STR);
        argList.add(person.getAddress().value);
        if (!person.getTags().isEmpty()) {
            argList.add(FLAG_TAG_STR);
            for (Tag t : person.getTags()) {
                argList.add(t.tagName);
            }
        }
        return argList.toArray(new String[0]);
    }

    /**
     * Converts {@code Team} to required arguments, along with person index.
     */
    public static String[] convertEditPersonPartialToArgs(Person person, int personIndex) {
        List<String> argList = new ArrayList<>();
        argList.add(String.valueOf(personIndex));
        argList.add(FLAG_NAME_STR);
        argList.add(person.getName().fullName);
        argList.add(FLAG_PHONE_STR);
        argList.add(person.getPhone().value);
        return argList.toArray(new String[0]);
    }

}
