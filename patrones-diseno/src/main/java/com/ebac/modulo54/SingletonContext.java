package com.ebac.modulo54;

public class SingletonContext {

    public void demonstrateSingleton() {
        AppConfiguration config1 = AppConfiguration.getInstance();
        AppConfiguration config2 = AppConfiguration.getInstance();

        System.out.println(
                "Nombre de la aplicación: "
                        + config1.getApplicationName()
        );

        System.out.println(
                "¿Ambas variables contienen la misma instancia? "
                        + (config1 == config2)
        );
    }
}
