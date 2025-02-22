package program;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

import model.entities.Product;

public class App {
    public static void main(String[] args) throws ParseException {
        Locale.setDefault(Locale.US);
        Scanner sc = new Scanner(System.in);

        List<Product> products = new ArrayList<>();

        System.out.println("Enter file path: ");
        String sourceFilestr = sc.nextLine();

        File sourceFile = new File(sourceFilestr);
        String sourceFolderStr = sourceFile.getParent();

        boolean sucess = new File (sourceFolderStr + "/out").mkdir();
        String targetFileStr = (sourceFolderStr + "/out/summary.csv");
        
        try (BufferedReader br = new BufferedReader(new FileReader(sourceFilestr))) {
            
            String itemCSV = br.readLine();
            while (itemCSV != null) {
                String[] fildes = itemCSV.split(",");
                String name = fildes[0];
                double price = Double.parseDouble(fildes[1]);
                int quantity = Integer.parseInt(fildes[2]);
                products.add(new Product(name, price, quantity)); 
                itemCSV = br.readLine();
            }

            try (BufferedWriter bw = new BufferedWriter(new FileWriter(targetFileStr))) {
             
                for (Product item : products){
                bw.write(item.getName() + ", " + String.format("%.2f",item.totalPrice()));
                bw.newLine();
            } 
            
            bw.close();
            System.out.println(targetFileStr + " CREATED");
            
            }catch (IOException e) {
                System.out.println("Error writing file" + e.getMessage());
            }

            br.close();
        
            } catch (IOException e) {
            System.out.println("Error writing file" + e.getMessage());
        }
    }
}



