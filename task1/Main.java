package files.task1;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;

public class Main {
    private static final String pathGames = "C://Games";
    private static final StringBuilder logTemp = new StringBuilder();

    public static void main(String[] args) {
        File dirSrc = new File(pathGames + "/src");
        File dirRes = new File(pathGames + "/res");
        File dirSaveGames = new File(pathGames + "/savegames");
        File dirTemp = new File(pathGames + "/temp");

        mkdir(dirSrc);
        mkdir(dirRes);
        mkdir(dirSaveGames);
        mkdir(dirTemp);

        File dirMain = new File(dirSrc.getPath() + "/main");
        File dirTest = new File(dirSrc.getPath() + "/test");

        mkdir(dirMain);
        mkdir(dirTest);

        File fileMain = new File(dirMain, "Main.java");
        File fileUtils = new File(dirMain, "Utils.java");

        createNewFile(fileMain);
        createNewFile(fileUtils);

        File dirDrawables = new File(dirRes.getPath() + "/drawables");
        File dirVectors = new File(dirRes.getPath() + "/vectors");
        File dirIcons = new File(dirRes.getPath() + "/icons");

        mkdir(dirDrawables);
        mkdir(dirVectors);
        mkdir(dirIcons);

        File fileTemp =  new File(dirTemp, "temp.txt");

        createNewFile(fileTemp);

        try (FileWriter fw = new FileWriter(fileTemp)) {
            fw.write(logTemp.toString());
            fw.flush();
        }
        catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    private static void mkdir(File dir) {
        logTemp.append(new Date()).append("    ");
        if (dir.mkdir()) {
            logTemp.append("Создан каталог ").append(dir.getAbsolutePath()).append("\n");
        } else {
            logTemp.append("Не создан каталог ").append(dir.getAbsolutePath()).append("\n");
        }
    }

    private static void createNewFile(File file) {
        logTemp.append(new Date()).append("    ");
        try {
            if (file.createNewFile()) {
                logTemp.append("Создан файл ").append(file.getAbsolutePath()).append("\n");
            } else {
                logTemp.append("Не создан файл ").append(file.getAbsolutePath()).append("\n");
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
