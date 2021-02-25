package org.sprite.config;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * ${Description}
 *
 * @author han xiaofeng on 2020/9/10
 */
public interface IShardTableRule extends ITableRule {


    public Collection<String> getDataBaseGroupNames();

    public List<ICalculateRule> getDbCalculateRules();

    public List<ICalculateRule> getTbCalculateRules();
}
