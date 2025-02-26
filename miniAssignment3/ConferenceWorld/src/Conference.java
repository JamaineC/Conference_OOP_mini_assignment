import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * To model a Conference - a collection of talks
 *
 * @author Jamaine Christian jac105
 * @version 2 (31/03/2022)
 */
public class Conference  {
    private String name;
    private ArrayList<Event> events;
    private ArrayList<Venue> venues;

    /**
     * Creates a conference
     */
    public Conference(){
        events = new ArrayList<>();
        venues = new ArrayList<>();
    }

    /**
     * This method gets the value for the name attribute. The purpose of the
     * attribute is: The name of the Conference e.g. "QCon London 2019"
     *
     * @return String The name of the conference
     */
    public String getName() {
        return name;
    }

    /**
     * This method sets the value for the name attribute. The purpose of the
     * attribute is: The name of the conference e.g. "QCon London 2019"
     *
     * @param name The name of the conference
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Enables a user to add a Talk to the Conference
     *
     * @param event A new event
     */
    public void addEvent(Event event){
        events.add(event);
    }

    /**
     * Add a new venue to the conference
     *
     * @param venue Must be a unique name
     * @return true if venue successfully added or false if the venue already exists
     */
    public boolean addVenue(Venue venue){
        boolean success = false;
        if (!venues.contains(venue)){
            venues.add(venue);
            success = true;
        }
        return success;
    }

    /**
     * Enables a user to delete a Talk from the Conference.
     *
     * @param eventName The event to remove
     */
    public boolean removeEvent(String eventName){
        // Search for the event by name
        Event which = null;
        for (Event event: events){
            if (eventName.equals(event.getName())){
                which = event;
                break;
            }
        }
        if (which != null){
            events.remove(which); // Requires that Event has an equals method
            System.out.println("removed " + which);
            return true;
        } else {
            System.err.println("cannot remove " + eventName +
                    " - not in conference " + name);
            return false;
        }
    }

    /**
     * Returns an array of the talks in the conference
     *
     * @return An array of the correct size
     */
    public Event[] obtainAllEvents() {
        // ENTER CODE HERE: (POSSIBLY CHANGE SOME, YOU MAY CHANGE THE SIGNATURE TO DEAL
        // WITH ALL KINDS OF EVENTS: TALKS AND SOCIALS)
        // SEE Talk.getSpeakers METHOD FOR SIMILAR CODE
        // YOU MUST IMPLEMENT THIS METHOD, EVEN IF IT IS NOT CURRENTLY USED: YOU WILL NEED TO TEST IT
        // BY ADDING CODE TO ConferenceApp
        Event[] result = new Event[events.size()];
        result = events.toArray(result);
        return result;
    }

    /**
     * Searches for and returns the talk, if found
     * @param name The name of the talk
     * @return The talk or else null if not found
     */
    public Event searchForEvent(String name) {
        // ENTER CODE HERE: (POSSIBLY CHANGE SOME, YOU MAY CHANGE THE SIGNATURE TO DEAL
        // WITH ALL KINDS OF EVENTS: TALKS AND SOCIALS)
        Event result = null;
        for(Event indexOfEvent : events){
            if(indexOfEvent.getName().equals(name)){
                System.out.println("The event " + name + " exists");
                result = indexOfEvent;
            }
        }
        return result;
    }

    /**
     * Searches for and returns the venue, if found
     * @param name The name of the venue
     * @return The venue or else null if not found
     */
    public Venue searchForVenue(String name) {
        // ENTER CODE HERE: POSSIBLY CHANGE SOME, 
		// YOU MUST NOT CHANGE THE SIGNATURE
        Venue result = null;
        for(Venue indexOfVenue : venues){
            if(indexOfVenue.getName().equals(name)){
                System.out.println("The venue " + name + " is now your selected venue for this event");
                result = indexOfVenue;
            }
        }
        return result;
    }

    /**
     * @return String showing all the information in the kennel
     */
    public String toString() {
        // ENTER CODE HERE: CHANGE TO USE StringBuilder TO MAKE MORE EFFICIENT
        StringBuilder results = new StringBuilder();
        results.append("Data in Conference " + name + " is: \n");
        for (Event event : events) {
            results.append(event.toString() + "\n");
        }
        return results.toString();
    }

    /**
     * Reads in Conference information from the file
     *
     * @param filename The file to read from
     * @throws IOException
     * @throws FileNotFoundException
     * @throws IllegalArgumentException if outfileName is null or empty
     */
    public void load(String filename) throws FileNotFoundException, IOException {
        // Using try-with-resource. We will cover this in Seminar 8 and workshop 17, but
        // what it does is to automatically close the file after the try / catch ends.
        // This means we don't have to worry about closing the file.

        // ENTER CODE HERE: YOU WILL NEED TO UPDATE THIS METHOD 
        // TO DEAL WITH DIFFERENT KINDS OF EVENTS
        try (FileReader fr = new FileReader(filename);
             BufferedReader br = new BufferedReader(fr);
             Scanner infile = new Scanner(br)) {

            events.clear();
            venues.clear();

            // Use the delimiter pattern so that we don't have to clear end of line
            // characters after doing a infile.nextInt or infile.nextBoolean and can use infile.next
            infile.useDelimiter("\r?\n|\r"); // End of line characters on Unix or DOS

            name = infile.next();

            while (infile.hasNext()) {
                Event event = new Event();
                System.out.println("Please enter event type: (talk/social)");
                String eventType = infile.next();

                switch (eventType) {
                    case "talk":
                        event = new Talk();
                        break;
                    case "social":
                        event = new SocialEvents();
                        break;
                }
                if (event != null) {
                    event.load(infile);
                    // Read the venue data
                    String venueName = infile.next();
                    boolean hasDataProjector = infile.nextBoolean();
                    Venue theVenue = searchForVenue(venueName);
                    if (theVenue == null) {
                        theVenue = new Venue(venueName);
                        theVenue.setHasDataProjector(hasDataProjector);
                        venues.add(theVenue);
                    }
                    event.setVenue(theVenue);

                    events.add(event);
                }
            }
        }
    }

    /**
     * Write out Conference information to the outfile
     *
     * @param outfileName The file to write to
     * @throws IOException
     * @throws IllegalArgumentException if outfileName is null or empty
     */
    public void save(String outfileName) throws IOException {
        // Again using try-with-resource 
		// so that I don't need to close the file explicitly
		
        try (FileWriter fw = new FileWriter(outfileName);
             BufferedWriter bw = new BufferedWriter(fw);
             PrintWriter outfile = new PrintWriter(bw);) {

            outfile.println(name);
            for (Event event : events) {
                //outfile.println();
                event.save(outfile);

                Venue venue = event.getVenue();
                outfile.println(venue.getName());

                // Print rather than println so that we don't leave a blank line at the end
                outfile.print(venue.hasDataProjector());
            }
        }
    }
}
