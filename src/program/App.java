package program;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;


import model.entities.Product;

public class App {
    public static void main(String[] args) throws Exception {
        Locale.setDefault(Locale.US);
        List<Product> products = new ArrayList<>();

        String order = ("src/Order.csv");
        boolean sucess = new File ("src/out").mkdir();
        String summary = "src/out/Summary.csv";
        
        try (BufferedReader br = new BufferedReader(new FileReader(order))) {
            BufferedWriter bw = new BufferedWriter(new FileWriter(summary));
            String line = br.readLine();
            
            while (line != null) {
                String[] lines = line.split(",");
                String name = lines[0];
                double price = Double.valueOf(lines[1]);
                int amount = Integer.valueOf(lines[2]);
                products.add(new Product(name, price, amount)); 
                line = br.readLine();
            }
 
            for ( int i = 0; i < 4; i++){
                bw.write(products.get(i).getName());
                bw.write(",");
                bw.write(Double.toString(products.get(i).totalPrice()));
                bw.newLine();
            }

            bw.close();
            br.close();

        } catch (IOException e) {
            System.out.println("ERROR " + e.getMessage());
        }
    }
}
