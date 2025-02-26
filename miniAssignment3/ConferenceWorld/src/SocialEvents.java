import java.io.PrintWriter;
import java.util.Calendar;
import java.util.Scanner;

/**
 * The social event subclass for the Conference.
 * @author Jamaine Christian jac105
 * @version 2.0 (31/03/22)
 */
public class SocialEvents extends Event{
    private Scanner scan;
    private boolean catering;
    private boolean invitation;
    /**
     * Constructor for social events
     *
     * @param name          The event title
     * @param startDateTime When it starts
     * @param endDateTime   When it ends
     */
    public SocialEvents(String name, Calendar startDateTime, Calendar endDateTime) {
        super(name, startDateTime, endDateTime);
    }

    /**
     * empty social event constructor
     */
    public SocialEvents() {


    }

    /**
     * this method return is catering is required
     * @return catering
     */
    public boolean isCatering() {
        return catering;
    }

    /**
     * This sets whether catering is required or not
     * @param catering
     */

    public void setCatering(boolean catering) {
        this.catering = catering;
    }

    /**
     * This decides if the social event is invitation only
     * @return invitation
     */
    public boolean isInvitation() {
        return invitation;
    }

    /**
     * This sets the social event to either being invite only or not
     * @param invitation
     */
    public void setInvitation(boolean invitation) {
        this.invitation = invitation;
    }

    /**
     * method for getting users input about the social event requirements
     */
    public void readKeyboard() {
        scan = new Scanner(System.in);
        System.out.println("Is catering required? (yes/no)");
        String choice = scan.nextLine().toLowerCase();
        if (choice.equals("no")) {
            catering = false;
        }
        else {
            catering = true;
        }
        System.out.println("Is it invitation only? (yes/no)");
        String choice1 = scan.nextLine().toLowerCase();
        if (choice1.equals("no")) {
            invitation = false;

        } else {
            invitation = true;
        }

    }

    /**
     *
     * @return Social event information
     */
    @Override
    public String toString() {
        return "\n  eventType= Social Event , " + super.toString() + "SocialEvents{" +
                "catering=" + catering +
                ", invitation=" + invitation +
                '}';
    }

    /**
     * this loads the file
     * @param infile loads the file
     */
    public void load(Scanner infile) {
        super.load(infile);
        catering = infile.nextBoolean();
        invitation = infile.nextBoolean();
    }

    /**
     * this saves the file
     * @param outfile saves it to our file
     */
    public void save(PrintWriter outfile) {
        if (outfile == null) {
            throw new IllegalArgumentException("outfile cannot not be equal to null");
        }
        System.out.println(" ");
        super.save(outfile);
        outfile.println(catering);
        outfile.println(invitation);
    }


}
