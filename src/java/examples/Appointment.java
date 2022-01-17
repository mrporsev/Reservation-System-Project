/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package examples;


public class Appointment {
    
    private String appointment_id;
    private String student_id;
    private String group_lesson_id;
    
    public Appointment(String appointment_id, String student_id, String group_lesson_id) {
        this.appointment_id = appointment_id;
        this.student_id = student_id;
        this.group_lesson_id = group_lesson_id;
    }
    
    public String getAppointmentId() {
        return this.appointment_id;
    }
    
    public String getStudentId() {
        return this.student_id;
    }
    
    public String getGroupLessonId() {
        return this.group_lesson_id;
    }
}
