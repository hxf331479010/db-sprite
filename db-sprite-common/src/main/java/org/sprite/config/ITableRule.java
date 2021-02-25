package org.sprite.config;

import java.util.Collection;
import java.util.List;
import java.util.Map;


public interface ITableRule  {

    public Map<String,IDistributionRule> getDistribution();

    /**
     * 获得所有读写组的负载规则
     * @return
     */
    public Map<String,IDataBaseGroupRule> getDataBaseGroupRules();

    public IDataBaseGroupRule getDataBaseGroupRule(String groupName);
}
