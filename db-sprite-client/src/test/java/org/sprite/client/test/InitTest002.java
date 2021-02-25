package org.sprite.client.test;

import org.sprite.config.sample.grouprule.SampleGroupRule;

/**
 * ${Description}
 *
 * @author han xiaofeng on 2020/11/30
 */
public class InitTest002 {

    public static void main(String[] args) {
        SampleGroupRule sampleGroupRule = new SampleGroupRule("master_00:r1w10,slave_00:r1w0");
        System.out.println(sampleGroupRule.getRead());
    }
}
