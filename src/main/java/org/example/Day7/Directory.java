package org.example.Day7;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class Directory extends File{
    private final Directory parent;
    private final Map<String, File> files = new HashMap<>();
    public Directory(Directory parent, String name) {
        super(name, 0);
        this.parent = parent;
    }

    public void addFile(File file) {
        files.put(file.getName(), file);
    }


    public long size() {
        long totalSize = 0;
        for (File file : files.values()) {
            totalSize += file.size();
        }
        return totalSize;
    }

    public Directory getParent() {
        return parent;
    }

    public Directory getDirectory(String name) {
        return (Directory) files.get(name);
    }
}
