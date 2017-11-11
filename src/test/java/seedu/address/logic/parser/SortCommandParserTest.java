package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_GROUP;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.Test;

import seedu.address.logic.commands.SortCommand;

//@@author sofarsophie
public class SortCommandParserTest {

    private SortCommandParser parser = new SortCommandParser();

    @Test
    public void parse_noArgs_returnSortCommand() {
        assertParseSuccess(parser, "", new SortCommand());
    }

    @Test
    public void parse_namePrefixArg_returnSortCommand() {
        assertParseSuccess(parser, PREFIX_NAME.toString(), new SortCommand(PREFIX_NAME));
    }

    @Test
    public void parse_validArg_returnSortCommand() {
        assertParseSuccess(parser, PREFIX_ADDRESS.toString(), new SortCommand(PREFIX_ADDRESS));
    }

    @Test
    public void parse_invalidArg_throwsParseException() {
        assertParseFailure(parser, "a",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, SortCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_multipleArgs_throwsParseException() {
        assertParseFailure(parser, PREFIX_EMAIL.toString() + PREFIX_GROUP.toString(),
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, SortCommand.MESSAGE_USAGE));
    }

}
//@@author
