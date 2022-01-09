package dev.shph.commandeur.routing;

/**
 * Implementation of {@link Routing} interface
 * that represents redirect routing.
 */
public final class Redirect implements Routing {
    private final String uri;

    public Redirect(String uri) {
        this.uri = uri;
    }

    @Override
    public String uri() {
        return uri;
    }

    @Override
    public Routing.Type type() {
        return Routing.Type.REDIRECT;
    }
}
