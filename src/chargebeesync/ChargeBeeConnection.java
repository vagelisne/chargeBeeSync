/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chargebeesync;

import java.io.IOException;
import com.chargebee.*;
import com.chargebee.models.*;
import java.io.BufferedWriter;
import java.nio.charset.Charset;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 *
 * @author Vagelis
 */
public class ChargeBeeConnection {
    public static void main(String args[]) throws IOException{
        //Connect with ChargeBee
        Environment.configure("vagelisne-test","test_FKLTLvBbqQVFfPkE2DRShzD2clYTU1bR");
        //Get all the Customers
        ListResult result = Customer.list().request();
        System.out.println(result+" ");
        //A way to get the id of each customer
        String id = "";
        int i = 0;
        for(ListResult.Entry entry:result){
            id = entry.customer().id();
            System.out.println(Customer.retrieve(id).request()+"\n"+"--------------------------------------------------");
        }
    
        //Create a new file(File stored where the project is)
        Path file = Paths.get("Sync1.txt");
        try {
            // Create the empty file with default permissions, etc.
            Files.createFile(file);
        } catch (FileAlreadyExistsException x) {
            System.err.format("file named %s" +
                " already exists%n", file);
        } catch (IOException x) {
            // Some other sort of failure, such as permissions.
            System.err.format("createFile error: %s%n", x);
        }
        //Writing data into file created
        Charset charset = Charset.forName("US-ASCII");
        String s = result.toString();
        try (BufferedWriter writer = Files.newBufferedWriter(file, charset)) {
            writer.write(s, 0, s.length());
        } catch (IOException x) {
            System.err.format("IOException: %s%n", x);
        }
    }
}