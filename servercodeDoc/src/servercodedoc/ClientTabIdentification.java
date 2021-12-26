/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package servercodedoc;

import java.io.PrintWriter;

/**
 *
 * @author lenovo
 */
public class ClientTabIdentification {
    PrintWriter out;
    String clientId;
    public ClientTabIdentification(PrintWriter out, String clientId){
        this.out= out;
        this.clientId= clientId;        
    }
    
}
