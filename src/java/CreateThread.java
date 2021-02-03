/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ManagedBean;

/**
 *
 * @author Pavani
 */
@ManagedBean
@RequestScoped
public class CreateThread {

    
    
    private String threadid;
     private String parentid;
    private String title;
    private String content;
    private String author;
    private LocalDateTime timestamp;
    private int rating;
    private String status;
    DataStorage data = new SQL_Database();

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }
        
    public String getAuthor() {
        return author;
    }
    
     public LocalDateTime getTimestamp() {
        return timestamp;
    }
    
    public String getThreadid() {
        return threadid;
    }
    
    public int getRating() {
        return rating;
    }
    
    public String getStatus() {
        return status;
    }
    
    public String getParentid() {
        return parentid;
    }
    
    public void setTitle(String title) {
        this.title = title;
    }
    
     public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setThreadid(String threadid) {
        this.threadid = threadid;
    }
    
    public void setParentid(String parentid) {
        this.parentid = parentid;
    }
    
    public void setStatus(String status) {
        this.status = status;
    }
    
    public void setRating(int rating) {
        this.rating = rating;
    }
    
     public String createThread(String accid)
    {
    
        return data.createThread(parentid, title,content,accid,timestamp,rating,status);
         
    }
     
     public String createReply(String accid, String parentid)
    {
  
        return data.createReply(parentid, title,content,accid);
         
    }
    
}
