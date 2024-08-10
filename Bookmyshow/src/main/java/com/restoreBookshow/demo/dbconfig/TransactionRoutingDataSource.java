package com.restoreBookshow.demo.dbconfig;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
import org.springframework.transaction.support.TransactionSynchronizationManager;

//import javax.annotation.Nullable;


public class TransactionRoutingDataSource extends AbstractRoutingDataSource {

//	@Nullable
	@Override
	protected Object determineCurrentLookupKey() {
		return TransactionSynchronizationManager.isCurrentTransactionReadOnly() ? DataSourceType.READ_ONLY
				: DataSourceType.READ_WRITE;
	}
}
