package org.sprite.datasource.internal;

import org.sprite.context.SpriteContext;
import org.sprite.jdbc.proxy.SpriteConnection;

import javax.sql.DataSource;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.util.logging.Logger;

/**
 * ${Description}
 *
 * @author han xiaofeng on 2020/12/2
 */
public class InternalShardDataSource implements DataSource {

    private SpriteContext spriteContext ;

    private PrintWriter logWriter = new PrintWriter(System.out);

    private int seconds;

    public InternalShardDataSource(SpriteContext spriteContext) {
        this.spriteContext = spriteContext;
    }

    @Override
    public Connection getConnection() throws SQLException {
        return new SpriteConnection(spriteContext);
    }

    @Override
    public Connection getConnection(String username, String password) throws SQLException {
        return new SpriteConnection(spriteContext);
    }

    public final <T> T unwrap(final Class<T> iface) throws SQLException {
        if (isWrapperFor(iface)) {
            return (T) this;
        }
        throw new SQLException(String.format("[%s] cannot be unwrapped as [%s]", getClass().getName(), iface.getName()));
    }

    @Override
    public final boolean isWrapperFor(final Class<?> iface) {
        return iface.isInstance(this);
    }

    @Override
    public PrintWriter getLogWriter() throws SQLException {
        return logWriter;
    }

    @Override
    public void setLogWriter(PrintWriter out) throws SQLException {
        logWriter = out;
    }

    @Override
    public void setLoginTimeout(int seconds) throws SQLException {
        this.seconds = seconds;
    }

    @Override
    public int getLoginTimeout() throws SQLException {
        return seconds;
    }

    @Override
    public Logger getParentLogger() throws SQLFeatureNotSupportedException {
        return Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
    }
}
