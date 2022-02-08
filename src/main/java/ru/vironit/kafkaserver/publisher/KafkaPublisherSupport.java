package ru.vironit.kafkaserver.publisher;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.List;

public class KafkaPublisherSupport{
    private PropertyChangeSupport support;
    private String message;

    public KafkaPublisherSupport() {
        support = new PropertyChangeSupport(this);
    }

    public void setMessage(String message) {
        support.firePropertyChange("message", this.message, message);
        this.message = message;
    }

    public void addPropertyChangeListener(PropertyChangeListener propertyChangeListener) {
        support.addPropertyChangeListener(propertyChangeListener);
    }

    public void removePropertyChangeListener(PropertyChangeListener propertyChangeListener) {
        support.removePropertyChangeListener(propertyChangeListener);
    }
}
