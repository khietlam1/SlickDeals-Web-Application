/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author Darshil Shah
 */
//////////////////////////////////********DARSHIL SHAH******************************////////////////////////////////////////////////////////////////////
@ManagedBean
@RequestScoped
public class Signup {

    private String acctype;
    private String accid;
    private String pw;

    public String getAcctype() {
        return acctype;
    }

    public void setAcctype(String acctype) {
        this.acctype = acctype;
    }

    public String getAccid() {
        return accid;
    }

    public void setAccid(String accid) {
        this.accid = accid;
    }

    public String getPw() {
        return pw;
    }

    public void setPw(String pw) {
        this.pw = pw;
    }


    public String Signup() {
        //load the driver
        try {
            Class.forName("com.mysql.jdbc.Driver");

        } catch (ClassNotFoundException e) {
            return ("Internal Error! Please try again later.");
        }

        
        DataStorage data = new SQL_Database() ;
           
        return data.Signup( accid, pw);

    }

   

}


