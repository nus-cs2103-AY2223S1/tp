package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CustomiseCommand.CustomiseSubCommand.HIDE;
import static seedu.address.logic.commands.CustomiseCommand.CustomiseSubCommand.ORDER;
import static seedu.address.logic.commands.CustomiseCommand.CustomiseSubCommand.SHOW;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.ArrayList;

import seedu.address.logic.commands.CustomiseCommand;
import seedu.address.logic.commands.CustomiseCommand.Attribute;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new CustomiseCommand object.
 */
public class CustomiseCommandParser implements Parser<CustomiseCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the CustomiseCommand
     * and returns a CustomiseCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public CustomiseCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, CustomiseCommand.MESSAGE_USAGE));
        }

        String[] arguments = trimmedArgs.split("\\s+");
        if (arguments.length <= 1 || arguments.length > 5) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, CustomiseCommand.MESSAGE_USAGE));
        }

        if (arguments[0].equals("order")) {
            return new CustomiseCommand(ORDER, toAttributeOrder(arguments));
        } else if (arguments[0].equals("hide")) {
            return new CustomiseCommand(HIDE, toAttributes(arguments));
        } else if (arguments[0].equals("show")) {
            return new CustomiseCommand(SHOW, toAttributes(arguments));
        } else {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, CustomiseCommand.MESSAGE_USAGE));
        }
    }

    /**
     * Converts the input into an array of Attribute objects in the required order.
     *
     * @param args an array of argument inputs
     * @return an array of Attribute
     * @throws ParseException if the user input does not conform the expected format
     */
    private Attribute[] toAttributeOrder(String[] args) throws ParseException {
        boolean[] isAttributeUsed = new boolean[4]; //0: ADDRESS, 1: EMAIL, 2: PHONE, 3: TAGS
        int noOfMissing = 4 - (args.length - 1); //first element of args is the subCommand
        Attribute[] attributeArr = new Attribute[4];

        //first element is ignored as it is the subCommand
        for (int i = 1; i < args.length; i++) {
            if (args[i].equals(PREFIX_ADDRESS.toString()) && !isAttributeUsed[0]) {
                attributeArr[i - 1] = Attribute.ADDRESS;
                isAttributeUsed[0] = true;
            } else if (args[i].equals(PREFIX_EMAIL.toString()) && !isAttributeUsed[1]) {
                attributeArr[i - 1] = Attribute.EMAIL;
                isAttributeUsed[1] = true;
            } else if (args[i].equals(PREFIX_PHONE.toString()) && !isAttributeUsed[2]) {
                attributeArr[i - 1] = Attribute.PHONE;
                isAttributeUsed[2] = true;
            } else if (args[i].equals(PREFIX_TAG.toString()) && !isAttributeUsed[3]) {
                attributeArr[i - 1] = Attribute.TAGS;
                isAttributeUsed[3] = true;
            } else {
                throw new ParseException(
                        String.format(MESSAGE_INVALID_COMMAND_FORMAT, CustomiseCommand.MESSAGE_USAGE));
            }
        }
        return fillMissingAttributes(attributeArr, isAttributeUsed, noOfMissing);
    }

    /**
     * Fills in the unspecified attributes in default order at the back.
     *
     * @param attributeArr the input Attribute array
     * @param isAttributeUsed the boolean array that index 0 represents ADDRESS, index 1 represents EMAIL,
     *                        index 2 represents PHONE and index 3 represents TAGS
     * @param noOfMissing the number of attributes that have to be filled in
     * @return the Attribute array order
     */
    private Attribute[] fillMissingAttributes(Attribute[] attributeArr, boolean[] isAttributeUsed, int noOfMissing) {
        int indexOfLastAttribute = attributeArr.length - 1;
        for (int i = 0; i < noOfMissing; i++) {
            if (!isAttributeUsed[0]) {
                attributeArr[indexOfLastAttribute - i] = Attribute.ADDRESS;
                isAttributeUsed[0] = true;
            } else if (!isAttributeUsed[1]) {
                attributeArr[indexOfLastAttribute - i] = Attribute.EMAIL;
                isAttributeUsed[1] = true;
            } else if (!isAttributeUsed[2]) {
                attributeArr[indexOfLastAttribute - i] = Attribute.PHONE;
                isAttributeUsed[2] = true;
            } else if (!isAttributeUsed[3]) {
                attributeArr[indexOfLastAttribute - i] = Attribute.TAGS;
                isAttributeUsed[3] = true;
            } else {
                assert false;
            }
        }
        return attributeArr;
    }

    /**
     * Converts the input into an array of Attribute objects.
     *
     * @param args an array of argument inputs
     * @return an array of Attribute
     * @throws ParseException if the user input does not conform the expected format
     */
    private Attribute[] toAttributes(String[] args) throws ParseException {
        boolean[] isAttributeUsed = new boolean[4]; //0: ADDRESS, 1: EMAIL, 2: PHONE, 3: TAGS
        ArrayList<Attribute> attributeArrayList = new ArrayList<>();

        for (int i = 1; i < args.length; i++) {
            if (args[i].equals(PREFIX_ADDRESS.toString()) && !isAttributeUsed[0]) {
                attributeArrayList.add(Attribute.ADDRESS);
                isAttributeUsed[0] = true;
            } else if (args[i].equals(PREFIX_EMAIL.toString()) && !isAttributeUsed[1]) {
                attributeArrayList.add(Attribute.EMAIL);
                isAttributeUsed[1] = true;
            } else if (args[i].equals(PREFIX_PHONE.toString()) && !isAttributeUsed[2]) {
                attributeArrayList.add(Attribute.PHONE);
                isAttributeUsed[2] = true;
            } else if (args[i].equals(PREFIX_TAG.toString()) && !isAttributeUsed[3]) {
                attributeArrayList.add(Attribute.TAGS);
                isAttributeUsed[3] = true;
            } else {
                throw new ParseException(
                        String.format(MESSAGE_INVALID_COMMAND_FORMAT, CustomiseCommand.MESSAGE_USAGE));
            }
        }
        Attribute[] attributeArr = new Attribute[attributeArrayList.size()];
        return attributeArrayList.toArray(attributeArr);
    }
}
