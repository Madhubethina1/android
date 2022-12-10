package uk.ac.tees.aad.W9507024;

public class Friend {

    String name;

    String phonenumber;

    public Friend(String name, String phonenumber) {
        this.name = name;
        this.phonenumber = phonenumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    @Override
    public String toString() {
        return this.name+" "+this.phonenumber;
    }

    public Friend() {
    }
}
