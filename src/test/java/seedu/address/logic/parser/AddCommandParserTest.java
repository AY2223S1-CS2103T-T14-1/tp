package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.ADDRESS_DESC_GOOGLE;
import static seedu.address.logic.commands.CommandTestUtil.ADDRESS_DESC_TIKTOK;
import static seedu.address.logic.commands.CommandTestUtil.EMAIL_DESC_GOOGLE;
import static seedu.address.logic.commands.CommandTestUtil.EMAIL_DESC_TIKTOK;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_ADDRESS_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_EMAIL_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_NAME_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_PHONE_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_TAG_DESC;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_GOOGLE;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_TIKTOK;
import static seedu.address.logic.commands.CommandTestUtil.PHONE_DESC_GOOGLE;
import static seedu.address.logic.commands.CommandTestUtil.PHONE_DESC_TIKTOK;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_NON_EMPTY;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.address.logic.commands.CommandTestUtil.TAG_DESC_FRIEND;
import static seedu.address.logic.commands.CommandTestUtil.TAG_DESC_HUSBAND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_TIKTOK;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_TIKTOK;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_TIKTOK;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_TIKTOK;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_FRIEND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalInternships.GOOGLE;
import static seedu.address.testutil.TypicalInternships.TIKTOK;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.AddCommand;
import seedu.address.model.internship.Address;
import seedu.address.model.internship.Email;
import seedu.address.model.internship.Internship;
import seedu.address.model.internship.Company;
import seedu.address.model.internship.Phone;
import seedu.address.model.tag.Tag;
import seedu.address.testutil.InternshipBuilder;

public class AddCommandParserTest {
    private AddCommandParser parser = new AddCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        Internship expectedInternship = new InternshipBuilder(TIKTOK).withTags(VALID_TAG_FRIEND).build();

