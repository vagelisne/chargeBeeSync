/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chargebeesync;

import com.chargebee.Environment;
import com.chargebee.ListResult;
import com.chargebee.models.*;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 *
 * @author Vagelis
 */
public class ChargeBeeSync {
    
    //Method used to create a file. Takes the path to create the file as a parameter
    public void createFile(Path file)throws IOException{
        //Create a new file
        try {
            // Create the empty file with default permissions, etc.
            Files.createFile(file);
        } catch (FileAlreadyExistsException x) {
            //If the file exists, it is deleted and a new file with the same name is created
            Files.delete(file);
            Files.createFile(file);
        } catch (IOException x) {
            // Some other sort of failure, such as permissions.
            System.err.format("createFile error: %s%n", x);
        }
    }
    
    //Method used to write into a file the synced data for the customers. Parameters are the results from ChargeBee and the path to a created file
    public void writeCustomerFile(ListResult result, Path file) throws IOException{
        String id, s = "";
        int i = 1;
        Charset charset = Charset.forName("US-ASCII");
        //Using for to get all the entries returned
        for(ListResult.Entry entry:result){
            createFile(file);
            id = entry.customer().id();
            //Format the way each customer appears
            s = s.concat("Customer No."+i+"\r\n"+Customer.retrieve(id).request()+"\r\n");
            i++;
            
            try (BufferedWriter writer = Files.newBufferedWriter(file, charset)) {
                writer.write(s, 0, s.length());
            } catch (IOException x) {
                System.err.format("IOException: %s%n", x);
            }
        }
    }
    
    //Method used to write into a file the synced data for the subscriptions. Parameters are the results from ChargeBee and the path to a created file
    public void writeSubscriptionsFile(ListResult result, Path file) throws IOException{
        String id, s = "";
        int i = 1;
        //Writing data into file created
        Charset charset = Charset.forName("US-ASCII");
        for(ListResult.Entry entry:result){
            createFile(file);
            id = entry.subscription().id();
            //Format the way each subscription appears
            s = s.concat("Subscription No."+i+"\r\n"+Subscription.retrieve(id).request()+"\r\n");
            i++;
            try (BufferedWriter writer = Files.newBufferedWriter(file, charset)) {
                writer.write(s, 0, s.length());
            } catch (IOException x) {
                System.err.format("IOException: %s%n", x);
            }
        }
    }
    
    //Method used to write into a file the synced data for the cards. Parameters are the results from ChargeBee and the path to a created file
    public void writeCardsFile(ListResult result, Path file) throws IOException{
        String s = "";
        int i = 1;
        createFile(file);
        //Writing data into file created
        Charset charset = Charset.forName("US-ASCII");
        for(ListResult.Entry entry:result){
            //Getting the card entries from the customers
            Card card = entry.card();
            //Check if customer has card
            if(card!=null){
                //Format the way each card appears
                s = s.concat("Card No."+i+"\r\n"+card+"\r\n");
                i++;
                try (BufferedWriter writer = Files.newBufferedWriter(file, charset)) {
                    writer.write(s, 0, s.length());
                } catch (IOException x) {
                    System.err.format("IOException: %s%n", x);
                }
            }
        }
    }
    
    //Method used to write into a file the synced data for the invoices. Parameters are the results from ChargeBee and the path to a created file
    public void writeInvoicesFile(ListResult result, Path file) throws IOException{
        String id, s = "";
        int i = 1;
        //Writing data into file created
        Charset charset = Charset.forName("US-ASCII");
        for(ListResult.Entry entry:result){
            createFile(file);
            id = entry.invoice().id();
            //Format the way each invoice appears
            s = s.concat("Invoice No."+i+"\r\n"+Invoice.retrieve(id).request()+"\r\n");
            i++;
            
            try (BufferedWriter writer = Files.newBufferedWriter(file, charset)) {
                writer.write(s, 0, s.length());
            } catch (IOException x) {
                System.err.format("IOException: %s%n", x);
            }
        }
    }
    
    //Method used to write into a file the synced data for the credit notes. Parameters are the results from ChargeBee and the path to a created file
    public void writeCreditNotesFile(ListResult result, Path file) throws IOException{
        String id, s = "";
        int i = 1;
        //Writing data into file created
        Charset charset = Charset.forName("US-ASCII");
        for(ListResult.Entry entry:result){
            createFile(file);
            id = entry.creditNote().id();
            
            s = s.concat("Credit Note No."+i+"\r\n"+CreditNote.retrieve(id).request()+"\r\n");
            i++;
            
            try (BufferedWriter writer = Files.newBufferedWriter(file, charset)) {
                writer.write(s, 0, s.length());
            } catch (IOException x) {
                System.err.format("IOException: %s%n", x);
            }
        }
    }
    
