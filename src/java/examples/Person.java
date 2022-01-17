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
public class Person {
    
    private String studentID;
    private String person_id;
    private String admin;
    
    public String getStudentID(){
        return this.studentID;
    }
    
    public String getPerson_id() {
        return this.person_id;
    }
    
    public String getAdmin() {
        return this.admin;
    }
    
    public void setAdmin(String admin) {
        this.admin = admin;
    }
    
    public void setPerson_id(String newPerson_id) {
        this.person_id = newPerson_id;
    }
    
    public void setStudentID(String newStudentID) {
        this.studentID = newStudentID;
    }
    
}
