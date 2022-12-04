package guru.qa.model;

import java.util.ArrayList;

public class User {
    public String firstName;
    public String lastName;
    public String gender;
    public int age;
    public Address address;
    public ArrayList<String> phoneNumbers;

    public static class Address {
        public String streetAddress;
        public String city;
        public String state;
    }
}
