/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.text.DateFormat;
import java.util.Date;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;

/**
 *
 * @author Pavani
 */
@Named(value = "dateAndTime")
@RequestScoped
public class DateTime {

    /**
     * Creates a new instance of DateTime
     */
    private String time;
    
    public String getTime(){
        return DateFormat.getTimeInstance(DateFormat.LONG).format(new Date());
    }
    
}
