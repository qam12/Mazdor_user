package com.example.qamberhaider.mazdor_user;

/**
 * Created by qamber.haider on 4/17/2018.
 */

public class User_Details {


    public String employee_FirstName;
    public String employee_Telephone;
    public String employee_Email;
    public String employee_Password;
    public String id;

    public User_Details() {
    }

    public User_Details(String employee_FirstName, String employee_Telephone, String employee_Email, String employee_Password, String id) {
        this.employee_FirstName = employee_FirstName;
        this.employee_Telephone = employee_Telephone;
        this.employee_Email = employee_Email;
        this.employee_Password = employee_Password;
        this.id = id;
    }

    public String getEmployee_FirstName() {
        return employee_FirstName;
    }

    public String getEmployee_Telephone() {
        return employee_Telephone;
    }

    public String getEmployee_Email() {
        return employee_Email;
    }

    public String getEmployee_Password() {
        return employee_Password;
    }

    public String getId() {
        return id;
    }
}
