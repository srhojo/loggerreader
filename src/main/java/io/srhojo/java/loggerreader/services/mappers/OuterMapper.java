package io.srhojo.java.loggerreader.services.mappers;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public interface OuterMapper<O, I> {

    O toOuter(I inner);

    default List<O> toOuter(List<I> inner) {
        return inner!=null ? inner.stream()
                    .map(this::toOuter)
                    .collect(Collectors.toList())
                : Collections.emptyList();
    }

}
