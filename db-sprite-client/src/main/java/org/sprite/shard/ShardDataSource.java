package org.sprite.shard;

import org.sprite.SpriteDataSource;
import org.sprite.config.SpriteConfig;
import org.sprite.type.DataSourceType;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Map;
import java.util.Properties;

public class ShardDataSource extends SpriteDataSource {

    public ShardDataSource (final Map<String, DataSource> dataSourceMap, final SpriteConfig shardingRule, final Properties props) {
        super(dataSourceMap,shardingRule,props, DataSourceType.SHARD);
    }

    public void init() {
        super.init();
    }

    public Connection getConnection() throws SQLException {
        return super.getConnection();
    }

    public Connection getConnection(String username, String password) throws SQLException {
        return super.getConnection(username,password);
    }




}
