package com.youland.vendor.loanpass.price;

/**
 * Define pricing related interfaces
 */
public interface IPriceInfo extends Comparable<IPriceInfo>{
    boolean priceEnabled();
    Double getLockPeriod();
    Double getPrice();

    /**
     * The higher the price, the high the profit
     * From the lowest to highest price
     * @param o
     * @return
     */
    @Override
    default int compareTo(IPriceInfo o) {
        if (this.getPrice() == null && o.getPrice() == null)
            return 0;
        else if (this.getPrice() == null)
            return -1;
        else if (o.getPrice() == null)
            return 1;
        return (int) (this.getPrice() - o.getPrice());
    }
}
