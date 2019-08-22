import java.util.Scanner;
import java.util.ArrayList;

public class Duke {


    public static void printList(ArrayList<Task> list) {
        int i = 1;
        System.out.println("Here are the tasks in your list:");
        for (Task task : list) {
            System.out.println(i + "." + task.toString());
            i += 1;
        }
    }

    public static void taskDone(int i, ArrayList<Task> list) throws Exception {
        try {
            list.get(i - 1).markAsDone();
        } 
        catch (IndexOutOfBoundsException e) {
            throw new DukeException("☹ OOPS!!! The item specified does not exist.");
        }
        
        System.out.println("Nice! I've marked this task as done: ");
        System.out.println(list.get(i - 1).toString());

    }

    public static void deleteTask(int i, ArrayList<Task> list) throws Exception {
        try {
            list.get(i - 1);
        }
        catch (IndexOutOfBoundsException e) {
            throw new DukeException("☹ OOPS!!! The item specified does not exist.");
        }
        
        System.out.println("Noted. I've removed this task: ");
        System.out.println(list.get(i - 1).toString());
        list.remove(i - 1);
        System.out.println("Now you have " + list.size() + " tasks in the list.");
    }

    public static void validateDetail(String[] detail) throws DukeException {
        if (detail.length == 0) {
            throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        } else if (! detail[0].equals("todo") && ! detail[0].equals("event") &&! detail[0].equals("deadline")) {
            throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        } else if (detail.length == 1) {
            throw new DukeException("☹ OOPS!!! The description of a " + detail[0] + " cannot be empty.");
        }
    }

    public static void validateDeadlineDetails(String[] detail) throws DukeException {
        if (detail.length != 2) {
            throw new DukeException("☹ OOPS!!! The due date of a deadline must be specified.");
        }
    }

    public static void validateEventDetails(String[] detail) throws DukeException {
        if (detail.length != 2) {
            throw new DukeException("☹ OOPS!!! The timeline of an event must be specified.");
        }
    }

    public static void addTask(String input, ArrayList<Task> list) throws Exception {
        Task task;
        String detail;
        String logo = " ____        _        \n"
                    + "|  _ \\ _   _| | _____ \n"
                    + "| | | | | | | |/ / _ \\\n"
                    + "| |_| | |_| |   <  __/\n"
                    + "|____/ \\__,_|_|\\_\\___|\n";
        String dueDetail;
        String[] inputAsArr = input.split(" ");
        validateDetail(inputAsArr);
        String command = inputAsArr[0];
        String rest = input.substring(input.indexOf(" ") + 1);

        if (command.equals("todo")) {
            task = new ToDos(rest);
            list.add(task);
            System.out.println("Got it. I've added this task:");
            System.out.println("\t" + task.toString());
        } else if (command.equals("deadline")) {
            String[] detAsArr = rest.split(" /by ");
            validateDeadlineDetails(detAsArr);
            detail = detAsArr[0];
            dueDetail = detAsArr[1];
            task = new Deadline(detail, dueDetail);
            list.add(task);
            System.out.println("Got it. I've added this task:");
            System.out.println("\t" + task.toString());
        } else {
            String[] detAsArr = rest.split(" /at ");
            validateEventDetails(detAsArr);
            detail = detAsArr[0];
            dueDetail = detAsArr[1];
            task = new Event(detail, dueDetail);
            list.add(task);
            System.out.println("Got it. I've added this task:");
            System.out.println("\t" + task);
        }
        System.out.println("Now you have " + list.size() + " tasks in the list.");
    }

    public static void main(String[] args) throws Exception {
        ArrayList<Task> list = new ArrayList<>();
        String input;
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        
        input = scanner.nextLine();

        while (! input.equals("bye")) {
            try {
                String[] command = input.split(" ");
                
                if (input.equals("list")) {
                    printList(list);
                } else if (command[0].equals("done")) {
                    if (command.length == 1) {
                        throw new DukeException("☹ OOPS!!! The completed task's index must be mentioned.");
                    } else {
                        try {
                            taskDone(Integer.parseInt(command[1]), list);
                        } 
                        catch (NumberFormatException nfe) {
                            throw new DukeException("☹ OOPS!!! The completed task's index must be a number.");
                        }
                    }
                } else if (command[0].equals("delete")) {
                    if (command.length == 1) {
                        throw new DukeException("☹ OOPS!!! The index of task to be deleted must be mentioned.");
                    } else {
                        try {
                            deleteTask(Integer.parseInt(command[1]), list);
                        } 
                        catch (NumberFormatException nfe) {
                            throw new DukeException("☹ OOPS!!! The index of task to be deleted must be a number.");
                        }
                    }
                } else {
                   addTask(input, list); 
                }
            } 
            catch (Exception e) {
                System.err.println(e);
            }
            finally {
                input = scanner.nextLine();
            }
        }

        System.out.println("Bye. Hope to see you again soon!");

    }
}
