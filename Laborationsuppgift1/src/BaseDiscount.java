public abstract class BaseDiscount implements Discount {
    protected Discount nextDiscount;

    public BaseDiscount(Discount nextDiscount) {
        this.nextDiscount = nextDiscount;
    }

    protected abstract boolean isApplicable(Product product);

    protected abstract double calculateDiscount(Product product);

    @Override
    public double apply(Product product) {
        if (isApplicable(product)) {
            double discount = calculateDiscount(product);
            product.setPrice(product.getPrice() - discount);
        }
        return (nextDiscount != null) ? nextDiscount.apply(product) : product.getPrice();
    }

    @Override
    public String getDescription(Product product) {
        String description = (isApplicable(product)) ? getClass().getSimpleName() + " applied" : "";
        return (nextDiscount != null) ? description + " " + nextDiscount.getDescription(product) : description;
    }
}
