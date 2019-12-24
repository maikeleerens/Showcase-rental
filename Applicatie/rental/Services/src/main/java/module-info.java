module Services {
    requires Domain;
    requires Infrastructure;
    requires com.fasterxml.jackson.annotation;
    requires spring.beans;
    requires spring.context;
    requires spring.security.core;
    requires jjwt;
    exports com.rental.services;
}