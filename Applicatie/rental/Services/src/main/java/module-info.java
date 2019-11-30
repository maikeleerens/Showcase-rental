module Services {
    requires Infrastructure;
    requires Domain;
    requires spring.beans;
    requires spring.context;
    requires com.fasterxml.jackson.annotation;
    exports com.rental.services;
}