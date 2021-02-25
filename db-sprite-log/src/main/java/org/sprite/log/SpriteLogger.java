package org.sprite.log;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public enum SpriteLogger{

    Instance;

    private Map<LogNameSpace,Logger> cacheLogger = new ConcurrentHashMap();

    public Logger getLogger(LogNameSpace logNameSpace) {

        if (!cacheLogger.containsKey(logNameSpace)) {
            synchronized (this) {
               if (!cacheLogger.containsKey(logNameSpace)) {
                   cacheLogger.put(logNameSpace, LoggerFactory.getLogger(logNameSpace.getDesc()));
               }
            }
        }

        return cacheLogger.get(logNameSpace);
    }


}
