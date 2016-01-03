package ro.leje.dao;

/**
 * @author Danut Chindris
 * @since September 15, 2015
 */
public interface BaseDAO {

    <T> T findEntity(long id, Class<T> clazz);
}
