import java.io.IOException;

/**
 * The parser class processes user inputs into understandable commands
 * which are then executed by the Duke program. 
 */
public class Parser {

    /**
     * Creates a new Parser object.
     */
    public Parser() {

    }

    /**
     * Processes the user's input into a valid command and takes an action 
     * accordingly based on the deciphered command.
     * 
     * @param input The user input.
     * @param taskList The taskList object handling all tasks.
     * @param storage The Storage object needed to load and write back to the tasks file. 
     * @throws Exception When any error occurs during the execution of the user command.
     */
    public StringBuilder processCommand(
        String input, TaskList taskList, Storage storage) throws DukeException, IOException {
        String[] command = input.split(" ");
        assert command.length > 0 : "command length should be greater than 0";
        if (command[0].equals("list")) {
            return taskList.printTasks();
        } else if (command[0].equals("delete")) {
            return new DeleteCommand().execute(input, taskList, storage);
        } else if (command[0].equals("done")) {
            return new DoneCommand().execute(input, taskList, storage);
        } else if (command[0].equals("find")) { 
            return new FindCommand().execute(input, taskList, storage);
        } else if (command[0].equals("bye")) {
            return new ExitCommand().execute(input, taskList, storage);
        } else { 
            return new AddCommand().execute(input, taskList, storage); 
        }
    }
}