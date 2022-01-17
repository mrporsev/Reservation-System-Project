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
public class Rental {

    private String rental_id;
    private String student_id;
    private String rental_start_date;
    private String rental_end_date;
    private String instrument_id;
    private String instrument_name;

    public Rental(String rID, String sID, String startDate, String endDate, String iID, String iName) {

        this.rental_id = rID;
        this.student_id = sID;
        this.rental_start_date = startDate;
        this.rental_end_date = endDate;
        this.instrument_id = iID;
        this.instrument_name = iName;

    }

    public String getRentalId() {
        return this.rental_id;
    }

    public String getStudentId() {
        return this.student_id;
    }

    public String getStartDate() {
        return this.rental_start_date;
    }
    
    public String getEndDate() {
        return this.rental_end_date;
    }
    
    public String getInstrumentId() {
        return this.instrument_id;
    }
    
    public String getInstrumentName() {
        return this.instrument_name;
    }

}
