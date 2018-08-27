package cuex.web.service.impl;

import cuex.web.model.Symbol;
import cuex.web.service.CSVProcessor;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class CSVProcessorImpl implements CSVProcessor {

    private CSVParser parser;

    public CSVProcessorImpl(String csv) {
        try {
            parser = getParser(new FileReader(csv));
        } catch (FileNotFoundException e) {
            throw new IllegalStateException("File does not exist", e);
        } catch (IOException e) {
            throw new IllegalStateException("Could not parse symbols", e);
        }
    }

    private CSVParser getParser(FileReader in) throws IOException {
        return CSVFormat.DEFAULT
                .withHeader("code", "name")
                .withFirstRecordAsHeader()
                .parse(in);
    }

    @Override
    public List<Symbol> getSymbols() {
        List<CSVRecord> records;
        try {
            records = parser.getRecords();
        } catch (IOException e) {
            throw new IllegalStateException("Could not parse symbols", e);
        }

        return records.stream()
                .map(record -> new Symbol(record.get("code").trim(), record.get("name").trim()))
                .collect(Collectors.toList());
    }
}
