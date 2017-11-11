# syy94
###### /java/seedu/address/logic/parser/AddCommandParserTest.java
``` java
        // multiple fields - all accepted
        Person expectedPersonMultipleFields = new PersonBuilder().withName(VALID_NAME_BOB).withPhone(VALID_PHONE_BOB)
                .withEmail(VALID_EMAIL_BOB).withAddress(VALID_ADDRESS_BOB).withGroup(VALID_GROUP_SAVING)
                .withTags(VALID_TAG_FRIEND).withFields(VALID_FIELD_SCHOOL, VALID_FIELD_COMPANY).build();
        assertParseSuccess(parser, AddCommand.COMMAND_WORD + NAME_DESC_BOB + PHONE_DESC_BOB
                        + EMAIL_DESC_BOB + ADDRESS_DESC_BOB + GROUP_DESC_SAVING
                        + TAG_DESC_FRIEND + FIELD_DESC_SCHOOL + FIELD_DESC_COMPANY,
                new AddCommand(expectedPersonMultipleFields));
```
###### /java/seedu/address/logic/UnlockApplicationTest.java
``` java
    private static final String testPass = "testPass";

    private Model model = new ModelManager();
    private Logic logic = new LogicManager(model);

    @BeforeClass
    public static void setup() throws IOException, NoSuchAlgorithmException {
        SecurityManager.savePass(testPass);
    }

    @Test
    public void applicationUnlockTest() {
        assertCommandException("test1", PasswordCommand.MESSAGE_WRONG_PASS);
        assertCommandSuccess(testPass, "Welcome", model);
    }

    @AfterClass
    public static void cleanUp() throws NoSuchAlgorithmException, IOException, WrongPasswordException {
        SecurityManager.removePass(testPass);
    }
```
###### /java/seedu/address/testutil/PersonBuilder.java
``` java
    /**
     * Sets the {@code Email} of the {@code Person} that we are building.
     */
    public PersonBuilder withFields(String ... fields) {
        try {
            this.person.setFields(SampleDataUtil.getFieldsSet(fields));
        } catch (IllegalValueException ive) {
            throw new IllegalArgumentException("field is expected to be unique.");
        }
        return this;
    }
```
###### /java/seedu/address/testutil/PersonUtil.java
``` java
        person.getFields().stream().forEach(
            s -> sb.append(PREFIX_CUSTOM_FIELD + s.toString() + " ")
        );
```
###### /java/seedu/address/testutil/PersonUtil.java
``` java
    /**
     * Returns the part of command string for the given {@code person}'s details.
     */
    public static String getPersonDetailsForEdit(ReadOnlyPerson person) {
        StringBuilder sb = new StringBuilder();
        sb.append(PREFIX_NAME + person.getName().fullName + " ");
        sb.append(PREFIX_PHONE + person.getPhone().value + " ");
        sb.append(PREFIX_EMAIL + person.getEmail().value + " ");
        sb.append(PREFIX_GROUP + person.getGroup().groupName + " ");
        sb.append(PREFIX_ADDRESS + person.getAddress().value + " ");
        person.getTags().stream().forEach(
            s -> sb.append(PREFIX_ADD_TAG + s.tagName + " ")
        );
        person.getFields().stream().forEach(
            s -> sb.append(PREFIX_CUSTOM_FIELD + s.toString() + " ")
        );
        return sb.toString();
    }
```
###### /java/systemtests/EditCommandSystemTest.java
``` java
        /* Case: clear tags -> cleared */
        index = INDEX_FIRST_PERSON;
        command = EditCommand.COMMAND_WORD + " " + index.getOneBased() + " " + PREFIX_CLEAR_TAG.getPrefix();
        editedPerson = new PersonBuilder(personToEdit).withTags().build();
        assertCommandSuccess(command, index, editedPerson);

        /* Case: checks that edit tag prefix precedence is respected -> tags cleared */
        index = INDEX_FIRST_PERSON;
        command = EditCommand.COMMAND_WORD + " " + index.getOneBased() + TAG_REMOVE_DESC_FRIEND + TAG_ADD_DESC_FRIEND
                + " " + PREFIX_CLEAR_TAG.getPrefix();
        editedPerson = new PersonBuilder(personToEdit).withTags().build();
        assertCommandSuccess(command, index, editedPerson);
```
###### /java/systemtests/FindCommandSystemTest.java
``` java
        /* Case: find field value of person in address book -> 1 persons found */
        command = FindCommand.COMMAND_WORD + " " + PREFIX_CUSTOM_FIELD + VALID_FIELD_FIND_DANIEL_VALUE;
        ModelHelper.setFilteredList(expectedModel, DANIEL);
        assertCommandSuccess(command, expectedModel);
        assertSelectedCardUnchanged();

        /* Case: find field key of person in address book -> 1 persons found */
        command = FindCommand.COMMAND_WORD + " " + PREFIX_CUSTOM_FIELD + VALID_FIELD_FIND_DANIEL_KEY;
        ModelHelper.setFilteredList(expectedModel, DANIEL);
        assertCommandSuccess(command, expectedModel);
        assertSelectedCardUnchanged();

        /* Case: find field key and value of person in address book -> 1 persons found */
        command = FindCommand.COMMAND_WORD + " " + PREFIX_CUSTOM_FIELD + VALID_FIELD_DANIEL;
        ModelHelper.setFilteredList(expectedModel, DANIEL);
        assertCommandSuccess(command, expectedModel);
        assertSelectedCardUnchanged();
```
