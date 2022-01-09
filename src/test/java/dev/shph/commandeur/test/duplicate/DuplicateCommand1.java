package dev.shph.commandeur.test.duplicate;

import dev.shph.commandeur.Command;
import dev.shph.commandeur.annotation.DiscoverableCommand;
import dev.shph.commandeur.routing.Routing;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@DiscoverableCommand("duplicate")
public class DuplicateCommand1 implements Command {
    @Override
    public Routing result(HttpServletRequest request, HttpServletResponse response) {
        return new Routing.Empty();
    }
}
