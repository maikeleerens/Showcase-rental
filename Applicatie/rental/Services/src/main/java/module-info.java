module Services {
    requires spring.context;
    requires Domain;
    requires Infrastructure;
    requires spring.beans;
    requires com.fasterxml.jackson.annotation;
    exports com.rental.services;
}