package org.sprite;


import org.sprite.config.SpriteConfig;
import org.sprite.context.SpriteContext;
import org.sprite.datasource.internal.InternalDataSourceFactory;
import org.sprite.type.DataSourceType;

import javax.sql.DataSource;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.util.Map;
import java.util.Properties;
import java.util.logging.Logger;

public abstract class SpriteDataSource extends SpriteContext implements DataSource {

    private DataSource dataSource;

    public Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }
    public Connection getConnection(String username, String password) throws SQLException {
        return dataSource.getConnection(username,password);
    }

    public void init(){
        super.init();
        dataSource = InternalDataSourceFactory.createDataSource(this);
    }

    public SpriteDataSource (final Map<String, DataSource> dataSourceMap, final SpriteConfig shardingRule, final Properties props , final DataSourceType type) {
        super(dataSourceMap,shardingRule,props,type);
    }

    public <T> T unwrap(Class<T> iface) throws SQLException {
        return dataSource.unwrap(iface);
    }

    public boolean isWrapperFor(Class<?> iface) throws SQLException {
        return dataSource.isWrapperFor(iface);
    }

    public PrintWriter getLogWriter() throws SQLException {
        return dataSource.getLogWriter();
    }

    public void setLogWriter(PrintWriter out) throws SQLException {
        dataSource.setLogWriter(out);
    }

    public void setLoginTimeout(int seconds) throws SQLException {
        dataSource.setLoginTimeout(seconds);
    }

    public int getLoginTimeout() throws SQLException {
        return dataSource.getLoginTimeout();
    }

    public Logger getParentLogger() throws SQLFeatureNotSupportedException {
        return Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
    }

}
