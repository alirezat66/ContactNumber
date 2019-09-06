package ir.greencode.contactnumberproject.database.object;

public class Contact {
    private String fName;
    private String lName;
    private String phone;
    long id;
    public String getfName() {
        return fName;
    }

    public String getlName() {
        return lName;
    }

    public Contact( long id,String fName, String lName, String phone) {
        this.fName = fName;
        this.lName = lName;
        this.phone = phone;
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public Contact(String fName, String lName, String phone) {
        this.fName = fName;
        this.lName = lName;
        this.phone = phone;
    }
    public String getFullName(){
        return fName+" " + lName;
    }

    public String getPhone() {
        return phone;
    }
    // this function return first charachter of Name;
    public String getShortName(){
        String sN = fName.substring(0,1);
        return sN;
    }
}
