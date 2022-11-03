package seedu.taassist.logic.parser;

import static seedu.taassist.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.taassist.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.taassist.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.taassist.logic.parser.CliSyntax.PREFIX_MODULE_CLASS;
import static seedu.taassist.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.taassist.logic.parser.CliSyntax.PREFIX_PHONE;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.taassist.logic.commands.AddCommand;
import seedu.taassist.logic.parser.exceptions.ParseException;
import seedu.taassist.model.moduleclass.ModuleClass;
import seedu.taassist.model.student.Address;
import seedu.taassist.model.student.Email;
import seedu.taassist.model.student.Name;
import seedu.taassist.model.student.Phone;
import seedu.taassist.model.student.Student;
import seedu.taassist.model.student.StudentModuleData;

/**
 * Parses input arguments and creates a new AddCommand object.
 */
public class AddCommandParser implements Parser<AddCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the AddCommand
     * and returns an AddCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_PHONE, PREFIX_EMAIL,
                PREFIX_ADDRESS, PREFIX_MODULE_CLASS);

        if (!argMultimap.containsPrefixes(PREFIX_NAME) || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.COMMAND_WORD,
                    AddCommand.MESSAGE_USAGE));
        }

        Name name = ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get());
        Phone phone = ParserUtil.parsePhone(argMultimap.getValue(PREFIX_PHONE).orElse(""));
        Email email = ParserUtil.parseEmail(argMultimap.getValue(PREFIX_EMAIL).orElse(""));
        Address address = ParserUtil.parseAddress(argMultimap.getValue(PREFIX_ADDRESS).orElse(""));
        Set<ModuleClass> moduleClassList = ParserUtil.parseModuleClasses(argMultimap.getAllValues(PREFIX_MODULE_CLASS));
        List<StudentModuleData> moduleData =
                moduleClassList.stream().map(StudentModuleData::new).collect(Collectors.toList());

        Student student = new Student(name, phone, email, address, moduleData);

        return new AddCommand(student);
    }
}
