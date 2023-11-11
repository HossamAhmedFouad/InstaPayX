package services;

import providers.Provider;

public class ElectricityBill implements Bill{
    @Override
    public boolean pay(Provider provider, String code) {
        return false;
    }

    @Override
    public void generateBill() {

    }
}
