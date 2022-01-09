package dev.shph.commandeur;

import dev.shph.commandeur.routing.Routing;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Interface to represent command executed by controller.
 */
public interface Command {

    /**
     * Returns the result of processing {@link HttpServletRequest} as
     * {@link Routing}.
     *
     * @param request Servlet request to process.
     * @param response Servlet response object that contains results of processing request.
     * @return
     */
    Routing result(HttpServletRequest request, HttpServletResponse response);

    /**
     * Implementations of {@link Command} to represent empty command.
     * Instances of this class are returned by {@link dev.shph.commandeur.container.CommandContainer}
     * if it can't find needed command.
     */
    final class Empty implements Command {
        @Override
        public Routing result(HttpServletRequest request, HttpServletResponse response) {
            return new Routing.Empty();
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
