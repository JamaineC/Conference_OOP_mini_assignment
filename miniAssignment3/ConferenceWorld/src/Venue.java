import java.util.Objects;

/**
 * The venue class for the Conference.
 * @author Jamaine Christian jac105
 * @version 2.0 (31/03/22)
 */
public class Venue {
    private String name;
    private boolean hasDataProjector;



    /**
     *
     * @param name
     */
    public Venue(String name){
        this.name = name;
    }

    /**
     * this gets the name
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * this sets the name
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     *
     * @return if a project is needed
     */
    public boolean hasDataProjector() {
        return hasDataProjector;
    }

    /**
     * this sets the venue to either having a data projector or not
     * @param hasDataProjector
     */
    public void setHasDataProjector(boolean hasDataProjector) {
        this.hasDataProjector = hasDataProjector;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Venue location = (Venue) o;
        return Objects.equals(name, location.name);
    }

    /**
     * turns venue information into a string
     * @return string
     */
    @Override
    public String toString() {
        return "Venue{" +
                "name='" + name + '\'' +
                ", hasDataProjector=" + hasDataProjector +
                '}';
    }
}
