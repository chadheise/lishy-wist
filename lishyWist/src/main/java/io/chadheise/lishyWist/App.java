package io.chadheise.lishyWist;

import com.google.inject.Guice;
import com.google.inject.Injector;

import io.chadheise.lishyWist.guice.LishyWistModule;
import io.vertx.core.Vertx;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;
import io.vertx.ext.web.Router;

/**
 * Hello world!
 *
 */
public class App {

    private static final Logger LOGGER = LoggerFactory.getLogger(App.class);

    public static void main(String[] args) {

        LOGGER.info("Starting application");

        Injector injector = Guice.createInjector(new LishyWistModule());

        Vertx vertx = injector.getInstance(Vertx.class);

        RouterInitializer routerInitializer = injector.getInstance(RouterInitializer.class);
        Router router = injector.getInstance(Router.class);
        routerInitializer.init(router);

        vertx.createHttpServer().requestHandler(router::accept).listen(5000);

    }
}
