package com.example.demo.DataBase;

import org.hibernate.engine.transaction.jta.platform.internal.AbstractJtaPlatform;

import javax.transaction.TransactionManager;
import javax.transaction.UserTransaction;

public class CustomJtaPlatform extends AbstractJtaPlatform {
    private static TransactionManager transactionManager;
    private static UserTransaction transaction;

    public static void setTransactionManager(TransactionManager tm) {
        transactionManager = tm;
    }

    public static void setUserTransaction(UserTransaction ut) {
        transaction = ut;
    }

    @Override
    protected TransactionManager locateTransactionManager() {
        return transactionManager;
    }

    @Override
    protected UserTransaction locateUserTransaction() {
        return transaction;
    }
}
