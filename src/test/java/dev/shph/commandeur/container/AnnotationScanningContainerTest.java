package dev.shph.commandeur.container;

import dev.shph.commandeur.Command;
import dev.shph.commandeur.routing.Routing;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AnnotationScanningContainerTest {
    private static final String TEST_COMMAND_NAME = "test-command";
    private static final String NON_EXISTING_COMMAND_NAME = "not-command";
    private static final String VALID_COMMANDS_PACKAGE = "dev.shph.commandeur.test.valid";
    private static final String NO_CONSTRUCTOR_COMMANDS_PACKAGE = "dev.shph.commandeur.test.noargs";
    private static final String DUPLICATE_COMMANDS_PACKAGE = "dev.shph.commandeur.test.duplicate";

    @Test
    void validCommandTest() throws CommandeurException {
        AnnotationScanningContainer container = new AnnotationScanningContainer(VALID_COMMANDS_PACKAGE);
        Command command = container.resolve(TEST_COMMAND_NAME);
        assertNotEquals(new Command.Empty(), command);
    }

    @Test
    void noConstructorCommandTest() throws CommandeurException {
        assertThrows(CommandeurException.class, () -> new AnnotationScanningContainer(NO_CONSTRUCTOR_COMMANDS_PACKAGE));
    }

    @Test
    void duplicateCommandTest() throws CommandeurException {
        assertThrows(CommandeurException.class, () -> new AnnotationScanningContainer(DUPLICATE_COMMANDS_PACKAGE));
    }

    @Test
    void resolveExistingCommandByName() throws CommandeurException {
        AnnotationScanningContainer container = new AnnotationScanningContainer(VALID_COMMANDS_PACKAGE);
        Command command = container.resolve(TEST_COMMAND_NAME);
        assertNotEquals(new Command.Empty(), command);
        assertEquals(new Routing.Empty(), command.result(null, null));
    }

    @Test
    void resolveExistingCommandBySupplier() throws CommandeurException {
        AnnotationScanningContainer container = new AnnotationScanningContainer(VALID_COMMANDS_PACKAGE);
        Command command = container.resolve(() -> TEST_COMMAND_NAME);
        assertNotEquals(new Command.Empty(), command);
        assertEquals(new Routing.Empty(), command.result(null, null));
    }

    @Test
    void resolveNonExistingCommandByName() throws CommandeurException {
        AnnotationScanningContainer container = new AnnotationScanningContainer(VALID_COMMANDS_PACKAGE);
        Command command = container.resolve(NON_EXISTING_COMMAND_NAME);
        assertEquals(new Command.Empty(), command);
    }

    @Test
    void resolveNonExistingCommandBySupplier() throws CommandeurException {
        AnnotationScanningContainer container = new AnnotationScanningContainer(VALID_COMMANDS_PACKAGE);
        Command command = container.resolve(() -> NON_EXISTING_COMMAND_NAME);
        assertEquals(new Command.Empty(), command);
    }
}