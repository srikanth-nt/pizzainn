package org.tui;

import org.apache.wicket.util.time.Duration;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.bio.SocketConnector;
import org.eclipse.jetty.webapp.WebAppContext;

/**
 * Jetty server for starting the application
 *
 * @author: Srikanth NT
 */
public class RunPizzaInn {
    private static final int TIMEOUT = (int) Duration.ONE_HOUR.getMilliseconds();

    public static void main(final String[] args) throws Exception {
        final SocketConnector connector = new SocketConnector();
        connector.setMaxIdleTime(TIMEOUT);
        connector.setSoLingerTime(-1);
        connector.setPort(8080);

        final Server server = new Server();
        server.addConnector(connector);

        final WebAppContext bb = new WebAppContext();
        bb.setServer(server);
        bb.setContextPath("/pizzainn");
        bb.setWar("core/src/main/webapp");

        server.setHandler(bb);
        try {
            System.out.println(">>> STARTING EMBEDDED JETTY SERVER, PRESS ANY KEY TO STOP");
            server.start();
            System.in.read();
            System.out.println(">>> STOPPING EMBEDDED JETTY SERVER");
            server.stop();
            server.join();
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }
    }
}
