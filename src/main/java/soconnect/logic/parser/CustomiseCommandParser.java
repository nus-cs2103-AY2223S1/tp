package soconnect.logic.parser;

import static soconnect.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static soconnect.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static soconnect.logic.parser.CliSyntax.PREFIX_EMAIL;
import static soconnect.logic.parser.CliSyntax.PREFIX_PHONE;
import static soconnect.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.ArrayList;

import soconnect.logic.commands.CustomiseCommand;
import soconnect.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new CustomiseCommand object.
 */
public class CustomiseCommandParser implements Parser<CustomiseCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the CustomiseCommand
     * and returns a CustomiseCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format.
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
            return new CustomiseCommand(CustomiseCommand.CustomiseSubCommand.ORDER, toAttributeOrder(arguments));
        } else if (arguments[0].equals("hide")) {
            return new CustomiseCommand(CustomiseCommand.CustomiseSubCommand.HIDE, toAttributes(arguments));
        } else if (arguments[0].equals("show")) {
            return new CustomiseCommand(CustomiseCommand.CustomiseSubCommand.SHOW, toAttributes(arguments));
        } else {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, CustomiseCommand.MESSAGE_USAGE));
        }
    }

    /**
     * Converts the input into an array of Attribute objects in the required order.
     *
     * @param args an array of argument inputs.
     * @return an array of Attribute.
     * @throws ParseException if the user input does not conform the expected format.
     */
    private CustomiseCommand.Attribute[] toAttributeOrder(String[] args) throws ParseException {
        boolean[] isAttributeUsed = new boolean[4]; //0: ADDRESS, 1: EMAIL, 2: PHONE, 3: TAGS
        int noOfMissing = 4 - (args.length - 1); //first element of args is the subCommand
        CustomiseCommand.Attribute[] attributeArr = new CustomiseCommand.Attribute[4];
        int indexOfAttributeArr = 0;

        //the element in index 0 of args is not used here as it is the subCommand
        for (int i = 1; i < args.length; i++) {
            if (args[i].equals(PREFIX_ADDRESS.toString()) && !isAttributeUsed[0]) {
                attributeArr[indexOfAttributeArr] = CustomiseCommand.Attribute.ADDRESS;
                isAttributeUsed[0] = true; //to prevent duplicate ADDRESS in array
            } else if (args[i].equals(PREFIX_EMAIL.toString()) && !isAttributeUsed[1]) {
                attributeArr[indexOfAttributeArr] = CustomiseCommand.Attribute.EMAIL;
                isAttributeUsed[1] = true; //to prevent duplicate EMAIL in array
            } else if (args[i].equals(PREFIX_PHONE.toString()) && !isAttributeUsed[2]) {
                attributeArr[indexOfAttributeArr] = CustomiseCommand.Attribute.PHONE;
                isAttributeUsed[2] = true; //to prevent duplicate PHONE in array
            } else if (args[i].equals(PREFIX_TAG.toString()) && !isAttributeUsed[3]) {
                attributeArr[indexOfAttributeArr] = CustomiseCommand.Attribute.TAGS;
                isAttributeUsed[3] = true; //to prevent duplicate TAGS in array
            } else {
                //if there are invalid arguments or duplicate arguments
                throw new ParseException(
                        String.format(MESSAGE_INVALID_COMMAND_FORMAT, CustomiseCommand.MESSAGE_USAGE));
            }
            indexOfAttributeArr++;
        }
        return fillMissingAttributes(attributeArr, isAttributeUsed, noOfMissing);
    }

    /**
     * Fills in the unspecified attributes in default order at the back.
     *
     * @param attributeArr the input Attribute array.
     * @param isAttributeUsed the boolean array that index 0 represents ADDRESS, index 1 represents EMAIL,
     *                        index 2 represents PHONE and index 3 represents TAGS.
     * @param noOfMissing the number of attributes that have to be filled in.
     * @return the Attribute array order.
     */
    private CustomiseCommand.Attribute[] fillMissingAttributes(CustomiseCommand.Attribute[] attributeArr,
                                                               boolean[] isAttributeUsed, int noOfMissing) {
        int indexOfLastAttribute = attributeArr.length - 1;
        for (int i = 0; i < noOfMissing; i++) {
            if (!isAttributeUsed[0]) {
                attributeArr[indexOfLastAttribute - i] = CustomiseCommand.Attribute.ADDRESS;
                isAttributeUsed[0] = true;
            } else if (!isAttributeUsed[1]) {
                attributeArr[indexOfLastAttribute - i] = CustomiseCommand.Attribute.EMAIL;
                isAttributeUsed[1] = true;
            } else if (!isAttributeUsed[2]) {
                attributeArr[indexOfLastAttribute - i] = CustomiseCommand.Attribute.PHONE;
                isAttributeUsed[2] = true;
            } else if (!isAttributeUsed[3]) {
                attributeArr[indexOfLastAttribute - i] = CustomiseCommand.Attribute.TAGS;
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
     * @param args An array of argument inputs.
     * @return An array of Attribute.
     * @throws ParseException If the user input does not conform the expected format.
     */
    private CustomiseCommand.Attribute[] toAttributes(String[] args) throws ParseException {
        boolean[] isAttributeUsed = new boolean[4]; //0: ADDRESS, 1: EMAIL, 2: PHONE, 3: TAGS
        ArrayList<CustomiseCommand.Attribute> attributeArrayList = new ArrayList<>();

        //the element in index 0 of args is not used here as it is the subCommand
        for (int i = 1; i < args.length; i++) {
            if (args[i].equals(PREFIX_ADDRESS.toString()) && !isAttributeUsed[0]) {
                attributeArrayList.add(CustomiseCommand.Attribute.ADDRESS);
                isAttributeUsed[0] = true; //to prevent duplicate ADDRESS in arraylist
            } else if (args[i].equals(PREFIX_EMAIL.toString()) && !isAttributeUsed[1]) {
                attributeArrayList.add(CustomiseCommand.Attribute.EMAIL);
                isAttributeUsed[1] = true; //to prevent duplicate EMAIL in arraylist
            } else if (args[i].equals(PREFIX_PHONE.toString()) && !isAttributeUsed[2]) {
                attributeArrayList.add(CustomiseCommand.Attribute.PHONE);
                isAttributeUsed[2] = true; //to prevent duplicate PHONE in arraylist
            } else if (args[i].equals(PREFIX_TAG.toString()) && !isAttributeUsed[3]) {
                attributeArrayList.add(CustomiseCommand.Attribute.TAGS);
                isAttributeUsed[3] = true; //to prevent duplicate TAGS in arraylist
            } else {
                throw new ParseException(
                        String.format(MESSAGE_INVALID_COMMAND_FORMAT, CustomiseCommand.MESSAGE_USAGE));
            }
        }
        CustomiseCommand.Attribute[] attributeArr = new CustomiseCommand.Attribute[attributeArrayList.size()];
        return attributeArrayList.toArray(attributeArr);
    }
}
