import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class DateHelperTest {

    @Test
    public void parseTime_validTimeFormat_timeStringReturned() throws DukeException {
        assertEquals("5.26pm", DateHelper.parseTime("1726"));
        assertEquals("9.24am", DateHelper.parseTime("924"));
        assertEquals("3.33am", DateHelper.parseTime("333"));
        assertEquals("11.45pm", DateHelper.parseTime("2345"));
        assertEquals("12.18pm", DateHelper.parseTime("1218"));
    }

    @Test
    public void parseDate_validDateFormat_dateStringReturned() throws DukeException {
        assertEquals("11th of February, 2019", DateHelper.parseDate("11/2/2019"));
        assertEquals("23rd of June, 2018", DateHelper.parseDate("23/6/2018"));
        assertEquals("9th of April, 2022", DateHelper.parseDate("9/4/2022"));
        assertEquals("12th of May, 2015", DateHelper.parseDate("12/5/2015"));
        assertEquals("16th of November, 2020", DateHelper.parseDate("16/11/2020"));
    }    

    @Test
    public void parseDate_insufficientDateDescription_dukeExceptionThrown() throws DukeException {
        try {
            DateHelper.parseDate("16112020");
        } catch (DukeException e) {
            assertEquals("OOPS!!! The date you inputted is not of valid format.", e.getMessage());
        }
    } 

    @Test
    public void parseTime_invalidTimeFormat_dukeExceptionThrown() throws DukeException {
        try {
            DateHelper.parseTime("2411");
        } catch (DukeException e) {
            assertEquals("OOPS!!! The time you inputted is not valid.", e.getMessage());
        }
    }
}