package services;

import providers.Provider;

public class GasBill implements Bill{
    @Override
    public boolean pay(Provider provider, String code) {
        return false;
    }

    @Override
    public void generateBill() {

    }
}
