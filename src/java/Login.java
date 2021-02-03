/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import javax.faces.context.FacesContext;

/**
 *
 * @author kitsune
 */
@Named(value = "login")
@SessionScoped
public class Login implements Serializable {

    private String accid;
    private String pw;
    private String type;
    private Account current_session;
   
    public void setAccid(String accid) {
        this.accid = accid;
    }

    public void setPw(String pw) {
        this.pw = pw;
    }

    public String getAccid() {
        return accid;
    }

    public String getPw() {
        return pw;
    }

    public Account getCurrent_session() {
        return current_session;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
    
    public String login() {

//load the Driver
        try {
            Class.forName("com.mysql.jdbc.Driver");

        } catch (Exception e) {
            e.printStackTrace();
            //return to internalError.xhtml
            return ("internalError");
        }

        DataStorage data = new SQL_Database();
        String type = data.getType(accid);
         setType(type);
        String fileName = data.login(accid, pw);

        if (fileName.equals("welcome")) {

            if (type.equals("user")) {
                current_session = new User(accid, pw);
              
            } else if (type.equals("editor")) {
                current_session = new Editor(accid, pw);
                
            } else {
                return "internalError";
            }
              return "welcome";
        
        } else {
            return fileName;
        }

    }

}
