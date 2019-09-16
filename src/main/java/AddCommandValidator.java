/**
 * Class used for validating user commands regarding adding 
 * a new task into Duke.
 */
public class AddCommandValidator {

    /**
     * Constructs a new AddCommandValidator object.
     */
    public AddCommandValidator() {

    }

    /**
     * Validates whether a command inputted by a user into Duke
     * is valid or not.
     * 
     * @param detail The details as inputted by the user but split into an array based on whitespaces. 
     * @throws InvalidDukeCommandException If the command inputted by the user is invalid.
     */
    public void validateDetail(String[] detail) throws InvalidDukeCommandException {
        if (detail.length == 0) {
            throw new InvalidDukeCommandException("OOPS!!! I'm sorry, but I don't know what that means :-(");
        } else if (! detail[0].equals("todo") && ! detail[0].equals("event") && ! detail[0].equals("deadline")) {
            throw new InvalidDukeCommandException("OOPS!!! I'm sorry, but I don't know what that means :-(");
        } else if (detail.length == 1) {
            throw new InvalidDukeCommandException("OOPS!!! The description of a " + detail[0] + " cannot be empty.");
        }
    }

    /**
     * Checks if the details given for the deadline by the user are valid
     * and sufficient to create the deadline.
     * 
     * @param detail The details as inputted by the user but split into an array based on whitespaces. 
     * @throws InvalidDeadlineSyntaxException If the deadline details as inputted 
     *     by the user are insufficient or of incorrect format.
     */
    public void validateDeadlineDetails(String[] detail) throws InvalidDeadlineSyntaxException {
        if (detail.length != 2) {
            throw new InvalidDeadlineSyntaxException("OOPS!!! The due date of a deadline must be specified.");
        }
    }

    /**
     * Checks if the details given for the event by the user are valid
     * and sufficient to create the event.
     * 
     * @param detail The details as inputted by the user but split into an array based on whitespaces. 
     * @throws InvalidEventSyntaxException If the event details as 
     *     inputted by the user are insufficient or of incorrect format.
     */
    public void validateEventDetails(String[] detail) throws InvalidEventSyntaxException {
        if (detail.length != 2) {
            throw new InvalidEventSyntaxException("OOPS!!! The timeline of an event must be specified.");
        }
    }
}