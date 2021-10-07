package com.liangma.migration.policy;

/**
 * 数据库迁移策略
 */
public enum MigrationPolicy {
    /**
     * 初始化
     */
    Initialize,

    /**
     * 模型修正
     */
    Modify,

    /**
     * 模型升级
     */
    Upgrade
}
