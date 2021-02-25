package org.sprite.config.sample.distributionrule;

import org.sprite.config.IDistributionRule;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * ${Description}
 *
 * @author han xiaofeng on 2020/9/8
 */
public final class GroovyDistributionRule implements IDistributionRule {

    private final String groovyScript;

    private final Map<String,Collection<String>> distrbution = new HashMap<String,Collection<String>>();

    public GroovyDistributionRule(String groovyScript) {
        this.groovyScript = groovyScript;
    }

    private void init() {
        //TODO:基于groovy脚本进行对象初始化
    }

    @Override
    public Map<String, Collection<String>> getDistrbution() {
        return null;
    }
}
