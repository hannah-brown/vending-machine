public enum Product {
    Coke(25), Pepsi(35), Soda(45);

    private final int price;

    Product(int price) {
        this.price = price;
    }

    public int getPrice() {
        return price;
    }

    public static Product convertIntToProduct(int i) {
        switch (i){
            case 1:
                return Coke;
            case 2:
                return Pepsi;
            case 3:
                return Soda;
            default:
                return null;
        }
    }

}
