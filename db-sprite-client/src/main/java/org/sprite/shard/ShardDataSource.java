package org.sprite.shard;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sprite.SpriteDataSource;
import org.sprite.config.SpriteConfig;
import org.sprite.log.LogNameSpace;
import org.sprite.log.SpriteLogger;
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

    public DataSource init() {
        super.init0();
        return this;
    }

    public Connection getConnection() throws SQLException {
        return super.getConnection();
    }

    public Connection getConnection(String username, String password) throws SQLException {
        return super.getConnection(username,password);
    }




}
