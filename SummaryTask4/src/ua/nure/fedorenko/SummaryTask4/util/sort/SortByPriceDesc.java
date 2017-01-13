package ua.nure.fedorenko.SummaryTask4.util.sort;

import ua.nure.fedorenko.SummaryTask4.db.entity.Tariff;

import java.util.Comparator;

/**
 * Created by Anastasia on 09.01.2017.
 */
public class SortByPriceDesc implements Comparator<Tariff> {
    @Override
    public int compare(Tariff o1, Tariff o2) {
        double price1 = o1.getPrice();
        double price2 = o2.getPrice();
        return price2 > price1 ? 1 : price2 < price1 ? -1 : 0;

    }
}
