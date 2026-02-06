package Group4.tracer;

import Group4.tracer.model.Product;

public class modelApplication {
    public static void main (String[] args) {
        Product p1 = new Product("P1", "Prod1", "Luxury", "Nike", "desc1");
        System.out.println(p1.getName());
    }
}
