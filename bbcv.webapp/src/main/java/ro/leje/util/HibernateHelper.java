package ro.leje.util;

import org.hibernate.dialect.Dialect;
import org.hibernate.dialect.MySQLDialect;
import org.hibernate.dialect.Oracle10gDialect;
import org.hibernate.dialect.Oracle8iDialect;
import org.hibernate.dialect.Oracle9iDialect;
import org.hibernate.dialect.SQLServerDialect;

/**
 * A class containing helper methods for various database operations.
 *
 * @author Danut Chindris
 * @since August 6, 2015
 */
public class HibernateHelper {

    private Dialect dialect;

    public String getLpad(String originalString, int finalLength, String padString) {
        if (dialect != null) {
            if (dialect instanceof MySQLDialect || dialect instanceof Oracle8iDialect || dialect instanceof Oracle9iDialect
                    || dialect instanceof Oracle10gDialect) {
                return "lpad('" + originalString + "'," + finalLength + ",'" + padString + "')";
            } else if (dialect instanceof SQLServerDialect) {
                return "replicate('" + padString + "'," + finalLength + "-datalength('" + originalString + "'))+'" + originalString + "'";
            }
        }
        return "";
    }

    public String getConcatenationOperator() {
        if (dialect != null) {
            if (dialect instanceof MySQLDialect || dialect instanceof Oracle8iDialect || dialect instanceof Oracle9iDialect
                    || dialect instanceof Oracle10gDialect) {
                return "||";
            } else if (dialect instanceof SQLServerDialect) {
                return "+";
            }
        }
        return "";
    }

    public Dialect getDialect() {
        return dialect;
    }

    public void setDialect(Dialect dialect) {
        this.dialect = dialect;
    }
}
