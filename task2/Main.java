package files.task2;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Main {
    public static void main(String[] args) {
        String pathSaveGames = "C://Games/savegames";
        GameProgress gameProgress1 = new GameProgress(100, 3, 1, 20.3);
        GameProgress gameProgress2 = new GameProgress(50, 2, 4, 330.4);
        GameProgress gameProgress3 = new GameProgress(77, 6, 8, 1003.9);

        saveGame(pathSaveGames + "/save1.dat", gameProgress1);
        saveGame(pathSaveGames + "/save2.dat", gameProgress2);
        saveGame(pathSaveGames + "/save3.dat", gameProgress3);

        if (zipFiles(pathSaveGames + "/saves.zip",
                new String[]{pathSaveGames + "/save1.dat",
                        pathSaveGames + "/save2.dat",
                        pathSaveGames + "/save3.dat"})) {
            System.out.println(pathSaveGames + "/saves.zip успешно заархиворован");
        } else {
            System.out.println(pathSaveGames + "/saves.zip не заархивирован");
        }

        File save1 = new File(pathSaveGames + "/save1.dat");
        File save2 = new File(pathSaveGames + "/save2.dat");
        File save3 = new File(pathSaveGames + "/save3.dat");

        deleteFile(save1);
        deleteFile(save2);
        deleteFile(save3);
    }

    private static void saveGame(String pathSaveFile, GameProgress gameProgress) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(pathSaveFile))) {
            oos.writeObject(gameProgress);
        }
        catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    private static boolean zipFiles(String pathZip, String[] pathsFiles) {
        try (ZipOutputStream zipOutputStream = new ZipOutputStream(new FileOutputStream(pathZip))) {
            for (String pathFile : pathsFiles) {
                try (FileInputStream fis = new FileInputStream(pathFile)) {
                    ZipEntry entry =
                            new ZipEntry(pathFile.substring(pathFile.lastIndexOf('/') + 1));
                    zipOutputStream.putNextEntry(entry);
                    byte[] buffer = new byte[fis.available()];
                    fis.read(buffer);
                    zipOutputStream.write(buffer);
                    zipOutputStream.closeEntry();
                }
                catch (Exception ex) {
                    System.out.println(ex.getMessage());
                    return false;
                }
            }
        }
        catch (Exception ex) {
            System.out.println(ex.getMessage());
            return false;
        }

        return true;
    }

    private static void deleteFile(File file) {
        if (file.delete()) {
            System.out.println("Файл " + file.getName() + " удален");
        }
    }
}