        // whitespace only preamble
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + NAME_DESC_TIKTOK + PHONE_DESC_TIKTOK + EMAIL_DESC_TIKTOK
                + ADDRESS_DESC_TIKTOK + TAG_DESC_FRIEND, new AddCommand(expectedInternship));

        // multiple names - last name accepted
        assertParseSuccess(parser, NAME_DESC_GOOGLE + NAME_DESC_TIKTOK + PHONE_DESC_TIKTOK + EMAIL_DESC_TIKTOK
                + ADDRESS_DESC_TIKTOK + TAG_DESC_FRIEND, new AddCommand(expectedInternship));

        // multiple phones - last phone accepted
        assertParseSuccess(parser, NAME_DESC_TIKTOK + PHONE_DESC_GOOGLE + PHONE_DESC_TIKTOK + EMAIL_DESC_TIKTOK
                + ADDRESS_DESC_TIKTOK + TAG_DESC_FRIEND, new AddCommand(expectedInternship));

        // multiple emails - last email accepted
        assertParseSuccess(parser, NAME_DESC_TIKTOK + PHONE_DESC_TIKTOK + EMAIL_DESC_GOOGLE + EMAIL_DESC_TIKTOK
                + ADDRESS_DESC_TIKTOK + TAG_DESC_FRIEND, new AddCommand(expectedInternship));

        // multiple addresses - last address accepted
        assertParseSuccess(parser, NAME_DESC_TIKTOK + PHONE_DESC_TIKTOK + EMAIL_DESC_TIKTOK + ADDRESS_DESC_GOOGLE
                + ADDRESS_DESC_TIKTOK + TAG_DESC_FRIEND, new AddCommand(expectedInternship));

        // multiple tags - all accepted
        Internship expectedInternshipMultipleTags =
                new InternshipBuilder(TIKTOK).withTags(VALID_TAG_FRIEND, VALID_TAG_HUSBAND).build();
        assertParseSuccess(parser, NAME_DESC_TIKTOK + PHONE_DESC_TIKTOK + EMAIL_DESC_TIKTOK + ADDRESS_DESC_TIKTOK
                + TAG_DESC_HUSBAND + TAG_DESC_FRIEND, new AddCommand(expectedInternshipMultipleTags));
    }

    @Test
    public void parse_optionalFieldsMissing_success() {
        // zero tags
        Internship expectedInternship = new InternshipBuilder(GOOGLE).withTags().build();
        assertParseSuccess(parser, NAME_DESC_GOOGLE + PHONE_DESC_GOOGLE + EMAIL_DESC_GOOGLE + ADDRESS_DESC_GOOGLE,
                new AddCommand(expectedInternship));
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE);

        // missing name prefix
        assertParseFailure(parser, VALID_NAME_TIKTOK + PHONE_DESC_TIKTOK + EMAIL_DESC_TIKTOK + ADDRESS_DESC_TIKTOK,
                expectedMessage);

        // missing phone prefix
        assertParseFailure(parser, NAME_DESC_TIKTOK + VALID_PHONE_TIKTOK + EMAIL_DESC_TIKTOK + ADDRESS_DESC_TIKTOK,
                expectedMessage);

        // missing email prefix
        assertParseFailure(parser, NAME_DESC_TIKTOK + PHONE_DESC_TIKTOK + VALID_EMAIL_TIKTOK + ADDRESS_DESC_TIKTOK,
                expectedMessage);

        // missing address prefix
        assertParseFailure(parser, NAME_DESC_TIKTOK + PHONE_DESC_TIKTOK + EMAIL_DESC_TIKTOK + VALID_ADDRESS_TIKTOK,
                expectedMessage);

        // all prefixes missing
        assertParseFailure(parser, VALID_NAME_TIKTOK + VALID_PHONE_TIKTOK + VALID_EMAIL_TIKTOK + VALID_ADDRESS_TIKTOK,
                expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid name
        assertParseFailure(parser, INVALID_NAME_DESC + PHONE_DESC_TIKTOK + EMAIL_DESC_TIKTOK + ADDRESS_DESC_TIKTOK
                + TAG_DESC_HUSBAND + TAG_DESC_FRIEND, Company.MESSAGE_CONSTRAINTS);

        // invalid phone
        assertParseFailure(parser, NAME_DESC_TIKTOK + INVALID_PHONE_DESC + EMAIL_DESC_TIKTOK + ADDRESS_DESC_TIKTOK
                + TAG_DESC_HUSBAND + TAG_DESC_FRIEND, Phone.MESSAGE_CONSTRAINTS);

        // invalid email
        assertParseFailure(parser, NAME_DESC_TIKTOK + PHONE_DESC_TIKTOK + INVALID_EMAIL_DESC + ADDRESS_DESC_TIKTOK
                + TAG_DESC_HUSBAND + TAG_DESC_FRIEND, Email.MESSAGE_CONSTRAINTS);

        // invalid address
        assertParseFailure(parser, NAME_DESC_TIKTOK + PHONE_DESC_TIKTOK + EMAIL_DESC_TIKTOK + INVALID_ADDRESS_DESC
                + TAG_DESC_HUSBAND + TAG_DESC_FRIEND, Address.MESSAGE_CONSTRAINTS);

        // invalid tag
        assertParseFailure(parser, NAME_DESC_TIKTOK + PHONE_DESC_TIKTOK + EMAIL_DESC_TIKTOK + ADDRESS_DESC_TIKTOK
                + INVALID_TAG_DESC + VALID_TAG_FRIEND, Tag.MESSAGE_CONSTRAINTS);

        // two invalid values, only first invalid value reported
        assertParseFailure(parser, INVALID_NAME_DESC + PHONE_DESC_TIKTOK + EMAIL_DESC_TIKTOK + INVALID_ADDRESS_DESC,
                Company.MESSAGE_CONSTRAINTS);

        // non-empty preamble
        assertParseFailure(parser, PREAMBLE_NON_EMPTY + NAME_DESC_TIKTOK + PHONE_DESC_TIKTOK + EMAIL_DESC_TIKTOK
                + ADDRESS_DESC_TIKTOK + TAG_DESC_HUSBAND + TAG_DESC_FRIEND,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE));
    }
}
