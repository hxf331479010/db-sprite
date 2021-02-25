package org.sprite.config;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public final class SpriteConfig {

    private final String spriteDataSourceName;

    private final Map<String,ITableRule> tableRules = new HashMap();

    public SpriteConfig(String spriteDataSourceName) {
        this.spriteDataSourceName = spriteDataSourceName;
    }

    public SpriteConfig addTableRule(String tableName , ITableRule tableRule) {
        return this;
    }

    public SpriteConfig addTableRule(Collection<String> tableNames , ITableRule tableRule) {
        return this;
    }

    public SpriteConfig addDefaultTableRule(ITableRule tableRule) {
        return this;
    }

    public SpriteConfig removeDefaultTableRule() {
        return this;
    }

    public SpriteConfig removeTableRule(String tableName) {
        return this;
    }

    public SpriteConfig removeTableRule(String ... tableNames) {
        return this;
    }

}
