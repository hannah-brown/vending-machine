public enum Coin {
    PENNY(1),
    NICKEL(5),
    DIME(10),
    QUARTER(25);

    private final int value;

    Coin(int value){
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static Coin[] getAllCoins(){
        return new Coin[]{QUARTER, DIME, NICKEL, PENNY};
    }

    public static Coin convertIntToCoin(int i) {
        switch (i){
            case 1:
                return PENNY;
            case 2:
                return NICKEL;
            case 3:
                return DIME;
            case 4:
                return QUARTER;
            default:
                return null;
        }
    }


}
