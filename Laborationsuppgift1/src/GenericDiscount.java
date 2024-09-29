import java.util.function.Predicate;
import java.util.function.Function;

public class GenericDiscount implements Discount {
    private final Predicate<Product> isApplicable;  // Lägg till final
    private final Function<Product, Double> calculateDiscount;  // Lägg till final
    private final Discount nextDiscount;  // Lägg till final

    public GenericDiscount(Predicate<Product> isApplicable, Function<Product, Double> calculateDiscount, Discount nextDiscount) {
        this.isApplicable = isApplicable;
        this.calculateDiscount = calculateDiscount;
        this.nextDiscount = nextDiscount;
    }

    @Override
    public double apply(Product product) {
        if (isApplicable.test(product)) {
            double discount = calculateDiscount.apply(product);
            product.setPrice(product.getPrice() - discount);
        }
        return (nextDiscount != null) ? nextDiscount.apply(product) : product.getPrice();
    }

    @Override
    public String getDescription(Product product) {
        return (isApplicable.test(product)) ? "Generic discount applied" : "";
    }
}
