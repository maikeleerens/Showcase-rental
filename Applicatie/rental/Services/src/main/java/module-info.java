module Services {
    requires Infrastructure;
    requires Domain;
    requires spring.beans;
    requires spring.context;
    exports com.rental.services;
}