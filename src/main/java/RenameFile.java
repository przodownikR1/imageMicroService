import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import lombok.SneakyThrows;

public class RenameFile {
   @SneakyThrows   
    public void renameFilesInPath(String pathStr){
        Path path = Paths.get(pathStr);
        Files.walk(path).filter(Files::isRegularFile).forEach(t -> {
            String newFileName = t.toFile().getName().replace('_', '-');
            t.toFile().renameTo(new File(path.toString() + "/" + newFileName));

        });
    }

   
}
