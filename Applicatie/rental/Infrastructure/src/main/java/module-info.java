module Infrastructure {
    requires spring.data.jpa;
    requires spring.context;
    requires Domain;
    requires com.fasterxml.jackson.annotation;
    requires java.persistence;
    requires org.hibernate.orm.core;
    requires spring.tx;
    exports com.rental.infrastructure.repositories;
}