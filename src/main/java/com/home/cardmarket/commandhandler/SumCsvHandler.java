package com.home.cardmarket.commandhandler;

import java.nio.file.Path;
import java.util.List;
import java.util.Map;

import com.home.cardmarket.InputEnum;
import com.home.cardmarket.TypeHandler;
import com.home.cardmarket.file.CsvReader;
import com.home.cardmarket.file.CsvWriter;
import com.home.cardmarket.rest.CmValueSetter;

import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@NoArgsConstructor
public class SumCsvHandler implements TypeHandler {

    @Override
    public void handle(Map<InputEnum, String> params) {
        List<Map<CsvHeaderEnum, String>> rows = new CsvReader().read(params.get(InputEnum.FILE_PATH));
        setRowValues(rows);
        log.debug("Rows after setting values: {}", rows);
        new CsvWriter().write(
                Path.of(params.get(InputEnum.FILE_PATH)).getParent().toString(),
                "sumCsv",
                List.of(
                        CsvHeaderEnum.getHeaders()));
    }

    private void setRowValues(List<Map<CsvHeaderEnum, String>> rows) {
        rows.forEach(row -> new CmValueSetter().setValue(row));
    }
}
