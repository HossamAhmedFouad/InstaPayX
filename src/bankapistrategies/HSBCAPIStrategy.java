package bankapistrategies;

public class HSBCAPIStrategy implements BankAPIStrategy{
    String accountNumber;
    String phone ;
    String baseUrl = "http://localhost:8001/api/hsbc/bank-accounts";
    StrategyUtil util;
    public HSBCAPIStrategy(String accountNumber,String phone){
        this.accountNumber = accountNumber;
        this.phone = phone;
        util = new StrategyUtil(accountNumber,phone,baseUrl);
    }
    @Override
    public boolean verify() {
        return util.verify();
    }

    @Override
    public boolean transferTo(String accountNumber, double amount,String targetUrl) {
        return util.transferTo(accountNumber,amount,targetUrl);
    }

    @Override
    public double getBalance() {
        return  util.getBalance();
    }
}
