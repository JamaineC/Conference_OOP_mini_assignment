import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Scanner;

/**
 * The main application class for the Conference. Has a command line menu.
 * @author Jamaine Christian jac105
 * @version 2.0 (31/03/22)
 */

public class ConferenceApp {

    private String filename;
    private Scanner scan;
    private Conference conference;

    /*
     * Notice how we can make this private, since we only call from main which
     * is in this class. We don't want this class to be used by any other class.
     */
    private ConferenceApp() {
        scan = new Scanner(System.in);
        System.out.print("Please enter the filename of conference information: ");
        filename = scan.nextLine();

        conference = new Conference();
    }

    /*
     * initialise() method runs from the main and reads from a file
     */
    private void initialise() {
        System.out.println("Using file " + filename);

        try {
            conference.load(filename);
        } catch (FileNotFoundException e) {
            System.err.println("The file: " + " does not exist. Assuming first use and an empty file." +
                    " If this is not the first use then have you accidentally deleted the file?");
        } catch (IOException e) {
            System.err.println("An unexpected error occurred when trying to open the file " + filename);
            System.err.println(e.getMessage());
        }
    }

    /*
     * runMenu() method runs from the main and allows entry of data etc
     */
	 
	 // ENTER CODE HERE: TO MAKE THE MENU MORE GENERALISED 
	 // FOR SUPPROTING BOTH TALKS AND SOCIAL EVENTS
    private void runMenu() {
        String response;
        do {
            printMenu();
            System.out.println("What would you like to do:");
            scan = new Scanner(System.in);
            response = scan.nextLine().toUpperCase();
            switch (response) {
                case "1":
                    addEvent();
                    break;
                case "2":
                    addVenue();
                    break;
                case "3":
                    searchForEvent();
                    break;
                case "4":
                    removeEvent();
                    break;
                case "5":
                    changeConferenceName();
                    break;
                case "6":
                    printAll();
                    break;
                case "7":
                    conference.obtainAllEvents();
                case "Q":
                    break;
                default:
                    System.out.println("Try again");
            }
        } while (!(response.equals("Q")));
    }

    private void printMenu() {
        System.out.println("1 -  add a new Event");
        System.out.println("2 -  add a venue");
        System.out.println("3 -  search for an event");
        System.out.println("4 -  remove an event");
        System.out.println("5 -  change conference name");
        System.out.println("6 -  display everything");
        System.out.println("7 -  obtain all events"); // this does not work
        System.out.println("q -  Quit");
    }

    private void addEvent() {
        String choice;
        System.out.println(" press 1 for a talk and 2 for a social event");
        choice = scan.nextLine();

        if (choice.equals("1")) {
            addTalk();
        }
        else if (choice.equals("2")) {
            addSocialEvent();
        }
        else {
            System.out.println("Invalid choice");
        }
    }
    private void addTalk() {

        System.out.println("Get speakers: ");
        ArrayList<Speaker> speakers = getSpeakers();

        Talk talk = new Talk();
        talk.setSpeakers(speakers);

        populateAndAddToConference(talk);
    }
    private void addSocialEvent() {
        SocialEvents social = new SocialEvents();
        social.readKeyboard();
        populateAndAddToConference(social);
    }

    /*
     * Adds event general data. This is common to all events. Then
     * adds to the conference.
     */
    private void populateAndAddToConference(Event event){
        System.out.println("Event name: ");
        String name = scan.nextLine();

        System.out.println("Enter start time for event");
        Calendar startDateTime = getDateTime();
        System.out.println("Enter end time for event");
        Calendar endDateTime = getDateTime();

        System.out.println("Is a data projector required?(Y/N)");
        String answer = scan.nextLine().toUpperCase();
        boolean projectorRequired = true;
        if (answer.equals("N")){
            projectorRequired = false;
        }

        Venue venue = null;
        do {
            System.out.println("Enter venue name");
            String venueName = scan.nextLine();
            answer = "N";
            venue = conference.searchForVenue(venueName);
            if (venue != null) {
                if (projectorRequired && !venue.hasDataProjector()){
                    System.out.println("Selected venue does not have a data projector. Choose a different venue");
                    answer = "Y";
                } else{
                    event.setName(name);
                    event.setDataProjectorRequired(projectorRequired);
                    event.setStartDateTime(startDateTime);
                    event.setEndDateTime(endDateTime);
                    event.setVenue(venue);

                    conference.addEvent(event);
                }
            } else {
                System.out.println("Venue " + venue + " does not exist. Try a different venue? (Y/N)");
                answer = scan.nextLine().toUpperCase();
            }
        } while (answer.equals("Y"));
    }


