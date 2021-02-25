package org.sprite.client.test;

import org.sprite.config.SpriteConfig;
import org.sprite.config.sample.calculaterule.GroovyCalculateRule;
import org.sprite.config.sample.distributionrule.GroovyDistributionRule;
import org.sprite.config.sample.grouprule.SampleGroupRule;
import org.sprite.config.sample.tablerule.SampleTableRule;
import org.sprite.shard.ShardDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;

/**
 * ${Description}
 *
 * @author han xiaofeng on 2020/11/26
 */
public class InitTest001 {

    public static void main(String[] args) {

        SampleTableRule tableRule = new SampleTableRule();
        tableRule.addDataBaseGroupRule("group1", new SampleGroupRule("master_00:r0w10,slave_00:r10w0"))
                .addDataBaseGroupRule("group2", new SampleGroupRule("master_01:r0w10,slave_01:r10w0"))
                .setDistributionRule(new GroovyDistributionRule("group1:[00-01],group2:[01-02]"))
                .addDbCalculateRule(new GroovyCalculateRule("return $tableName$+\"_\"+#cust_id#.hashCode() % 2"))
                .addTbCalculateRule(new GroovyCalculateRule("return #cust_id#.hashCode() % 2"));

        SpriteConfig config = new SpriteConfig("testDataSource").addTableRule("order_info",tableRule );

        ShardDataSource dd = new ShardDataSource(new HashMap<String, DataSource>(),config,null);
        dd.init();

        try {
            Connection cc = dd.getConnection();

            PreparedStatement ps = cc.prepareStatement("select 1 from dual");

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                System.out.println(rs.getObject(1));
            }

        } catch (Exception e) {

        }


    }
}
