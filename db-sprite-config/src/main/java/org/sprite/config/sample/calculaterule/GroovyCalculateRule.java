package org.sprite.config.sample.calculaterule;

import groovy.lang.Closure;
import groovy.lang.GroovyClassLoader;
import groovy.lang.GroovyShell;
import groovy.lang.Script;
import groovy.util.Expando;
import org.sprite.config.ICalculateRule;
import org.sprite.model.ShardKey;

import java.security.AccessController;
import java.security.PrivilegedAction;
import java.util.Collection;

/**
 * ${Description}
 *
 * @author han xiaofeng on 2020/9/8
 */
public final class GroovyCalculateRule implements ICalculateRule {

    private final String groovyScript;


    public GroovyCalculateRule(String groovyScript) {
        this.groovyScript = groovyScript;
//        groovy
        init();
    }

    private String execute() {
        return null;
    }

    private void init() {
        GroovyClassLoader loader = AccessController.doPrivileged(new PrivilegedAction<GroovyClassLoader>() {
            @Override
            public GroovyClassLoader run() {
                return new GroovyClassLoader(GroovyCalculateRule.class.getClassLoader());
            }
        });

        Class<?> groovyClazz = loader.parseClass(groovyScript);
        try {
            Object obj = groovyClazz.newInstance();


        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    @Override
    public Collection<String> shardKeys() {
        return null;
    }

    @Override
    public String eval(ShardKey shardKey) {


        return null;
    }
}
