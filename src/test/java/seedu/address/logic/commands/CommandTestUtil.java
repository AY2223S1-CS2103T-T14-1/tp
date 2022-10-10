package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.parser.CliSyntax.PREFIX_APPLIED_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_COMPANY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LINK;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.testutil.Assert.assertThrows;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.internship.Internship;
import seedu.address.model.internship.NameContainsKeywordsPredicate;
import seedu.address.testutil.EditInternshipDescriptorBuilder;

/**
 * Contains helper methods for testing commands.
 */
public class CommandTestUtil {

    public static final String VALID_COMPANY_GOOGLE = "Google";
    public static final String VALID_COMPANY_TIKTOK = "Tiktok";
    public static final String VALID_LINK_GOOGLE = "https://careers.google.com/students";
    public static final String VALID_LINK_TIKTOK = "https://careers.tiktok.com/campus";
    public static final String VALID_EMAIL_GOOGLE = "careers@google.com";
    public static final String VALID_EMAIL_TIKTOK = "careers@tiktok.com";
    public static final String VALID_APPLICATION_STATUS_GOOGLE = "applied";
    public static final String VALID_APPLICATION_STATUS_TIKTOK = "applied";
    public static final String VALID_APPLIED_DATE_GOOGLE = "2022-10-30";
    public static final String VALID_APPLIED_DATE_TIKTOK = "2022-11-30";
    public static final String VALID_TAG_HUSBAND = "husband";
    public static final String VALID_TAG_FRIEND = "friend";

    public static final String COMPANY_DESC_GOOGLE = " " + PREFIX_COMPANY + VALID_COMPANY_GOOGLE;
    public static final String COMPANY_DESC_TIKTOK = " " + PREFIX_COMPANY + VALID_COMPANY_TIKTOK;
    public static final String LINK_DESC_GOOGLE = " " + PREFIX_LINK + VALID_LINK_GOOGLE;
    public static final String LINK_DESC_TIKTOK = " " + PREFIX_LINK + VALID_LINK_TIKTOK;
    public static final String EMAIL_DESC_GOOGLE = " " + PREFIX_EMAIL + VALID_EMAIL_GOOGLE;
    public static final String EMAIL_DESC_TIKTOK = " " + PREFIX_EMAIL + VALID_EMAIL_TIKTOK;
    public static final String APPLIED_DATE_DESC_GOOGLE = " " + PREFIX_APPLIED_DATE + VALID_APPLIED_DATE_GOOGLE;
    public static final String APPLIED_DATE_DESC_TIKTOK = " " + PREFIX_APPLIED_DATE + VALID_APPLIED_DATE_TIKTOK;
    public static final String TAG_DESC_FRIEND = " " + PREFIX_TAG + VALID_TAG_FRIEND;
    public static final String TAG_DESC_HUSBAND = " " + PREFIX_TAG + VALID_TAG_HUSBAND;

    public static final String INVALID_COMPANY_DESC = " " + PREFIX_COMPANY + " "; // whitespace string not allowed for company
    public static final String INVALID_LINK_DESC = " " + PREFIX_LINK + "91 1a"; // ' ' not allowed in links
    public static final String INVALID_EMAIL_DESC = " " + PREFIX_EMAIL + "bob!yahoo"; // missing '@' symbol
    public static final String INVALID_APPLIED_DATE_DESC = " " + PREFIX_APPLIED_DATE; // empty string not allowed for appliedDate
    public static final String INVALID_TAG_DESC = " " + PREFIX_TAG + "hubby*"; // '*' not allowed in tags

    public static final String PREAMBLE_WHITESPACE = "\t  \r  \n";
    public static final String PREAMBLE_NON_EMPTY = "NonEmptyPreamble";

    public static final EditCommand.EditInternshipDescriptor DESC_GOOGLE;
    public static final EditCommand.EditInternshipDescriptor DESC_TIKTOK;

    static {
        DESC_GOOGLE = new EditInternshipDescriptorBuilder().withName(VALID_COMPANY_GOOGLE)
                .withPhone(VALID_LINK_GOOGLE).withEmail(VALID_EMAIL_GOOGLE).withAddress(VALID_APPLIED_DATE_GOOGLE)
                .withTags(VALID_TAG_FRIEND).build();
        DESC_TIKTOK = new EditInternshipDescriptorBuilder().withName(VALID_COMPANY_TIKTOK)
                .withPhone(VALID_LINK_TIKTOK).withEmail(VALID_EMAIL_TIKTOK).withAddress(VALID_APPLIED_DATE_TIKTOK)
                .withTags(VALID_TAG_HUSBAND, VALID_TAG_FRIEND).build();
    }

    /**
     * Executes the given {@code command}, confirms that <br>
     * - the returned {@link CommandResult} matches {@code expectedCommandResult} <br>
     * - the {@code actualModel} matches {@code expectedModel}
     */
    public static void assertCommandSuccess(Command command, Model actualModel, CommandResult expectedCommandResult,
            Model expectedModel) {
        try {
            CommandResult result = command.execute(actualModel);
            assertEquals(expectedCommandResult, result);
            assertEquals(expectedModel, actualModel);
        } catch (CommandException ce) {
            throw new AssertionError("Execution of command should not fail.", ce);
        }
    }

    /**
     * Convenience wrapper to {@link #assertCommandSuccess(Command, Model, CommandResult, Model)}
     * that takes a string {@code expectedMessage}.
     */
    public static void assertCommandSuccess(Command command, Model actualModel, String expectedMessage,
            Model expectedModel) {
        CommandResult expectedCommandResult = new CommandResult(expectedMessage);
        assertCommandSuccess(command, actualModel, expectedCommandResult, expectedModel);
    }

    /**
     * Executes the given {@code command}, confirms that <br>
     * - a {@code CommandException} is thrown <br>
     * - the CommandException message matches {@code expectedMessage} <br>
     * - the address book, filtered internship list and selected internship in {@code actualModel} remain unchanged
     */
    public static void assertCommandFailure(Command command, Model actualModel, String expectedMessage) {
        // we are unable to defensively copy the model for comparison later, so we can
        // only do so by copying its components.
        AddressBook expectedAddressBook = new AddressBook(actualModel.getAddressBook());
        List<Internship> expectedFilteredList = new ArrayList<>(actualModel.getFilteredInternshipList());

        assertThrows(CommandException.class, expectedMessage, () -> command.execute(actualModel));
        assertEquals(expectedAddressBook, actualModel.getAddressBook());
        assertEquals(expectedFilteredList, actualModel.getFilteredInternshipList());
    }
    /**
     * Updates {@code model}'s filtered list to show only the internship at the given {@code targetIndex} in the
     * {@code model}'s address book.
     */
    public static void showInternshipAtIndex(Model model, Index targetIndex) {
        assertTrue(targetIndex.getZeroBased() < model.getFilteredInternshipList().size());

        Internship internship = model.getFilteredInternshipList().get(targetIndex.getZeroBased());
        final String[] splitName = internship.getCompany().company.split("\\s+");
        model.updateFilteredInternshipList(new NameContainsKeywordsPredicate(Arrays.asList(splitName[0])));

        assertEquals(1, model.getFilteredInternshipList().size());
    }

}
