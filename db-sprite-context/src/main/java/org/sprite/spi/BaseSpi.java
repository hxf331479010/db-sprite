package org.sprite.spi;

import org.sprite.event.SpriteEvent;
import org.sprite.runtime.SpriteRuntimeContext;

/**
 * 这是SPI方式的一个基类接口。
 *
 * @author han xiaofeng on 2020/9/10
 */
public interface BaseSpi {

    public int order();

    public SpriteEvent getEvent();

    public boolean invoke(SpriteRuntimeContext context);
}
