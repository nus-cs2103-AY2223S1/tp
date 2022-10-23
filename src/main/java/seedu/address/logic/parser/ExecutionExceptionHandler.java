package seedu.address.logic.parser;

import picocli.CommandLine;

/**
 * Used to throw the error during command execution as {@code CommandLine#execute} does not throw an Exception
 */
public class ExecutionExceptionHandler implements CommandLine.IExecutionExceptionHandler {
    @Override
    public int handleExecutionException(Exception ex, CommandLine commandLine, CommandLine.ParseResult parseResult)
            throws Exception {
        System.out.println("potato");
        throw ex;
    }
}
