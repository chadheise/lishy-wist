package io.chadheise.lishyWist.handlers;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.amazonaws.services.dynamodbv2.document.Item;
import com.amazonaws.services.dynamodbv2.document.ItemCollection;
import com.amazonaws.services.dynamodbv2.document.KeyAttribute;
import com.amazonaws.services.dynamodbv2.document.PrimaryKey;
import com.amazonaws.services.dynamodbv2.document.PutItemOutcome;
import com.amazonaws.services.dynamodbv2.document.QueryOutcome;
import com.amazonaws.services.dynamodbv2.document.Table;
import com.amazonaws.services.dynamodbv2.document.spec.DeleteItemSpec;
import com.amazonaws.services.dynamodbv2.document.spec.GetItemSpec;
import com.amazonaws.services.dynamodbv2.document.spec.QuerySpec;
import com.google.inject.Inject;

import io.chadheise.lishyWist.guice.annotations.ItemsTable;
import io.chadheise.lishyWist.util.Constants;
import io.vertx.core.json.Json;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.RoutingContext;

public class ItemsHandlers {

    private final Table itemsTable;

    @Inject
    public ItemsHandlers(@ItemsTable final Table itemsTable) {
        this.itemsTable = itemsTable;
    }

    public void getAll(final RoutingContext ctx) {
        String userId = ctx.request().getParam(Constants.PARAM_USER);

        QuerySpec querySpec = new QuerySpec();

        KeyAttribute keyAttr = new KeyAttribute("user", userId);
        querySpec.withHashKey(keyAttr);

        List<String> desiredAttributes = new ArrayList<>();
        desiredAttributes.add("name");
        desiredAttributes.add("link");
        desiredAttributes.add("notes");
        desiredAttributes.add("id");
        // if (!thisUser) {
        // desiredAttributes.add("claimed");
        // }
        querySpec.withAttributesToGet(toArray(desiredAttributes));

        ItemCollection<QueryOutcome> collection = itemsTable.query(querySpec);

        JsonArray jsonArray = new JsonArray();
        collection.forEach(item -> {
            jsonArray.add(new JsonObject(item.toJSON()));
        });

        ctx.response().setStatusCode(200)
                .putHeader("content-type", "application/json; charset=utf-8")
                .end(Json.encodePrettily(jsonArray));
    }

    public void get(final RoutingContext ctx) {
        String userId = ctx.request().getParam(Constants.PARAM_USER);
        String itemId = ctx.request().getParam(Constants.PARAM_ITEM);

        GetItemSpec spec = new GetItemSpec();
        spec.withPrimaryKey(new KeyAttribute("user", userId), new KeyAttribute("id", itemId));
        // TODO: Handle invalid userIDs and itemIds

        List<String> desiredAttributes = new ArrayList<>();
        desiredAttributes.add("name");
        desiredAttributes.add("link");
        desiredAttributes.add("notes");
        desiredAttributes.add("id");
        // if (!thisUser) {
        // desiredAttributes.add("claimed");
        // }
        spec.withAttributesToGet(toArray(desiredAttributes));

        Item item = itemsTable.getItem(spec);

        ctx.response().setStatusCode(200)
                .putHeader("content-type", "application/json; charset=utf-8")
                .end(item.toJSONPretty());
    }

    public void create(final RoutingContext ctx) {
        String userId = ctx.request().getParam(Constants.PARAM_USER);

        JsonObject requestBody = ctx.getBodyAsJson();
        System.out.println(requestBody);

        // TODO: Validate json input

        requestBody.put("user", userId);
        requestBody.put("id", UUID.randomUUID().toString());

        Item item = Item.fromJSON(requestBody.toString());

        PutItemOutcome outcome = itemsTable.putItem(item);
        // TODO: Handle errors

        ctx.response().setStatusCode(201)
                .putHeader("content-type", "application/json; charset=utf-8")
                .end(requestBody.encodePrettily());
    }

    public void delete(final RoutingContext ctx) {
        String userId = ctx.request().getParam(Constants.PARAM_USER);
        String itemId = ctx.request().getParam(Constants.PARAM_ITEM);

        PrimaryKey pk = new PrimaryKey();
        pk.addComponents(new KeyAttribute("user", userId), new KeyAttribute("id", itemId));
        DeleteItemSpec spec = new DeleteItemSpec();
        spec.withPrimaryKey(pk);
        itemsTable.deleteItem(spec);
        // TODO: Handle errors

        ctx.response().setStatusCode(200)
                .end();
    }

    private String[] toArray(final List<String> list) {
        String[] arr = new String[list.size()];
        return list.toArray(arr);
    }

}