    //Method used to write into a file the synced data for the orders. Parameters are the results from ChargeBee and the path to a created file
    public void writeOrdersFile(ListResult result, Path file) throws IOException{
        String id, s = "";
        int i = 1;
        //Writing data into file created
        Charset charset = Charset.forName("US-ASCII");
        for(ListResult.Entry entry:result){
            createFile(file);
            id = entry.order().id();
            
            s = s.concat("Order No."+i+"\r\n"+Order.retrieve(id).request()+"\r\n");
            i++;
            
            try (BufferedWriter writer = Files.newBufferedWriter(file, charset)) {
                writer.write(s, 0, s.length());
            } catch (IOException x) {
                System.err.format("IOException: %s%n", x);
            }
        }
    }
    
    //Method used to write into a file the synced data for the orders. Parameters are the results from ChargeBee and the path to a created file
    public void writeTransactionsFile(ListResult result, Path file) throws IOException{
        String id, s = "";
        int i = 1;
        //Writing data into file created
        Charset charset = Charset.forName("US-ASCII");
        for(ListResult.Entry entry:result){
            createFile(file);
            id = entry.transaction().id();
            
            s = s.concat("Transaction No."+i+"\r\n"+Transaction.retrieve(id).request()+"\r\n");
            i++;
            
            try (BufferedWriter writer = Files.newBufferedWriter(file, charset)) {
                writer.write(s, 0, s.length());
            } catch (IOException x) {
                System.err.format("IOException: %s%n", x);
            }
        }
    }
    
    //Method used to write into a file the synced data for the plans. Parameters are the results from ChargeBee and the path to a created file
    public void writePlansFile(ListResult result, Path file) throws IOException{
        String id, s = "";
        int i = 1;
        //Writing data into file created
        Charset charset = Charset.forName("US-ASCII");
        for(ListResult.Entry entry:result){
            createFile(file);
            id = entry.plan().id();
            
            s = s.concat("Plan No."+i+"\r\n"+Plan.retrieve(id).request()+"\r\n");
            i++;
            
            try (BufferedWriter writer = Files.newBufferedWriter(file, charset)) {
                writer.write(s, 0, s.length());
            } catch (IOException x) {
                System.err.format("IOException: %s%n", x);
            }
        }
    }
    
    //Method used to write into a file the synced data for the addons. Parameters are the results from ChargeBee and the path to a created file
    public void writeAddonsFile(ListResult result, Path file) throws IOException{
        String id, s = "";
        int i = 1;
        //Writing data into file created
        Charset charset = Charset.forName("US-ASCII");
        for(ListResult.Entry entry:result){
            createFile(file);
            id = entry.addon().id();
            
            s = s.concat("Addon No."+i+"\r\n"+Addon.retrieve(id).request()+"\r\n");
            i++;
            
            try (BufferedWriter writer = Files.newBufferedWriter(file, charset)) {
                writer.write(s, 0, s.length());
            } catch (IOException x) {
                System.err.format("IOException: %s%n", x);
            }
        }
    }
    
    //Method used to write into a file the synced data for the coupons. Parameters are the results from ChargeBee and the path to a created file
    public void writeCouponsFile(ListResult result, Path file) throws IOException{
        String id, s = "";
        int i = 1;
        //Writing data into file created
        Charset charset = Charset.forName("US-ASCII");
        for(ListResult.Entry entry:result){
            createFile(file);
            id = entry.coupon().id();
            
            s = s.concat("Coupon No."+i+"\r\n"+Coupon.retrieve(id).request()+"\r\n");
            i++;
            
            try (BufferedWriter writer = Files.newBufferedWriter(file, charset)) {
                writer.write(s, 0, s.length());
            } catch (IOException x) {
                System.err.format("IOException: %s%n", x);
            }
        }
    }
    
    //Method used to write into a file the synced data for the addresses. Parameters are the results from ChargeBee and the path to a created file
    public void writeAddressesFile(ListResult result, Path file) throws IOException{
        String s = "";
        int i = 1;
        //Writing data into file created
        Charset charset = Charset.forName("US-ASCII");
        for(ListResult.Entry entry:result){
            createFile(file);
         
            s = s.concat("Address No."+i+"\r\n"+entry.subscription().shippingAddress()+"\r\n");
            i++;
            
            try (BufferedWriter writer = Files.newBufferedWriter(file, charset)) {
                writer.write(s, 0, s.length());
            } catch (IOException x) {
                System.err.format("IOException: %s%n", x);
            }
        }
    }
    
    public void connectToChargeBee(){
        //Connect with ChargeBee
        Environment.configure("vagelisne-test","test_FKLTLvBbqQVFfPkE2DRShzD2clYTU1bR");
    }
}
