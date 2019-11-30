module Domain {
    requires com.fasterxml.jackson.annotation;
    requires org.hibernate.orm.core;
    requires java.persistence;
    exports com.rental.domain.entities;
    exports com.rental.domain.interfaces;
    exports com.rental.domain.entities.base;
    exports com.rental.domain.interfaces.entities;
}