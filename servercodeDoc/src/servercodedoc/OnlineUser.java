/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servercodedoc;

import java.io.PrintWriter;

/**
 *
 * @author lenovo
 */
public class OnlineUser {
    PrintWriter out;
    int k;
    String port;
    public OnlineUser(PrintWriter out, int k,String port){
        this.out= out;
        this.k= k;
        this.port=port;
    }
}
