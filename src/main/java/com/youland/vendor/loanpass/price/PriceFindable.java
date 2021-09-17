package com.youland.vendor.loanpass.price;

import lombok.NonNull;

import java.util.List;

public interface PriceFindable<T extends IPriceInfo> {
    T findFirst(@NonNull PriceFinderCriteria criteria);
    T findLast(@NonNull PriceFinderCriteria criteria);
    List<T> findAll(@NonNull PriceFinderCriteria criteria);
}
