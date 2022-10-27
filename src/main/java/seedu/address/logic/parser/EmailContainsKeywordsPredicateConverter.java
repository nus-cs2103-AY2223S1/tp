package seedu.address.logic.parser;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import picocli.CommandLine;
import seedu.address.model.person.EmailContainsKeywordsPredicate;

public class EmailContainsKeywordsPredicateConverter implements CommandLine.IParameterConsumer {
    @Override
    public void consumeParameters(Stack<String> args, CommandLine.Model.ArgSpec argSpec,
                                  CommandLine.Model.CommandSpec commandSpec) {
        List<String> keywords = new ArrayList<>();
        while (!args.isEmpty()) {
            String keyword = args.pop();
            keywords.add(keyword);
        }

        argSpec.setValue(new EmailContainsKeywordsPredicate(keywords));
    }
}
