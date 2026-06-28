package com.home.cardmarket;

import java.util.Map;

import com.home.cardmarket.commandhandler.SumCsvExampleHandler;
import com.home.cardmarket.commandhandler.SumCsvHandler;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@AllArgsConstructor
public enum CommandTypeHandlerEnum {

    SUM_CSV_HANDLER("sum-csv", new SumCsvHandler()),
    SUM_CSV_EXAMPLE_HANDLER("sum-csv-example", new SumCsvExampleHandler());

    public final String command;
    public final TypeHandler handler;

    public static void executeCommand(Map<InputEnum, String> params) {
        if (params.get(InputEnum.TYPE).isBlank()) {
            log.error("Type must not be empty, set {} !", "--type");
            return;
        }

        for (CommandTypeHandlerEnum type : CommandTypeHandlerEnum.values()) {
            if (type.command.equals(params.get(InputEnum.TYPE))) {
                log.debug("Executing command: {} with parameters: {}", params.get(InputEnum.TYPE), params);
                type.handler.handle(params);
                return;
            }
        }

        log.error("Unknown type: {} !", params.get(InputEnum.TYPE));
    }
}
