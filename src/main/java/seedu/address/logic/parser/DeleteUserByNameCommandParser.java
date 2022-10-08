package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import seedu.address.logic.parser.exceptions.ParseException;

import seedu.address.logic.commands.DeleteUserByNameCommand;
import seedu.address.model.person.FullNamePredicate;

public class DeleteUserByNameCommandParser implements Parser<DeleteUserByNameCommand> {

    public DeleteUserByNameCommand parse(String args) throws ParseException {
        ArgumentMultimap argumentMultimap = ArgumentTokenizer.tokenize(args);
            if (argumentMultimap.getPreamble().isEmpty()) {
                throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteUserByNameCommand.MESSAGE_USAGE));
            }

            String name = argumentMultimap.getPreamble();
            return new DeleteUserByNameCommand(new FullNamePredicate(name));



    }
}
