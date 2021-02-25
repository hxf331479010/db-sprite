package org.sprite.config.sample.tablerule;

import org.sprite.config.*;

import java.util.*;

/**
 * ${Description}
 *
 * @author han xiaofeng on 2020/9/8
 */
public final class SampleTableRule implements IShardTableRule {

    private final Map<String,IDataBaseGroupRule> dataBaseGroupRules = new HashMap<String,IDataBaseGroupRule>();

    private final List<ICalculateRule> dbCalculateRules = new ArrayList<ICalculateRule>();

    private final List<ICalculateRule> tbCalculateRules = new ArrayList<ICalculateRule>();

    private final Collection<IDistributionRule> distributionRules = new ArrayList<IDistributionRule>();

    public SampleTableRule addDbCalculateRule(ICalculateRule calculateRule) {
        dbCalculateRules.add(calculateRule);
        return this;
    }

    public SampleTableRule addTbCalculateRule(ICalculateRule calculateRule) {
        tbCalculateRules.add(calculateRule);
        return this;
    }

    public SampleTableRule addDataBaseGroupRule(String groupKey,IDataBaseGroupRule dataBaseGroupRule) {
        dataBaseGroupRules.put(groupKey,dataBaseGroupRule);
        return this;
    }

    public SampleTableRule setDistributionRule(IDistributionRule distributionRule) {
        //TODO:
        return this;
    }


    @Override
    public Map<String,IDataBaseGroupRule> getDataBaseGroupRules() {
        return dataBaseGroupRules;
    }

    @Override
    public IDataBaseGroupRule getDataBaseGroupRule(String groupName) {
        return dataBaseGroupRules.get(groupName);
    }

    @Override
    public Collection<String> getDataBaseGroupNames() {
        return dataBaseGroupRules.keySet();
    }

    @Override
    public Map<String, IDistributionRule> getDistribution() {
        return null;
    }

    @Override
    public List<ICalculateRule> getDbCalculateRules() {
        return dbCalculateRules;
    }

    @Override
    public List<ICalculateRule> getTbCalculateRules() {
        return tbCalculateRules;
    }
}
