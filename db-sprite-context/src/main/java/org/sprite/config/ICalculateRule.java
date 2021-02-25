package org.sprite.config;

import org.sprite.model.ShardKey;

import java.util.Collection;

/**
 * 计算规则
 *
 * @author han xiaofeng on 2020/9/7
 */
public interface ICalculateRule {

    Collection<String> shardKeys();

    String eval(ShardKey shardKey);
}
