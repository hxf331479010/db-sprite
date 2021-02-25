package org.sprite.config.sample.grouprule;

import org.sprite.config.IDataBaseGroupRule;

import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * ${Description}
 *
 * @author han xiaofeng on 2020/9/10
 */
public final class SampleGroupRule implements IDataBaseGroupRule {

    private static final Pattern readWeightPattern = Pattern.compile("[Rr](\\d*)");
    private static final Pattern writeWeightPattern = Pattern.compile("[Ww](\\d*)");

    private final String weight;

    private final Random random = new Random();

    private AtomicBoolean inited = new AtomicBoolean(false);

    private List<String> dbs  = new ArrayList();

    private LinkedList<Integer> readArea = new LinkedList();

    private LinkedList<Integer>  writeArea = new LinkedList();


    /**
     *
     * @param weight  db1:r10w10,db2:r10w10
     */
    public SampleGroupRule(String weight) {
        this.weight = weight;
        init();
    }

    private void init(){
        if (!inited.compareAndSet(false,true)) {
            throw new RuntimeException("twice start");
        }
        try {
            //TODO : 解析参数
            Map<String,Weight> weightMap = getDbAndWeight(this.weight);

            for (Map.Entry<String,Weight> entry : weightMap.entrySet()) {
                dbs.add(entry.getKey());
                Weight weight = entry.getValue();
                if (readArea.size()==0) {
                    readArea.add(weight.readWeight);
                } else {
                    readArea.add(readArea.getLast()+weight.readWeight);
                }

                if (writeArea.size()==0) {
                    writeArea.add(weight.writeWeight);
                } else {
                    writeArea.add(writeArea.getLast()+weight.writeWeight);
                }
            }
        } catch (Exception e) {
            inited.set(false);
        }
    }

    private Map<String,Weight> getDbAndWeight(String weight) {

        Map<String,Weight> result = new HashMap<String,Weight>();
        String[] dbsAndWeight = weight.split(",");
        for (String dbAndWeight : dbsAndWeight) {
            String[] message = dbAndWeight.split(":");
            switch(message.length) {
                case 1:
                    result.put(message[0],new DefaultWeight());
                    break;
                case 2:
                    result.put(message[0],new Weight(message[1]));
            }
        }
        return result;
    }


    private int random(int factor) {
        return random.nextInt(factor);
    }

    @Override
    public String getWrite() {

        Integer factor = writeArea.getLast();
        int randomNo = random(factor);

        for (int i=0;i<writeArea.size();i++) {
            if (writeArea.get(i)<randomNo) {
                return dbs.get(i);
            }
        }
        return null;
    }

    @Override
    public String getRead() {
        Integer factor = readArea.getLast();
        int randomNo = random(factor);
        for (int i=0;i<readArea.size();i++) {
            if (randomNo < readArea.get(i)) {
                return dbs.get(i);
            }
        }
        return null;
    }


    class Weight {

        private Integer readWeight;

        private Integer writeWeight;

        public Weight(String weight){
            readWeight = parseNumber(readWeightPattern,weight,10);
            writeWeight = parseNumber(writeWeightPattern,weight,10);
        }

        private int parseNumber(Pattern pattern , String weight , int defaultValue) {
            Matcher m = pattern.matcher(weight);
            if (m.find()) {
                if (m.group(1).length() ==0 ) {
                    return  defaultValue;
                } else {
                    return Integer.parseInt(m.group(1));
                }
            } else {
                return  defaultValue;
            }
        }

        public Integer getReadWeight() {
            return readWeight;
        }

        public Integer getWriteWeight() {
            return writeWeight;
        }
    }

    class DefaultWeight extends Weight {

        public DefaultWeight() {
            super("r10w10");
        }

    }
}
