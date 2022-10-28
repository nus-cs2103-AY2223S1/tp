package seedu.address.logic.commands;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.AddressBookParser;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.Model;

public class CustomCommandBuilder {
    private final String repr;
    private final String commandData;

    public CustomCommandBuilder(String repr, String commandData) {
        this.repr = repr;
        this.commandData = commandData;
    }

    public String getRepr() {
        return repr;
    }

    public Command build() {
        return new Command() {
            private Object o = null;

            @Override
            public void setInput(Object additionalData) throws CommandException {
                o = additionalData;
            }

            @Override
            public CommandResult execute(Model model) throws CommandException {
                try {
                    return AddressBookParser.get().parseCommand(commandData).execute(model);
                } catch (ParseException e) {
                    throw new CommandException(e.getMessage());
                }
            }
        };
    }

    @Override
    public int hashCode() {
        return repr.hashCode() ^ commandData.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return (obj instanceof CustomCommandBuilder)
                && ((CustomCommandBuilder) obj).repr.equals(repr)
                && ((CustomCommandBuilder) obj).commandData.equals(commandData);
    }
}
