package dev.shph.commandeur.container;

import dev.shph.commandeur.Command;

import java.util.function.Supplier;

/**
 * Interface to represent the command container.
 */
public interface CommandContainer {

    /**
     * Searches for implementation of {@link Command} in container by name from
     * supplier.
     *
     * @param commandNameSupplier Supplier of name string.
     * @return Either found implementation of {@link Command} or instance of {@link Command.Empty}
     */
    Command resolve(Supplier<String> commandNameSupplier);

    /**
     * Searches for implementation of {@link Command} in container by name f.
     *
     * @param commandName Command name.
     * @return Either found implementation of {@link Command} or instance of {@link Command.Empty}
     */
    Command resolve(String commandName);

    /**
     * Add command of {@code commandClass} class to container.
     *
     * @param commandName Command name.
     * @param commandClass Class of command to add.
     */
    void register(String commandName, Class<?> commandClass);

    /**
     * Add instance of {@code command} to container.
     *
     * @param commandName Command name.
     * @param command Instance of command to add.
     */
    void register(String commandName, Command command);
}
