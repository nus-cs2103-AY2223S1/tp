package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.FLAG_INDEX_STR;

import java.util.ArrayList;
import java.util.List;

import picocli.CommandLine;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.EditCommand;
import seedu.address.logic.commands.EditCommand.EditPersonDescriptor;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new EditCommand object
 */
public class EditCommandParser implements Parser<EditCommand> {
    /**
     * Creates an EditCommandParser with default arguments
     */
    public EditCommandParser() {

    }

    /**
     * Parses the given {@code String} of arguments in the context of the EditCommand
     * and returns an EditCommand object for execution.
     *
     * @throws ParseException if the user input does not conform to the expected
     *                        format
     */
    public EditCommand parse(String args) throws ParseException {
        try {
            List<String> argsList = new ArrayList<>(List.of(ArgumentTokenizer.tokenize(args)));
            if (argsList.size() == 0) {
                throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditCommand.MESSAGE_USAGE));
            }

            if (!argsList.get(0).equals(FLAG_INDEX_STR)) {
                argsList.add(0, FLAG_INDEX_STR);
            }
//            parser.parse(argsList.toArray(String[]::new));
            EditPersonDescriptor editPersonDescriptor = new EditPersonDescriptor();

            Index index = Index.fromOneBased(0);
//            try {
//                index = ParserUtil.parseIndex(String.valueOf(arguments.index));
//            } catch (ParseException | IndexOutOfBoundsException e) {
//                throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditCommand.MESSAGE_USAGE), e);
//            }
//
//            if (arguments.name != null) {
//                editPersonDescriptor.setName(ParserUtil.parseName(arguments.name));
//            }
//
//            if (arguments.phone != null) {
//                editPersonDescriptor.setPhone(ParserUtil.parsePhone(arguments.phone));
//            }
//
//            if (arguments.email != null) {
//                editPersonDescriptor.setEmail(ParserUtil.parseEmail(arguments.email));
//            }
//
//            if (arguments.address != null) {
//                editPersonDescriptor.setAddress(ParserUtil.parseAddress(arguments.address));
//            }
//
//            if (arguments.tags != null) {
//                editPersonDescriptor.setTags(ParserUtil.parseTags(arguments.tags));
//            }

            if (!editPersonDescriptor.isAnyFieldEdited()) {
                throw new ParseException(EditCommand.MESSAGE_NOT_EDITED);
            }
            return new EditCommand(index, editPersonDescriptor);
        } catch (CommandLine.ParameterException e) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditCommand.MESSAGE_USAGE), e);
        }
    }

    /**
     * Command arguments for the EditCommand
     */
    private static class EditCommandArguments {
//        @Parameter(names = {FLAG_INDEX_STR, FLAG_INDEX_STR_LONG}, required = true, description = "Index of person")
//        private Integer index;
//
//        @Parameter(names = {FLAG_NAME_STR, FLAG_NAME_STR_LONG}, description = "Name of person")
//        private String name;
//
//        @Parameter(names = {FLAG_PHONE_STR, FLAG_PHONE_STR_LONG}, description = "Phone of person")
//        private String phone;
//
//        @Parameter(names = {FLAG_EMAIL_STR, FLAG_EMAIL_STR_LONG}, description = "Email of person")
//        private String email;
//
//        @Parameter(names = {FLAG_ADDRESS_STR, FLAG_ADDRESS_STR_LONG}, description = "Address of person")
//        private String address;
//
//        @Parameter(names = {FLAG_TAG_STR, FLAG_TAG_STR_LONG}, description = "Tags of person")
//        private List<String> tags;
    }
}
