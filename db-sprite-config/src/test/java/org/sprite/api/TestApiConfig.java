package org.sprite.api;

import org.sprite.config.IDataBaseGroupRule;
import org.sprite.config.SpriteConfig;
import org.sprite.config.sample.calculaterule.GroovyCalculateRule;
import org.sprite.config.sample.tablerule.SampleTableRule;

/**
 * ${Description}
 *
 * @author han xiaofeng on 2020/9/8
 */

public class TestApiConfig {

    public static void main(String[] args) {

        SampleTableRule tableRule = new SampleTableRule();
        tableRule.addDbCalculateRule(new GroovyCalculateRule(""))
                .addTbCalculateRule(new GroovyCalculateRule(""))
                .addDataBaseGroupRule("group1", new IDataBaseGroupRule() {
                    @Override
                    public String getWrite() {
                        return null;
                    }

                    @Override
                    public String getRead() {
                        return null;
                    }
                });

        SpriteConfig config = new SpriteConfig("myDs").addDefaultTableRule(tableRule);

    }

}
