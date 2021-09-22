package ru.sibadi.shop.DataAccessObjects;

import ru.sibadi.shop.model.Product;
import ru.sibadi.shop.model.Shop;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ProductDao {
    public void writeInTxtProducts(Shop shop){

        try(FileWriter writer = new FileWriter("Products.txt")) {
            for (Product product : shop.getProducts()) {
                String name = product.getName();
                String price = String.valueOf(product.getPrice());
                writer.write(name + " " + price + System.getProperty("line.separator"));

            }
        }
        catch(IOException ex){

            System.out.println(ex.getMessage());
        }
    }
    public List<Product> readFromTxtProducts(){
        List<Product> products = new ArrayList<>();
        try(Reader readerProducts = new FileReader("Products.txt")) {
            BufferedReader bufferedReader = new BufferedReader(readerProducts);
            while (true) {
                String line = bufferedReader.readLine();
                if (line == null)
                    break;
                String[] splitedLine = line.split(" ");
                Product product = new Product(splitedLine[0], Double.parseDouble(splitedLine[1]));
                products.add(product);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return products;
    }
}
