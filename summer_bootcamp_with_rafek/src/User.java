
public abstract class User {
    private static int counter=1;
    private int userId;
    private LocalDate dateOfBirth;
    private String name;
    private String lastname;
    private String pesel;
    private String clientNumber;
    private String email;


    public User(LocalDate dateOfBirth, String name, String lastname, String pesel, String email) {
        this.name = name;
        this.lastname = lastname;
        this.dateOfBirth = dateOfBirth;
        this.email = email;
        this.pesel = pesel;
        this.userId = counter
        counter++;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getPesel() {
        return pesel;
    }

    public void setPesel(String pesel) {
        this.pesel = pesel;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public User(LocalDate dateOfBirth, String name, String lastname, String pesel, String email) {
        this.name = name;
        this.lastname = lastname;
        this.dateOfBirth = dateOfBirth;
        this.email = email;
        this.pesel = pesel;
        this.userId = counter
        counter++;
    }




}
