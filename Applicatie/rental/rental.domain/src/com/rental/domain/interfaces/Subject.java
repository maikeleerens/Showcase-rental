package com.rental.domain.interfaces;

//Interface used for the Subject objects
public interface Subject {
    void Attach(Observer observer);
    void Detach(Observer observer);
    void Notify();
}
