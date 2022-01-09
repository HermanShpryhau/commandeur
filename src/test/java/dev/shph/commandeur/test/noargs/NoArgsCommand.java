package dev.shph.commandeur.test.noargs;

import dev.shph.commandeur.Command;
import dev.shph.commandeur.annotation.DiscoverableCommand;
import dev.shph.commandeur.routing.Routing;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@DiscoverableCommand("noargs")
public class NoArgsCommand implements Command {

    public NoArgsCommand(int arg) {}

    @Override
    public Routing result(HttpServletRequest request, HttpServletResponse response) {
        return new Routing.Empty();
    }
}
