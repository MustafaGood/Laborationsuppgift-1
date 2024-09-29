public class Main {
    public static void main(String[] args) {
        Product milk = new Product("Milk", 20, 6);
        Product bread = new Product("Bread", 25, 2);

        Discount discountChain = new FridayDiscount(
                new MilkDiscount(
                        new QuantityDiscount(null)));

        System.out.println("Price before discount: " + milk.getPrice());
        discountChain.apply(milk);
        System.out.println("Price after discount: " + milk.getPrice());
        System.out.println("Discount applied: " + discountChain.getDescription(milk));

        System.out.println("Price before discount: " + bread.getPrice());
        discountChain.apply(bread);
        System.out.println("Price after discount: " + bread.getPrice());
        String breadDiscountDescription = discountChain.getDescription(bread).isEmpty()
                ? "No discount applied"
                : discountChain.getDescription(bread);
        System.out.println("Discount applied: " + breadDiscountDescription);
    }
}
