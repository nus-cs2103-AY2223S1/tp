package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.FLAG_ADDRESS_STR;
import static seedu.address.logic.parser.CliSyntax.FLAG_ADDRESS_STR_LONG;
import static seedu.address.logic.parser.CliSyntax.FLAG_EMAIL_STR;
import static seedu.address.logic.parser.CliSyntax.FLAG_EMAIL_STR_LONG;
import static seedu.address.logic.parser.CliSyntax.FLAG_NAME_STR;
import static seedu.address.logic.parser.CliSyntax.FLAG_NAME_STR_LONG;
import static seedu.address.logic.parser.CliSyntax.FLAG_PHONE_STR;
import static seedu.address.logic.parser.CliSyntax.FLAG_PHONE_STR_LONG;
import static seedu.address.logic.parser.CliSyntax.FLAG_TAG_STR;
import static seedu.address.logic.parser.CliSyntax.FLAG_TAG_STR_LONG;

import java.util.Arrays;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Options;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.AddCommand;
import seedu.address.logic.commands.EditCommand;
import seedu.address.logic.commands.EditCommand.EditPersonDescriptor;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new EditCommand object
 */
public class EditCommandParser implements Parser<EditCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the EditCommand
     * and returns an EditCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public EditCommand parse(String args) throws ParseException {
        Options options = new Options();

        options.addOption(FLAG_NAME_STR, FLAG_NAME_STR_LONG, true, "Name of person");
        options.addOption(FLAG_PHONE_STR, FLAG_PHONE_STR_LONG, true, "Phone of person");
        options.addOption(FLAG_EMAIL_STR, FLAG_EMAIL_STR_LONG, true, "Email of person");
        options.addOption(FLAG_ADDRESS_STR, FLAG_ADDRESS_STR_LONG, true, "Address of person");
        options.addOption(FLAG_TAG_STR, FLAG_TAG_STR_LONG, true, "Tag of person");

        try {
            CommandLineParser parser = new DefaultParser();
            String[] argsArray = ArgumentTokenizer.tokenize(args);
            CommandLine cmd = parser.parse(options, argsArray);
            EditPersonDescriptor editPersonDescriptor = new EditPersonDescriptor();

            Index index;

            try {
                index = ParserUtil.parseIndex(cmd.getArgs()[0]);
            } catch (ParseException | IndexOutOfBoundsException e) {
                throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditCommand.MESSAGE_USAGE), e);
            }

            if (cmd.hasOption(FLAG_NAME_STR)) {
                editPersonDescriptor.setName(ParserUtil.parseName(cmd.getOptionValue(FLAG_NAME_STR)));
            }

            if (cmd.hasOption(FLAG_PHONE_STR)) {
                editPersonDescriptor.setPhone(ParserUtil.parsePhone(cmd.getOptionValue(FLAG_PHONE_STR)));
            }

            if (cmd.hasOption(FLAG_EMAIL_STR)) {
                editPersonDescriptor.setEmail(ParserUtil.parseEmail(cmd.getOptionValue(FLAG_EMAIL_STR)));
            }

            if (cmd.hasOption(FLAG_ADDRESS_STR)) {
                editPersonDescriptor.setAddress(ParserUtil.parseAddress(cmd.getOptionValue(FLAG_ADDRESS_STR)));
            }
            if (cmd.hasOption(FLAG_TAG_STR)) {
                editPersonDescriptor.setTags(ParserUtil.parseTags(Arrays.asList(cmd.getOptionValues(FLAG_TAG_STR))));
            }

            if (!editPersonDescriptor.isAnyFieldEdited()) {
                throw new ParseException(EditCommand.MESSAGE_NOT_EDITED);
            }

            return new EditCommand(index, editPersonDescriptor);
        } catch (org.apache.commons.cli.ParseException e) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE));
        }
    }
}
