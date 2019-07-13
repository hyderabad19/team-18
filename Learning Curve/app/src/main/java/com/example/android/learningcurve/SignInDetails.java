package com.example.android.learningcurve;

public class SignInDetails {
    String name;
    String password;
    String temail;
    String institute;

    public SignInDetails()
    {

    }
    public SignInDetails(String name, String password, String temail, String institute){
        this.name=name;
        this.password=password;
        this.temail=temail;;
        this.institute=institute;

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getTemail() {
        return temail;
    }

    public void setTemail(String temail) {
        this.temail = temail;
    }

    public String getInstitute() {
        return institute;
    }

    public void setInstitute(String institute) {
        this.institute = institute;
    }
}
