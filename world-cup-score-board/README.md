# World Cup Score Board

The implementation of assignment provided by Dalibor Samek. This is my opinion on how the proper Score Board library should look
like. I used layers architecture design to decompose application to smaller parts.

### Layers:

1. Facade - class ScoreBoardImpl provides facade to whole logic of library. Client can use this class by creating new instance.
   Client is able to use class with DefaultDependencyFactory by calling no args constructor.
2. Service layer - ScoreBoardServiceImpl class contains all business logic needed by library. It provides methods to start, finish,
   update game and getSummary.
3. Mapping layer - transforms internal data objects (entities) into Data Transfer Objects (dto). This is mainly done because entity
   objects (database objects) can change (add fields), but this change does not affect communication between library and client
   code.
4. Database layer - Data Access Object that contains in memory storage for GameEntities.
5. Data layer - entities that are stored in database.

### Used design patterns:

1. Proxy - class ScoreBoardImpl is proxy for ScoreBoardServiceImpl. Its purpose is to handle dependency management and exception
   handling.
2. Data access object - class ScoreBoardDaoImpl is only class that has access to data objects and provides methods to manipulate
   them. Everything that is stored in ScoreBoardDaoImpl is well encapsulated - in case that caller changes the entity, the database
   representation of entity is not changed, until caller calls save() method. This is achieved by returning of deep copies for each
   object returned from dao layer.
3. Null Object - for testing abstract classes.

### Notes

- All methods are annotated with @NotNull or @Nullable annotations to inform caller about the parameters and return values of
  method.
- In case that caller provides incorrect parameter (for example null into the parameter that is annotated with @NotNull), the
  IntelliJ IDEA IDE will inform caller about this mistake by warning. In case that caller uses any method incorrectly he receives
  IllegalArgumentException.
- There are 79 unit tests and 1 acceptance test, when you run maven clean install both unit and integration (acceptance) tests are
  executed.
- Coverage in IntelliJ IDEA IDE shows 100% coverage for classes (14/14), 91% coverage for methods(72/79) and 93% coverage for
  lines (196/209). Main uncovered lines of code are constructors, getters/setters and toString() methods.
- If needed I can also add jacoco to show coverage after maven clean install.