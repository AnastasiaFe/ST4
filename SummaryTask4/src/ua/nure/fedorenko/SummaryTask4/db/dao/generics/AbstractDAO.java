package ua.nure.fedorenko.SummaryTask4.db.dao.generics;

import ua.nure.fedorenko.SummaryTask4.exception.DBException;

import java.util.List;

public  abstract class AbstractDAO<K, T >{

        public abstract List<T> getAll() throws DBException;

        public abstract T getById(K id) throws DBException;

        public abstract void delete(T entity) throws DBException;

        public abstract void create(T entity) throws DBException;

        public abstract void update(T entity) throws DBException;

}
