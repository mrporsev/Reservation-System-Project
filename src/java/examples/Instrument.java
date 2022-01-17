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
public class Instrument {
    
    private String instrument_id;
    private String type_of_instrument;
    private String instrument_rental_per_month;
    private boolean is_rented;
    
    public Instrument(String id, String type, String rental, boolean isRented) {
        this.instrument_id = id;
        this.type_of_instrument = type;
        this.instrument_rental_per_month = rental;
        this.is_rented = isRented;
    }
    
    public String getType() {
        return this.type_of_instrument;
    }
    
    
    public String getRental() {
        return this.instrument_rental_per_month;
    }
    
    public boolean getRentalStatus() {
        return this.is_rented;
    }
    
    public String getInstrumentId() {
        return this.instrument_id;
    }
}
