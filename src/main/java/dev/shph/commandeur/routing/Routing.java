package dev.shph.commandeur.routing;

/**
 * Interface to represent the result of {@link dev.shph.commandeur.Command}
 * execution.
 */
public interface Routing {

    /** @return String containing resulting resource URI.
     */
    String uri();

    /**
     * @return Type of routing.
     */
    Type type();

    enum Type {
        FORWARD, REDIRECT, EMPTY
    }

    /**
     * Implementation of {@link Routing} that represents empty result.
     */
    final class Empty implements Routing {
        @Override
        public String uri() {
            return "";
        }

        @Override
        public Type type() {
            return Type.EMPTY;
        }

        @Override
        public int hashCode() {
            return 1;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            return obj != null && obj.getClass() == getClass();
        }
    }
}
