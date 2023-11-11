package services;

import providers.Provider;

public class WaterBill implements Bill{
    @Override
    public boolean pay(Provider provider, String code) {
        return false;
    }

    @Override
    public void generateBill() {

    }
}