    private Calendar getDateTime() {
        Calendar result = Calendar.getInstance();
        System.out.println("On one line (numbers): year month day hour minutes");

        // Note that an ArrayIndexOutOfBoundsException is thrown if an
        // illegal value is entered. For simplicity, we will pretend that won't happen.

        int year = scan.nextInt();
        // Note that months start from 0 so we have to subtract 1
        // when reading and then add 1 when displaying the result
        int month = scan.nextInt() - 1;
        int day = scan.nextInt();
        int hour = scan.nextInt();
        int minutes = scan.nextInt();
        scan.nextLine(); // Clear the end of line character

        result.clear();
        result.set(year, month, day, hour, minutes);

        System.out.println("The date/time you entered was: " +
                result.get(Calendar.YEAR) + "/" +
                (result.get(Calendar.MONTH) + 1) + "/" +
                result.get(Calendar.DAY_OF_MONTH) + ":" +
                result.get(Calendar.HOUR_OF_DAY) + ":" +
                result.get(Calendar.MINUTE));
        return result;
    }

    private ArrayList<Speaker> getSpeakers() {
        ArrayList<Speaker> speakers = new ArrayList<>();
        String answer;
        do {
            System.out.println("Enter on separate lines: speaker-name speaker-phone");
            String speakerName = scan.nextLine();
            String speakerPhone = scan.nextLine();
            Speaker speaker = new Speaker(speakerName, speakerPhone);
            speakers.add(speaker);
            System.out.println("Another owner (Y/N)?");
            answer = scan.nextLine().toUpperCase();
        } while (!answer.equals("N"));
        return speakers;
    }

    private void changeConferenceName() {
        String name = scan.nextLine();
        conference.setName(name);
    }

    private void searchForEvent() {
        System.out.println("Which talk do you want to search for");
        String name = scan.nextLine();
        Event event = conference.searchForEvent(name);
        if (event != null){
            System.out.println(event);
        } else {
            System.out.println("Could not find talk: " + name);
        }
    }

    private void removeEvent() {
        System.out.println("Which event do you want to remove");
        String eventToBeRemoved;
        eventToBeRemoved = scan.nextLine();
        conference.removeEvent(eventToBeRemoved);
    }

    private void addVenue() {
        Venue venue;
        String venueName;
        boolean tryAgain;
        do {
            tryAgain = false;
            System.out.println("Enter the venue name");
            venueName = scan.nextLine();
            venue = conference.searchForVenue(venueName);
            if (venue != null){
                System.out.println("This venue already exists. Give it a different name");
                tryAgain = true;
            }
        }while(tryAgain);

        System.out.println("Does it have a data projector?(Y/N)");
        String answer = scan.nextLine().toUpperCase();
        boolean hasDataProjector = answer.equals("Y");

        venue = new Venue(venueName);
        venue.setHasDataProjector(hasDataProjector);

        conference.addVenue(venue);
    }

    private void printAll() {
        // ENTER CODE HERE:THIS IS NOT SORTED. 
		// YOU WILL NEED TO UPDATE THIS TO DISPLAY SORTED EVENTS
        System.out.println(conference);
    }

    /*
     * save() method runs from the main and writes back to file
     */
    private void save() {
        try{
            conference.save(filename);
        } catch (IOException e) {
            System.err.println("Problem when trying to write to file: " + filename);
        }

    }

    // /////////////////////////////////////////////////
    public static void main(String args[]) {
        System.out.println("**********WELCOME TO CONFERENCE CREATOR***********");

        ConferenceApp app = new ConferenceApp();
        app.initialise();
        app.runMenu();
        app.printAll();
        // MAKE A BACKUP COPY OF conf.txt JUST IN CASE YOU CORRUPT IT
        app.save();

        System.out.println("***********THANK YOU FOR USING OUR SERVICES**********");
    }


}
