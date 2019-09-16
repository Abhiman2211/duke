import java.text.DateFormatSymbols;

/**
 * Helps handle and format the date inputted by the user into 
 * Duke into a valid format.
 */
public class DateHelper {

    /**
     * Parses the user inputted time into a text format that will
     * be displayed by Duke in its responses.
     * 
     * @param timeDetail The time as inputted by the user.
     * @return String representation of the time as to be shown by Duke.
     * @throws InvalidDukeDateException When the user inputs invalid or non-existent time details.
     */
    public static String parseTime(String timeDetail) throws InvalidDukeDateException {
        int hour = (Integer.parseInt(timeDetail) / 100);
        validateHour(hour);
        String actualHour = hour % 12 == 0 ? "12" : String.valueOf(hour % 12); 
        int minute = Integer.parseInt(timeDetail) % 100;
        validateMinute(minute);
        String meridiem = hour >= 12 && hour < 24 ? "pm" : "am";
        String processedMinute = minute == 0 ? "" : "." + String.valueOf(minute);
        return actualHour + processedMinute + meridiem;
    }

    /**
     * Parses the user inputted date into a text format that will
     * be displayed by Duke in its responses.
     * 
     * @param dateDetail The date as inputted by the user.
     * @return String representation of the date as to be shown by Duke.
     * @throws InvalidDukeDateException When the user inputs invalid or non-existent date details.
     */
    public static String parseDate(String dateDetail) throws InvalidDukeDateException {
        String[] dateBreakup = dateDetail.split("/");
        validateDateInput(dateBreakup);
        String month = getMonth(dateBreakup[1]);    
        int lastDigitOfDay = Integer.parseInt(dateBreakup[0]) % 10;
        int firstDigitOfDay = Integer.parseInt(dateBreakup[0]) / 10;
        String dayEnding = firstDigitOfDay == 1 ? "th" : lastDigitOfDay == 1 
                ? "st" : lastDigitOfDay == 2 ? "nd" : lastDigitOfDay == 3 ? "rd" : "th";
        return dateBreakup[0] + dayEnding + " of " + month + ", " + dateBreakup[2]; 
    }

    private static String getMonth(String month) throws InvalidDukeDateException {
        int monthIndex = Integer.parseInt(month);
        if (monthIndex > 12 || monthIndex < 1) {
            throw new InvalidDukeDateException("OOPS!!! The month you inputted is not valid.");
        }
        return new DateFormatSymbols().getMonths()[monthIndex - 1];
    }

    private static void validateHour(int hour) throws InvalidDukeDateException {
        if (hour >= 24 || hour < 0) {
            throw new InvalidDukeDateException("OOPS!!! The time you inputted is not valid.");
        }
    }

    private static void validateMinute(int minute) throws InvalidDukeDateException {
        if (minute > 59 || minute < 0) {
            throw new InvalidDukeDateException("OOPS!!! The time you inputted is not valid.");
        }
    }

    private static void validateDateInput(String[] date) throws InvalidDukeDateException {
        if (date.length != 3) {
            throw new InvalidDukeDateException("OOPS!!! The date you inputted is not of valid format.");
        }
    }

}