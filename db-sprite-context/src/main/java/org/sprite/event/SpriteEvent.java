package org.sprite.event;

public enum SpriteEvent {
    /*初始化前后*/
    preInit,postInit,
    /*解析前后*/
    preParse,postParse,
    /*路由前后*/
    preRoute,postRoute,
    /*结果集合并前后*/
    preMerge,postMerge,
    /*sql改写前后*/
    preReWrite,postReWrite,
    /*执行器优化前后*/
    preOptimize,postOptimize,
    /*数据库执行sql前后*/
    preExecute,postExecute;
}
