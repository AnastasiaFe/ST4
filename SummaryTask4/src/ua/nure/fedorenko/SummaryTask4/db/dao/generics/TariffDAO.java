package ua.nure.fedorenko.SummaryTask4.db.dao.generics;

import ua.nure.fedorenko.SummaryTask4.db.entity.Tariff;
import ua.nure.fedorenko.SummaryTask4.exception.DBException;

import java.util.List;

/**
 * Created by Anastasia on 06.01.2017.
 */
public abstract class TariffDAO extends AbstractDAO<Integer,Tariff> {

public abstract List<Tariff> getAllServiceTariffs(int serviceId) throws DBException;
}
