package org.sprite.context;

import org.slf4j.Logger;
import org.sprite.common.SpiExtLoader;
import org.sprite.common.properties.PropertiesKeys;
import org.sprite.config.SpriteConfig;
import org.sprite.event.SpriteEvent;
import org.sprite.log.LogNameSpace;
import org.sprite.log.SpriteLogger;
import org.sprite.runtime.SpriteRuntimeContext;
import org.sprite.spi.BaseSpi;
import org.sprite.type.DataSourceType;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.*;

/**
 * ${Description}
 *
 * @author han xiaofeng on 2020/12/2
 */
public abstract class SpriteContext {

    private Logger logger = SpriteLogger.Instance.getLogger(LogNameSpace.Init);

    protected final Map<String, DataSource> dataSourceMap;
    protected final SpriteConfig shardingRule;
    protected final Properties props;

    protected final DataSourceType type;

    protected EventCentor eventCentor;

    protected MonitorCentor monitorCentor;


    /**
     * @Author: xiaofenghan
     * @Description:  进行初始化内核
     */
    protected void init0() {
        /* 事件通知初始化 */
        logger.info("init event notify ");
        eventCentor = new EventCentor().init();

        /**
         * 执行初始化通知
         */
        eventCentor.invoke(SpriteEvent.preInit,null);

        String monitorSwitch = props.getProperty(PropertiesKeys.MONITOR_DB,"false");

        if ("TRUE".equalsIgnoreCase(monitorSwitch)) {

            logger.info("init monitor centor start ");
            monitorCentor = new MonitorCentor().init();
            logger.info("init monitor centor finished");
        }
    }

    /**
     * @Author: xiaofenghan
     * @Description: 关闭内核
     */
    protected void close0() {
        if (monitorCentor != null ) {
            monitorCentor.close();
        }


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


    /**
     * @Author: xiaofenghan
     * @Description: 事件通知中心，用于基于事件通知相应的处理类
     */
    private class EventCentor {

        private Map<SpriteEvent, TreeSet<BaseSpi>> map = new HashMap<>();

        public EventCentor init() {
            List<BaseSpi> list = SpiExtLoader.loadExt(BaseSpi.class);

            for (BaseSpi ext : list) {
                if (!map.containsKey(ext.getEvent())) {
                    map.put(ext.getEvent(),new TreeSet<BaseSpi>(new Comparator<BaseSpi>() {
                        @Override
                        public int compare(BaseSpi o1, BaseSpi o2) {
                            return new Integer(o1.order()).compareTo(o2.order());
                        }
                    }));
                }
                TreeSet<BaseSpi> treeSet = map.get(ext.getEvent());
                treeSet.add(ext);
            }

            return this;
        }

        public void invoke(SpriteEvent spriteEvent, SpriteRuntimeContext runtimeContext) {

            TreeSet<BaseSpi> treeSet = map.get(spriteEvent);

            for (BaseSpi eventHandler : treeSet) {
                eventHandler.invoke(runtimeContext);
            }

        }

    }

    /**
     * @Author: xiaofenghan
     * @Description: 监控中间，用于监控数据库的可联通性
     */
    private class MonitorCentor {

        public MonitorCentor init() {

            return this;
        }


        public void close() {

        }

    }

}
