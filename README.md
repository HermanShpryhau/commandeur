# Commandeur
**Commandeur** is an implementation of Command design pattern commonly used when creating a unified controller with Java Servlet API. 
It also provides a mechanism to automatically find and load commands into container. These commands can then be easily fetched from this container.

## Usage

### Initializing a container

**Comandeur** provides `CommandContainer` interface to a container. For now its only implementation is 
`AnnotaionScanningContainer` that scans the classpath for commands.

I suggest that you initialize the container in a servlet context listener and then retrieve it from servlet context
when needed.

```java
public class CommandContainerInitializer implements ServletContextListener {
    private static final Logger logger = LogManager.getLogger();

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        try {
            CommandContainer container = new AnnotaionScanningContainer();
            sce.getServletContext().setAttribute("commandContainer", container);
        } catch (ContainerInitializationException e) {
            logger.error("Unable to initialize command dispatcher. {}", e.getMessage());
            throw new RuntimeException("Unable to initialize command container.", e);
        }
    }
}
```

### Sample Command

To create a command that can be discovered and instantiated with **commandeur**'s container it must implement `Command` 
interface and be annotated with `@DiscoverableCommand` annotation. You must specify command's name in the annotation.
The name must be uniques for each command. Otherwise, an exception will be thrown upon initialization of container.

```java
@DiscoverableCommand("test-command")
public class SampleCommand implements Command {
    @Override
    public Routing result(HttpServletRequest request, HttpServletResponse response) {
        return new Routing.Empty();
    }
}
```

### Returning the results

Results of command execution are represented by `Routing` interface and its implementations. Its implementations
`Forward` and `Redirect` represent a forward and redirect behaviours of Servlet responses.

```java
@DiscoverableCommand("test-command")
public class SampleCommand implements Command {
    @Override
    public Routing result(HttpServletRequest request, HttpServletResponse response) {
        if (...) {
            return new Forward("/forward-resource");
        } else {
            return new Redirect("/redirect-resource");
        }
    }
}
```

### Getting and executing a command

To get a command just use `resolve()` method of `CommandContainer`. The execution of command is lazy and only happens whe a result is requested.

```java
Command command = commandContainer.resolve(commandName);
Routing routing = command.result(request, response);
```