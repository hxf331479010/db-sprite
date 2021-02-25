package org.sprite.groovy;

import groovy.lang.GroovyClassLoader;
import groovy.lang.GroovyShell;
import groovy.lang.Script;

import java.security.AccessController;
import java.security.PrivilegedAction;

/**
 * ${Description}
 *
 * @author han xiaofeng on 2020/12/1
 */
public class GroovyActuator {


    public Object getGroovyRunImpl(String groovyScript) {

        GroovyClassLoader loader = AccessController.doPrivileged(new PrivilegedAction<GroovyClassLoader>() {
            @Override
            public GroovyClassLoader run() {
                return new GroovyClassLoader(GroovyActuator.class.getClassLoader());
            }
        });

        Class<?> groovyClazz = loader.parseClass(groovyScript);
        try {
            Object obj = groovyClazz.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }



    public static void main(String[] args) {
        GroovyShell groovyShell = new GroovyShell();
        Script script = groovyShell.parse("return custId.hashCode();");
        script.setProperty("custId","1234");
        System.out.println(script.run());
        System.out.println("1234".hashCode());



        Runnable aa = new Runnable() {
            @Override
            public void run() {
                int x = "1234".hashCode();
                for (;;) {
                    try {
                        Thread.sleep(2000L);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    script.setProperty("custId","1234");


                    if (x != (int)script.run()){
                        System.out.println("error");
                    } else {
                        System.out.println("1234".hashCode()+"------"+script.run());
                    }
                }
            }
        };

        Runnable bb = new Runnable() {
            @Override
            public void run() {
                int x = "123".hashCode();
                for (;;) {
                    script.setProperty("custId","123");
                    try {
                        Thread.sleep(2000L);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(11);
                    if (x != (int)script.run()){
                        System.out.println("error");
                    } else {
                        System.out.println("123".hashCode()+"------"+script.run());
                    }
                }
            }
        };

        Thread t1 = new Thread(aa);
        Thread t2 = new Thread(bb);

        t1.start();
        t2.start();

    }


}
