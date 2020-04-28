package io.srhojo.java.loggerreader.services.mappers;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public interface InnerMapper<O,I> {

    I toInner(O outer);


    default List<I> toOuter(List<O> inner) {
        return inner!=null ? inner.stream()
                .map(this::toInner)
                .collect(Collectors.toList())
                : Collections.emptyList();
    }
}
