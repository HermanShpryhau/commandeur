package dev.shph.commandeur.container;

import dev.shph.commandeur.Command;
import dev.shph.commandeur.annotation.DiscoverableCommand;
import org.reflections.Reflections;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.function.Supplier;
import java.util.stream.Collectors;

/**
 * Implementations of {@link CommandContainer} that searches the classpath
 * for classes annotated with {@link DiscoverableCommand} annotation and
 * implementing {@link Command} interface.
 */
public final class AnnotationScanningContainer implements CommandContainer {
    private final Map<String, Command> container = new HashMap<>();

    /**
     * Creates container by scanning the whole class path
     */
    public AnnotationScanningContainer() throws CommandeurException {
        this(new Reflections());
    }

    /**
     * Creates container by scanning classes in base package
     * and its sub-packages.
     *
     * @param basePackage Identifier of base package
     */
    public AnnotationScanningContainer(String basePackage) {
        this(new Reflections(basePackage));
    }

    private AnnotationScanningContainer(Reflections reflections) {
        Set<Class<?>> discoverableCommands = reflections.getTypesAnnotatedWith(DiscoverableCommand.class).stream()
                .filter(clazz -> Arrays.asList(clazz.getInterfaces()).contains(Command.class))
                .collect(Collectors.toSet());
        for (Class<?> clazz : discoverableCommands) {
            DiscoverableCommand discoverableCommand = clazz.getAnnotation(DiscoverableCommand.class);
            try {
                Command commandInstance = (Command) clazz.newInstance();
                if (container.get(discoverableCommand.value()) != null) {
                    throw new CommandeurException("Duplicate command name \"" + discoverableCommand.value() +"\"");
                }
                container.put(discoverableCommand.value(), commandInstance);
            } catch (InstantiationException | IllegalAccessException e) {
                throw new CommandeurException("Cannot create instance of " + clazz.getName(), e);
            }
        }
    }

    @Override
    public Command resolve(Supplier<String> commandNameSupplier) {
        return Optional.ofNullable(container.get(commandNameSupplier.get())).orElse(new Command.Empty());
    }

    @Override
    public Command resolve(String commandName) {
        return Optional.ofNullable(container.get(commandName)).orElse(new Command.Empty());
    }

    @Override
    public void register(String commandName, Class<?> commandClass) {
        try {
            Command commandInstance = (Command) commandClass.newInstance();
            register(commandName, commandInstance);
        } catch (InstantiationException | IllegalAccessException e) {
            throw new CommandeurException("Cannot create instance of " + commandClass.getName(), e);
        }
    }

    @Override
    public void register(String commandName, Command command) {
        if (container.get(commandName) != null) {
            throw new CommandeurException("Duplicate command name \"" + commandName +"\"");
        }
        container.put(commandName, command);
    }
}
