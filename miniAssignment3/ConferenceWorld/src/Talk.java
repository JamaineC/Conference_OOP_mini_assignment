import java.io.PrintWriter;
import java.util.*;

/**
 * The talk subclass for the Conference.
 * @author Jamaine Christian jac105
 * @version 2.0 (31/03/22)
 */
public class Talk extends Event{

    private String name;
    private ArrayList<Speaker> speakers = new ArrayList<>();

    public Talk() {

    }

    /**
     * Constructor for Talk
     * @param name The talk title
     * @param startDateTime When it starts
     * @param endDateTime When it ends
     */
    public Talk(String name, Calendar startDateTime, Calendar endDateTime) {
        super(name, startDateTime, endDateTime);
        this.name = name;
        // We should really check that the start time is before the end time
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
    }


    public final void setSpeakers(List<Speaker> speakers) {
        // We make a true copy of the speakers ArrayList to make sure that we
        // don't break encapsulation: i.e. don't share object references with
        // other code
        if (speakers == null){
            throw new IllegalArgumentException("speakers must not be null");
        }
        this.speakers.clear();
        for (Speaker s : speakers) {
            Speaker copy = new Speaker(s.getName(), s.getPhone());
            this.speakers.add(copy);
        }
    }

    /**
     * Returns a copy of the speakers
     * @return A copy of the speakers as an array
     */
    public Speaker[] getSpeakers(){
        Speaker[] result = new Speaker[speakers.size()];
        result = speakers.toArray(result);
        return result;
    }

    /**
     * A basic implementation to just return all the data in string form.
     * CHANGE THIS TO USE StringBuffer
     * @return All the string data for the talk
     */
    @Override
    public String toString() {
        StringBuilder results = new StringBuilder();
        results.append("\n  eventType=Talk , ").append(super.toString()).append(", speakers=");
        for(Speaker speaker: speakers){
            results.append(speaker.toString());
        }

        return "" + results;
    }



    /**
     * Reads in Talk specific information from the file
     * @param infile An open file
     * @throws IllegalArgumentException if infile is null
     */
    public void load(Scanner infile) {
        if (infile == null) {
            throw new IllegalArgumentException("infile must not be null");
        }
        name = infile.next();
        startDateTime = readDateTime(infile);
        endDateTime = readDateTime(infile);

        dataProjectorRequired = infile.nextBoolean();

        int numSpeakers = infile.nextInt();
        Speaker speaker = null;
        speakers.clear();
        for (int i=0; i<numSpeakers; i++){
            String speakerName = infile.next();
            String phone = infile.next();
            speaker = new Speaker(speakerName, phone);
            speakers.add(speaker);
        }
    }


    /**
     * Writes out information about the Talk to the file
     * @param outfile An open file
     * @throws IllegalArgumentException if outfile is null
     */
    public void save(PrintWriter outfile) {
        if (outfile == null)
            throw new IllegalArgumentException("outfile must not be null");
        super.save(outfile);
        outfile.println(speakers.size());
        for (Speaker speaker: speakers){
            outfile.println(speaker.getName());
            outfile.println(speaker.getPhone());
        }
    }


    /**
     * Note that this only compares equality based on a
     * talks's name.
     * @param o the other talk to compare against.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;  // Are they the same object?
        if (o == null || getClass() != o.getClass()) return false; // Are they the same class?
        Talk talk = (Talk) o;  // Do the cast to Talk
        // Now just check the names
        return Objects.equals(name, talk.name); // Another way of checking equality. Also checks for nulls
    }

}
