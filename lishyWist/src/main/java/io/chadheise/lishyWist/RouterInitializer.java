package io.chadheise.lishyWist;

import com.google.inject.Inject;

import io.chadheise.lishyWist.handlers.ItemsHandlers;
import io.chadheise.lishyWist.util.Constants;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.handler.BodyHandler;

public class RouterInitializer {

    private final ItemsHandlers itemsHandlers;

    @Inject
    public RouterInitializer(final ItemsHandlers itemsHandlers) {
        this.itemsHandlers = itemsHandlers;
    }

    public void init(final Router router) {

        router.route("/v1/users/:" + Constants.PARAM_USER + "/items")
                .handler(BodyHandler.create());

        router.get("/v1/users/:" + Constants.PARAM_USER + "/items")
                .handler(itemsHandlers::getAll);

        router.get("/v1/users/:" + Constants.PARAM_USER + "/items/:" + Constants.PARAM_ITEM)
                .handler(itemsHandlers::get);

        router.post("/v1/users/:" + Constants.PARAM_USER + "/items")
                .handler(itemsHandlers::create);

        router.delete("/v1/users/:" + Constants.PARAM_USER + "/items/:" + Constants.PARAM_ITEM)
                .handler(itemsHandlers::delete);

    }

}
