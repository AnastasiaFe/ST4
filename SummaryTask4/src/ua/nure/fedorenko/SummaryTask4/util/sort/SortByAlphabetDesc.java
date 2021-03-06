package ua.nure.fedorenko.SummaryTask4.util.sort;

import ua.nure.fedorenko.SummaryTask4.db.entity.Tariff;

import java.util.Comparator;

/**
 * Created by Anastasia on 09.01.2017.
 */
public class SortByAlphabetDesc implements Comparator<Tariff> {
    @Override
    public int compare(Tariff o1, Tariff o2) {
       return o2.getName().compareTo(o1.getName());
    }
}
