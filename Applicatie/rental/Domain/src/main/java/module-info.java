module Domain {
    requires java.persistence;
    requires org.hibernate.orm.core;
    requires com.fasterxml.jackson.annotation;
    exports com.rental.domain.entities.base;
    exports com.rental.domain.interfaces;
    exports com.rental.domain.interfaces.entities;
}