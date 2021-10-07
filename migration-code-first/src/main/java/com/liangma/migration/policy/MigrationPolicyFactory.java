package com.liangma.migration.policy;

import com.liangma.migration.provider.IMigrationProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Deprecated
public class MigrationPolicyFactory implements IMigrationFactory {

    @Autowired
    private IMigrationProvider migrationProvider;

    public IMigration getMigration(MigrationPolicy policy) {
        switch (policy) {
            case Initialize:
                return new InitializeMigration(migrationProvider);
            case Upgrade:
                return new UpgradeMigration(migrationProvider);
            case Modify:
            default:
                return new ModifyMigration(migrationProvider);
        }
    }
}
