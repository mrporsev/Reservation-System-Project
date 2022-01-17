package examples;

import java.io.Serializable;

public class Links implements Serializable {
    
    private String instrument;
    private String link;
    private String gif;
    
    public Links(){}
    
    public Links(String instrument, String link, String gif){
        this.instrument = instrument;
        this.link = link;
        this.gif = gif;
    }
    
    public void setInstrument(String instrument){
        this.instrument = instrument;
    }
    
    public void setLink(String link){
        this.link = link;
    }
    
    public void setGif(String id){
        this.gif = id;
    }
    
    public String getInstrument(){
        return this.instrument;
    }
    
    public String getLink(){
        return this.link;
    }
    
    public String getGif(){
        return this.gif;
    }
    
}
