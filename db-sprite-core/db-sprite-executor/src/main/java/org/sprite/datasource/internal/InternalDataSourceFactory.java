package org.sprite.datasource.internal;

import org.sprite.context.SpriteContext;

import javax.sql.DataSource;

/**
 * ${Description}
 *
 * @author han xiaofeng on 2020/12/2
 */
public class InternalDataSourceFactory {

    public static DataSource createDataSource(SpriteContext spriteContext) {

        switch (spriteContext.getType()) {
            case SHARD :
                return new InternalShardDataSource(spriteContext);
        }
        return new InternalShardDataSource(spriteContext);
    }
}
