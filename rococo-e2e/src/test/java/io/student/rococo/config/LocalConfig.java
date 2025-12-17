package io.student.rococo.config;

public enum LocalConfig implements Config {
    INSTANCE;   // <- Вот этот волшебный момент!
/*
Singleton - это шаблон проектирования, который гарантирует, что у класса будет только один экземпляр
во всей программе, и предоставляет глобальную точку доступа к этому экземпляру.
Это рекомендация Джошуа Блоха (автора "Effective Java"):
"A single-element enum type is the best way to implement a singleton"
*/

    @Override
    public String frontUrl() {
        return "http://localhost:3000";
    }

    @Override
    public String userJdbcUrl() {
        return "jdbc:mysql://localhost:3306/rococo-auth";
    }

    @Override
    public String dbUsername() {
        return "root";
    }

    @Override
    public String dbPassword() {
        return "secret";
    }
}
