/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package examples;

/**
 *
 * @author porse
 */
public class Personal_information {

    private String person_id;
    private String age;
    private String first_name;
    private String last_name;
    private String social_security_number;
    private String email;
    private String phone_number;
    private String zip_code;
    private String street_name;
    private String city_name;
    
    public Personal_information(String pID, String a, String f, String l, String ssn, String e, String p, String z, String s, String c) {
        this.person_id = pID;
        this.age = a;
        this.first_name = f;
        this.last_name = l;
        this.social_security_number = ssn;
        this.email = e;
        this.phone_number = p;
        this.zip_code = z;
        this.street_name = s;
        this.city_name = c;
    }
    
    public String getPerson_id(){
        return this.person_id;
    }
    
    public String getAge() {
        return this.age;
    }
    
    public String getFirst_name() {
        return this.first_name;
    }
    
    public String getLast_name() {
        return this.last_name;
    }
    
    public String getSocial_security_number() {
        return this.social_security_number;
    }
    
    public String getEmail() {
        return this.email;
    }
    
    public String getPhone_number() {
        return this.phone_number;
    }
    
    public String getZip_code() {
        return this.zip_code;
    }
    
    public String getStreet_name() {
        return this.street_name;
    }
    
    public String getCity_name() {
        return this.city_name;
    }
    
}
