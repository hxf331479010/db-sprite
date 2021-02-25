package org.sprite.config.sample.tablerule;

import org.sprite.config.IDataBaseGroupRule;
import org.sprite.config.IDistributionRule;
import org.sprite.config.INoShardTableRule;
import org.sprite.config.ITableRule;

import java.util.Map;

/**
 * ${Description}
 *
 * @author han xiaofeng on 2020/9/10
 */
public final class NoShardTableRule implements INoShardTableRule {

    private final String db;

    public NoShardTableRule(String db) {
        this.db = db;
    }

    @Override
    public Map<String, IDistributionRule> getDistribution() {
        return null;
    }

    @Override
    public Map<String, IDataBaseGroupRule> getDataBaseGroupRules() {
        return null;
    }

    @Override
    public IDataBaseGroupRule getDataBaseGroupRule(String groupName) {
        return null;
    }
}
