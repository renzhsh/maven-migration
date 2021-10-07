package com.liangma.migration.policy;

@Deprecated
public interface IMigrationFactory {
    IMigration getMigration(MigrationPolicy policy);
}
