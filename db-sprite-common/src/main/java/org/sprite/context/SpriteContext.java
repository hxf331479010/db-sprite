package org.sprite.context;

import org.sprite.config.SpriteConfig;
import org.sprite.type.DataSourceType;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Map;
import java.util.Properties;

/**
 * ${Description}
 *
 * @author han xiaofeng on 2020/12/2
 */
public abstract class SpriteContext {

    protected final Map<String, DataSource> dataSourceMap;
    protected final SpriteConfig shardingRule;
    protected final Properties props;

    protected final DataSourceType type;

    protected void init() {

    }

    public DataSourceType getType() {
        return type;
    }

    public SpriteContext(final Map<String, DataSource> dataSourceMap , final SpriteConfig shardingRule , final Properties props, final DataSourceType type) {
        this.dataSourceMap = dataSourceMap;
        this.shardingRule = shardingRule;
        this.props = props;
        this.type = type;
    }

    public String getProperty(String key) {
        return props.getProperty(key);
    }

    public String getProperty(String key,String defaultValue) {
        return props.getProperty(key,defaultValue);
    }

    public DataSource getDataSource(String groupId) {
        return dataSourceMap.get(groupId);
    }

}
