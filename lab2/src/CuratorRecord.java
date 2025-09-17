import java.time.LocalDate;

public class CuratorRecord {
    private String lastName;
    private String firstName;
    private LocalDate birthDate;
    private String phone;
    private String address;

    public CuratorRecord(String lastName, String firstName, LocalDate birthDate, String phone, String address) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.birthDate = birthDate;
        this.phone = phone;
        this.address = address;
    }

    @Override
    public String toString() {
        return "Студент: " + lastName + " " + firstName +
                ", Дата народження: " + birthDate +
                ", Телефон: " + phone +
                ", Адреса: " + address;
    }
}
