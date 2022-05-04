package org.example.helper;

import java.io.IOException;
import java.util.List;

public interface DataSetService {
    List<String> getDataSet() throws IOException;
}
