module Infrastructure {
    requires Domain;
    requires spring.data.jpa;
    requires spring.context;
    requires spring.tx;
    requires java.persistence;
    requires com.fasterxml.jackson.annotation;
    exports com.rental.infrastructure.repositories;
}