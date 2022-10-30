package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showInternshipAtIndex;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_INTERNSHIP;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_INTERNSHIP;
import static seedu.address.testutil.TypicalInternships.getTypicalFindMyIntern;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.model.FindMyIntern;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.internship.ApplicationStatus;
import seedu.address.model.internship.Internship;
import seedu.address.testutil.InternshipBuilder;

public class MarkCommandTest {

    private Model model = new ModelManager(getTypicalFindMyIntern(), new UserPrefs());

    @Test
    public void execute_validIndexUnfilteredList_success() {
        Index indexLastInternship = Index.fromOneBased(model.getFilteredInternshipList().size());
        Internship lastInternship = model.getFilteredInternshipList().get(indexLastInternship.getZeroBased());

        InternshipBuilder internshipInList = new InternshipBuilder(lastInternship);
        Internship editedInternship = internshipInList.withApplicationStatus(ApplicationStatus.Rejected).build();

        MarkCommand markCommand = new MarkCommand(indexLastInternship, ApplicationStatus.Rejected);

        String expectedMessage = String.format(MarkCommand.MESSAGE_MARK_INTERNSHIP_SUCCESS, editedInternship);

        Model expectedModel = new ModelManager(new FindMyIntern(model.getFindMyIntern()), new UserPrefs());
        expectedModel.markInternship(lastInternship, editedInternship);

        assertCommandSuccess(markCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidInternshipIndexUnfilteredList_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredInternshipList().size() + 1);

        MarkCommand markCommand = new MarkCommand(outOfBoundIndex, ApplicationStatus.Applied);

        assertCommandFailure(markCommand, model, Messages.MESSAGE_INVALID_INTERNSHIP_DISPLAYED_INDEX);
    }

    @Test
    public void execute_filteredList_success() {
        showInternshipAtIndex(model, INDEX_FIRST_INTERNSHIP);

        Internship internshipInFilteredList =
                model.getFilteredInternshipList().get(INDEX_FIRST_INTERNSHIP.getZeroBased());
        Internship editedInternship =
                new InternshipBuilder(internshipInFilteredList).withApplicationStatus(ApplicationStatus.Rejected)
                        .build();

        MarkCommand markCommand = new MarkCommand(INDEX_FIRST_INTERNSHIP, ApplicationStatus.Rejected);

        String expectedMessage = String.format(MarkCommand.MESSAGE_MARK_INTERNSHIP_SUCCESS, editedInternship);

        Model expectedModel = new ModelManager(new FindMyIntern(model.getFindMyIntern()), new UserPrefs());
        expectedModel.markInternship(model.getFilteredInternshipList().get(0), editedInternship);
        expectedModel.updateFilteredInternshipList(model.getCurrentPredicate());

        assertCommandSuccess(markCommand, model, expectedMessage, expectedModel);
    }


    @Test
    public void execute_duplicateInternshipFilteredList_failure() {
        showInternshipAtIndex(model, INDEX_FIRST_INTERNSHIP);

        // mark the internship with the current applicationStatus
        Internship internshipInList =
                model.getFindMyIntern().getInternshipList().get(INDEX_FIRST_INTERNSHIP.getZeroBased());
        MarkCommand markCommand = new MarkCommand(INDEX_FIRST_INTERNSHIP,
                internshipInList.getApplicationStatus());

        assertCommandFailure(markCommand, model, MarkCommand.MESSAGE_SAME_APPLICATION_STATUS);
    }

    @Test
    public void equals() {
        MarkCommand markFirstCommand = new MarkCommand(INDEX_FIRST_INTERNSHIP, ApplicationStatus.Applied);
        MarkCommand markSecondCommand = new MarkCommand(INDEX_SECOND_INTERNSHIP, ApplicationStatus.Applied);

        // same object -> returns true
        assertTrue(markFirstCommand.equals(markFirstCommand));

        // same values -> returns true
        MarkCommand markFirstCommandCopy = new MarkCommand(INDEX_FIRST_INTERNSHIP, ApplicationStatus.Applied);
        assertTrue(markFirstCommand.equals(markFirstCommandCopy));

        // different types -> returns false
        assertFalse(markFirstCommand.equals(1));

        // null -> returns false
        assertFalse(markFirstCommand.equals(null));

        // different internship -> returns false
        assertFalse(markFirstCommand.equals(markSecondCommand));
    }

}
