package ro.leje.dao;

/**
 * @author Danut Chindris
 * @since September 15, 2015
 */
public interface BaseDAO {

    Long create(Object entity);

    void update(Object object);

    void delete(Object object);

    <T> T findEntity(long id, Class<T> clazz);
}
