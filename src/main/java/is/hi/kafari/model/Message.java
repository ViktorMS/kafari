/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package is.hi.kafari.model;

/**
 * @author viktor vjh2@hi.is
 * @date November 2017
 * 
 *  Birtir skilaboð efst á síðu, ef það þarf að koma upplýsingum til notanda
 */
public class Message {
    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
    
    public Message(){}
}
