package org.sprite.model;

import java.util.Map;

/**
 * 这是封装一次计算所使用到的值
 *
 * @author han xiaofeng on 2020/9/7
 */
public final class ShardKey {

    Map<String,Comparable> shardValue;

    public Map<String,Comparable> getShardValue(){
        return shardValue;
    }

}
