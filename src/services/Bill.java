package services;

import providers.Provider;

public interface Bill {
    boolean pay(Provider provider, String code);
    void generateBill();
}
