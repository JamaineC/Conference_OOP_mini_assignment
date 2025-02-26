import java.util.Objects;

/**
 * The speaker class for the Conference.
 * @author Jamaine Christian jac105
 * @version 2.0 (31/03/22)
 */
public class Speaker {
    private String name;
    private String phone;

    /**
     *
     * @param name
     */
    public Speaker(String name){
        this.name = name;
    }

    /**
     * basic speaker constructor
     * @param name
     * @param phone
     */
    public Speaker(String name, String phone){
        this(name);
        this.phone = phone;

    }

    /**
     * this gets the speakers name
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * this sets the speakers name
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * this gets the speakers phone number
     * @return phone
     */
    public String getPhone() {
        return phone;
    }

    /**
     * this sets the speakers phone number
     * @param phone
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Speaker speaker = (Speaker) o;
        return Objects.equals(name, speaker.name) &&
                Objects.equals(phone, speaker.phone);
    }

    /**
     *  this turns the speakers information into a string
     * @return string
     */
    @Override
    public String toString() {
        return "Speaker{" +
                "name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }
}
