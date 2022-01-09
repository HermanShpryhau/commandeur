package dev.shph.commandeur.routing;

/**
 * Implementation of {@link Routing} interface
 * that represents forward routing.
 */
public final class Forward implements Routing {
    private final String uri;

    public Forward(String uri) {
        this.uri = uri;
    }

    @Override
    public String uri() {
        return uri;
    }

    @Override
    public Routing.Type type() {
        return Routing.Type.FORWARD;
    }
}
