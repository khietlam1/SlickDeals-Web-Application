/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import javax.inject.Named;
import javax.enterprise.context.RequestScoped;

@Named(value = "user")
@RequestScoped
public class User extends Account {

    public User(String accid, String pw) {
        super(accid, pw);
    }

  

    @Override
    public String updateThread(String threadid, String status) {
        throw new UnsupportedOperationException("**EDITOR ONLY**DO NOT WRITE CODES HERE");
    }

}
