package model;

import java.io.IOException;

public interface Saveable {

    void save(String txtFile) throws IOException;
}
