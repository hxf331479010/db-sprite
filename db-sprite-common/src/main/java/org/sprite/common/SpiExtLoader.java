package org.sprite.common;

import org.sprite.log.LogNameSpace;
import org.sprite.log.SpriteLogger;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ServiceLoader;

/**
 * create by han xiaofeng on 2021/2/26
 */
public class SpiExtLoader {

    public static <T> List<T> loadExt(Class<T> clazz) {

        ArrayList<T> result = new ArrayList<>();

        try {

            ServiceLoader<T> load = ServiceLoader.load(clazz, Thread.currentThread().getContextClassLoader());

            Iterator<T> iterator = load.iterator();

            while(iterator.hasNext()) {

                result.add(iterator.next());

            }

        } catch (Exception e) {

            SpriteLogger.Instance.getLogger(LogNameSpace.Init).error("spi ext error , spi interface class is :" + clazz.getName(),e);

        }

        return result;

    }
}
