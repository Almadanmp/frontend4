package pt.ipp.isep.dei.project.io.ui.reader;

import java.io.IOException;

public interface Reader {
    Object readFile(String path) throws IOException;
}
