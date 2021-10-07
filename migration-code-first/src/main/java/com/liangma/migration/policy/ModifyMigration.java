package com.liangma.migration.policy;

import com.liangma.migration.provider.IMigrationProvider;

public class ModifyMigration implements IMigration {
    private IMigrationProvider migrationProvider;

    public ModifyMigration(IMigrationProvider migrationProvider) {
        this.migrationProvider = migrationProvider;
    }

    //    private IEnumerable<DbChange> NewTables { get; set; }
//
//    private IEnumerable<DbChange> ChangeTables { get; set; }

    @Override
    public void execute() {

    }
}
