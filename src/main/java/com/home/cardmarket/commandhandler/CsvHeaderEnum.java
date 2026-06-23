package com.home.cardmarket.commandhandler;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum CsvHeaderEnum {

    ID("id"),
    GAME("game"),
    NAME("name"),
    LANGUAGE("language"),
    QUANTITY("quantity"),
    PRICE_FROM("price from"),
    PRICE_TREND("price trend"),
    TOTAL_FROM("total from"),
    TOTAL_TREND("total trend");

    public final String header;

    public static List<String> getHeaders() {
        return Arrays.stream(CsvHeaderEnum.values())
                .map(headerEnum -> headerEnum.header)
                .collect(Collectors.toList());
    }
}
