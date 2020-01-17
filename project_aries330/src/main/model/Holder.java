package model;

import java.util.Objects;

public class Holder {

    private String fullName;
    private int age;
    private String dateOfBirth;
    private String address;
    private int phoneNumber;

    //EFFECTS: a constructor that initializes the Holder object
    public Holder(String name, int age, String dateOfBirth, String address, int phoneNumber) {
        this.fullName = name;
        if (age >= 18) {
            this.age = age;
        }
        this.dateOfBirth = dateOfBirth;
        this.address = address;
        this.phoneNumber = phoneNumber;
    }

    //EFFECTS: returns the full name in String
    public String getFullName() {
        return fullName;
    }

    //EFFECTS: returns the age in int
    public int getAge() {
        return age;
    }

    //EFFECTS: returns the date of birth in String
    public String getDateOfBirth() {
        return dateOfBirth;
    }

    //EFFECTS: returns the address of the holder in String
    public String getAddress() {
        return address;
    }

    //EFFECTS: returns the phone number in int
    public int getPhoneNumber() {
        return phoneNumber;
    }

    @Override
    public int hashCode() {
        return Objects.hash(getFullName(), getAge(), getDateOfBirth(), getAddress());
    }

}