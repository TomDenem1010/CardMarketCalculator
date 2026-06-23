package com.home.cardmarket.commandhandler;

import java.util.List;
import java.util.Map;

import com.home.cardmarket.InputEnum;
import com.home.cardmarket.TypeHandler;
import com.home.cardmarket.file.CsvWriter;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class SumCsvExampleHandler implements TypeHandler {

    @Override
    public void handle(Map<InputEnum, String> params) {
        new CsvWriter().write(
                params.get(InputEnum.FILE_PATH),
                "sumCsvExample",
                List.of(
                        CsvHeaderEnum.getHeaders(),
                        List.of(
                                "1",
                                "FleshAndBlood",
                                "GEM-Pack-Promos/Cut-Through-Red-Extended-Art-Rainbow-Foil",
                                "1",
                                "1",
                                "",
                                "",
                                "",
                                "")));
    }
}
