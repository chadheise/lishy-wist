package io.chadheise.lishyWist.guice;

import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Table;
import com.google.inject.AbstractModule;

import io.chadheise.lishyWist.guice.annotations.ItemsTable;
import io.vertx.core.Vertx;
import io.vertx.ext.web.Router;

public class LishyWistModule extends AbstractModule {

    private static final Vertx VERTX = Vertx.factory.vertx();
    private static final DynamoDB DYNAMO_DB = new DynamoDBProvider().get();

    @Override
    protected void configure() {

        bind(Vertx.class).toInstance(VERTX);
        bind(Router.class).toInstance(Router.router(VERTX));

        bind(Table.class).annotatedWith(ItemsTable.class).toInstance(DYNAMO_DB.getTable("items"));
    }
}
