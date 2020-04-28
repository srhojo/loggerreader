package io.srhojo.java.loggerreader.api.domain;

import java.util.List;

public class ContainerList<T> {

    private List<T> values;

    private ContainerList(List<T> values) {
        this.values = values;
    }

    public static ContainerList of(List values) {
        return new ContainerList(values);
    }
}
