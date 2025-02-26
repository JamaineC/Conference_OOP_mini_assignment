import java.io.PrintWriter;
import java.util.Calendar;
import java.util.Scanner;

/**
 * The event superclass for the Conference.
 * @author Jamaine Christian jac105
 * @version 2.0 (31/03/22)
 */
public class Event {
    public Calendar startDateTime;
    public Calendar endDateTime;
    public String name;
    public Venue venue;
    public boolean dataProjectorRequired;

    /**
     * Constructor for Talk
     *
     * @param name          The talk title
     * @param startDateTime When it starts
     * @param endDateTime   When it ends
     */
    public Event(String name, Calendar startDateTime, Calendar endDateTime) {
        this.name = name;
        // We should really check that the start time is before the end time
        if(startDateTime.compareTo(endDateTime) < 0) {
            this.startDateTime = startDateTime;
            this.endDateTime = endDateTime;
        }
        else{
            System.out.println("This event cannot finish before it starts");
        }
    }

    /**
     * empty event constructor
     */
    public Event() {
    }

    /**
     * this gets the event name
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * this sets the event name
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Sets the venue. This will only be allowed
     * if the meets the data projector requirement.
     * Otherwise displays an error message. This should really throw an exception
     *
     * @param venue The venue for the talk
     */
    public void setVenue(Venue venue) {
        // Only allow this if the venue spec matches the
        // the talk requirement
        if (dataProjectorRequired && !venue.hasDataProjector()) {
            System.err.println("Talk " + name + " requires a data projector. " +
                    "Venue " + venue.getName() + " does not have one");
        } else {
            this.venue = venue;
        }
    }

    /**
     * this gets the Venue
     * @return venue
     */
    public Venue getVenue() {
        return venue;
    }

    /**
     * this decides if the data projector is required
     * @return isDataProjectorRequired
     */
    public boolean isDataProjectorRequired() {
        return dataProjectorRequired;
    }

    /**
     * Sets the data projector requirement. This will only be allowed
     * if there is an associated venue that meets the requirement.
     * Otherwise displays an error message. This should really throw an exception
     *
     * @param dataProjectorRequired Whether required or not
     */
    public void setDataProjectorRequired(boolean dataProjectorRequired) {
        if (venue != null && (dataProjectorRequired && !venue.hasDataProjector())) {
            System.err.println("Talk " + name + " currently has a venue " +
                    venue.getName() + " that does not have a data projector. Change the venue first");
        } else {
            this.dataProjectorRequired = dataProjectorRequired;
        }
    }

    /**
     *  this gets the time of the start of the event
     * @return startDateTime
     */
    public Calendar getStartDateTime() {
        return startDateTime;
    }

    /**
     * this sets the start date and time of the event
     * @param startDateTime
     */
    public void setStartDateTime(Calendar startDateTime) {
        // We should really check that the start time is before the end time
        this.startDateTime = startDateTime;
    }

    /**
     * this gets the end date and time of the event
     * @return endDateTime
     */
    public Calendar getEndDateTime() {
        return endDateTime;
    }

    /**
     * this stes the end date and time of the event
     * @param endDateTime
     */
    public void setEndDateTime(Calendar endDateTime) {
        // We should really check that the end time is after the start time
        this.endDateTime = endDateTime;
    }

    /**
     * this turns calendar values into a string
     * @param dateTime
     * @return the date and time
     */
    private String dateTimeToString(Calendar dateTime) {

        int year = dateTime.get(Calendar.YEAR);
        int month = dateTime.get(Calendar.MONTH) + 1; // We have to add 1 since months start from 0
        int day = dateTime.get(Calendar.DAY_OF_MONTH);
        int hour = dateTime.get(Calendar.HOUR_OF_DAY);
        int minutes = dateTime.get(Calendar.MINUTE);

        return "" + year + ":" + month + ":" + day + ":" + hour + ":" + minutes;
    }

    /**
     * this turns the event information into a string
     * @return string
     */
    public String toString() {

       return "startDateTime=" + dateTimeToString(startDateTime) +
                ", endDateTime=" + dateTimeToString(endDateTime) +
                ", name='" + name + '\'' +
                ", venue=" + venue +
                ", dataProjectorRequired=" + dataProjectorRequired;

    }

    /**
     *
     * @param outfile writes date to our file
     * @param dateTime gets the dateTime
     */
    private void writeDateTime(PrintWriter outfile, Calendar dateTime) {
        outfile.println(dateTime.get(Calendar.YEAR));
        outfile.println(dateTime.get(Calendar.MONTH));
        outfile.println(dateTime.get(Calendar.DAY_OF_MONTH));
        outfile.println(dateTime.get(Calendar.HOUR_OF_DAY));
        outfile.println(dateTime.get(Calendar.MINUTE));
    }

    /**
     *
     * @param scan reads our input
     * @return our input from scan
     */
    public Calendar readDateTime(Scanner scan) {
        Calendar result = Calendar.getInstance();

        int year = scan.nextInt();
        int month = scan.nextInt();
        int day = scan.nextInt();
        int hour = scan.nextInt();
        int minutes = scan.nextInt();
        result.clear();
        result.set(year, month, day, hour, minutes);
        return result;
    }

    /***
     *
     * @param outfile saves it to our file
     */
    public void save(PrintWriter outfile) {
        if (outfile == null)
            throw new IllegalArgumentException("outfile must not be null");
        outfile.println(name);
        writeDateTime(outfile, startDateTime);
        writeDateTime(outfile, endDateTime);

        outfile.println(dataProjectorRequired);
    }

    /**
     *
     * @param infile loads the file
     */
    public void load(Scanner infile) {
        if (infile == null) {
            throw new IllegalArgumentException("infile must not be null");
        }
        name = infile.next();
        startDateTime = readDateTime(infile);
        endDateTime = readDateTime(infile);

        dataProjectorRequired = infile.nextBoolean();


    }
}
