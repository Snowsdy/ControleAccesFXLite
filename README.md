# Controle Acces FX Lite

Old project updated to a Maven project with the following 
technologies :

- SpringBoot (JPA with H2 Database) + H2Config.
- JavaFX

Made with JDK 21.

Not all functionalities are implemented yet. However, 
this version of the project works compared to the old one 
due to obsolescence.

Admin account to testing admin functionalities :

```java
// Prototype Admin(name, login, password)
new Admin("Mr. Admin n°1", "toto", "tutu");
```

## SOLID Principles

This project was made to update my old one in order to 
apply these principles.

However, not fully.

SOLID Principles applied thanks to the SpringBoot framework, 
so in the following packages :

- `repositories`: Layer to communicate with the database.
- `services`: Upper layer to bind `repositories` to the client side.
- `config`: will instantiate a temporary H2 database at 
each run of the program.
- `entities`: where are stored entities classes.

## Future updates

The next goals will be the update of the full version 
of the old project and will be renamed *AccessControlFX*, 
apply the SOLID principles to the client side using 
JavaFX (not applied yet) and finally to update the JavaFX design by
adding a stylesheet and update the UX/UI.