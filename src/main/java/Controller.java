public class Controller {
    private final View view;
    private final Wallet wallet;

    Controller(View view, Wallet wallet){
        this.view = view;
        this.wallet = wallet;
    }

    public void run(){
        boolean keepGoing =true;
        int mainMenuSelection;

        while (keepGoing){
            mainMenuSelection = getMenuSelection();
            switch (mainMenuSelection){
                case 0:
                    keepGoing = false;
                    break;
                case 1:
                    viewBalance();
                    break;
                case 2:
                    insertChange();
                    break;
                case 3:
                    selectItem();
                    break;
                case 4:
                    getChange();
                    break;
                case 5:
                    staffReset();
                    break;
                default:
                    unknownCommand();
            }

        }
    }

    private int getMenuSelection(){
        return view.printMainMenuAndGetSelection();
    }

    private void unsupported(){
        view.displayUnsupportedCommand();
    }

    private void unknownCommand(){
        view.displayUnknownCommand();
    }

    private void viewBalance(){
        view.displayBalance(wallet.getBalance());
    }

    private void insertChange(){
        int selection = view.printInsertMenuAndGetSelection();
        if(selection != 0){
            wallet.addCoin(Coin.convertIntToCoin(selection));
            view.displayBalance(wallet.getBalance());
        }
    }

    private void selectItem(){
        int selection = view.printProductMenuAndGetSelection();

        if(selection != 0){
            Product item = Product.convertIntToProduct(selection);
            if(wallet.isPurchasable(item)){
                wallet.buyProductAndUpdateBalance(item);
                view.printSuccessfulPurchase(wallet.getBalance(), item);
            }else{
                view.printUnsuccessfulPurchase();
            }
        }

    }

    private void getChange(){
        view.printChangeMessage(wallet.getBalance(), CoinManager.calculateChange(wallet.getBalance()));
        wallet.clearBalance();
    }

    private void staffReset(){
        if (view.printPasscodeRequestAndVerifyAccess()){
            int selection = view.printTotalIncomeMenuAndGetSelection(wallet.getTotalIncome());
            if(selection == 1){
                view.printChangeMessage(wallet.getTotalIncome(), CoinManager.calculateChange(wallet.getTotalIncome()));
                wallet.clearTotalIncome();
            }
        }else {
            view.printAccessDenied();
        }
    }
}
