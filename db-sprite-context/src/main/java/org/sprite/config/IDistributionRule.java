package org.sprite.config;

import java.util.Collection;
import java.util.Map;

/**
 * 数据表分布的接口
 *
 * @author han xiaofeng on 2020/9/7
 */
public interface IDistributionRule {

    public Map<String, Collection<String>> getDistrbution();
}
