public class MainVG {
    public static void main(String[] args) {
        Product milk = new Product("Milk", 20, 6);

        Discount genericDiscount = new GenericDiscount(
                product -> product.getQuantity() >= 5,
                ignored -> 15.0,
                new GenericDiscount(
                        product -> product.getName().equals("Milk"),
                        product -> product.getPrice() * 0.10,
                        null));

        System.out.println("Price before discount: " + milk.getPrice());
        genericDiscount.apply(milk);
        System.out.println("Price after discount: " + milk.getPrice());
        System.out.println("Discount applied: " + genericDiscount.getDescription(milk));
    }
}
