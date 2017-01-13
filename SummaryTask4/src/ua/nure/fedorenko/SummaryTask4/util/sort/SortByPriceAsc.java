package ua.nure.fedorenko.SummaryTask4.util.sort;

import ua.nure.fedorenko.SummaryTask4.db.entity.Tariff;

import java.util.Comparator;

public class SortByPriceAsc implements Comparator<Tariff> {
    @Override
    public int compare(Tariff o1, Tariff o2) {
        double price1 = o1.getPrice();
        double price2 = o2.getPrice();
        return price1 > price2 ? 1 : price1 < price2 ? -1 : 0;
    }
}
