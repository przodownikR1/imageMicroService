package pl.java.scalatech.tools;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Stream;

public final class  FileUtils {

    public static List<File> findFilesIn(File rootDir, Predicate<File> predicate) {
        ArrayList<File> collected = new ArrayList<>();
        walk(rootDir, predicate, collected);
        return collected;
    }

    private static void walk(File dir, Predicate<File> filterFunction, List<File> collected) {
        Stream.of(listOnlyWhenDirectory(dir))
                .forEach(file -> walk(file, filterFunction, addAndReturn(collected, file, filterFunction)));
    }

    private static File[] listOnlyWhenDirectory(File dir) {
        return dir.isDirectory() ? dir.listFiles() : new File[]{};
    }

    private static List<File> addAndReturn(List<File> files, File toAdd, Predicate<File> filterFunction) {
        if (filterFunction.test(toAdd)) {
            files.add(toAdd);
        }
        return files;
    }
    
    public static Stream<Path> filesInDir(Path dir, Predicate<String> filter) {
        return listFiles(dir)
                .flatMap(path -> path.toFile().isDirectory() 
                        ? filesInDir(path, filter) 
                        : Stream.of(path)
                ).filter(p -> filter.test(p.getFileName().toString()));
    }
     
        private static Stream<Path> listFiles(Path dir) {
            try {
                return Files.list(dir);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        
    
}
