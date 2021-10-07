package com.liangma.migration.policy;

import com.liangma.migration.descriptor.TableDescriptor;
import com.liangma.migration.exception.MigrationException;

import java.util.List;

public abstract class BaseMigration implements IMigration {

   abstract List<TableDescriptor> getDeclaredTables() throws MigrationException;


   abstract List<TableDescriptor> getOwnerTables() ;
}
