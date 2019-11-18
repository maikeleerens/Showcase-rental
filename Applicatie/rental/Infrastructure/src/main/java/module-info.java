module Infrastructure {
    requires spring.data.jpa;
    requires spring.context;
    requires Domain;
    exports com.rental.infrastructure.repositories;
}