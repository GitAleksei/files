package files.task3;

import files.task2.GameProgress;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class Main {
    public static void main(String[] args) {
        String pathSaveGames = "C://Games/savegames";

        if (openZip(pathSaveGames + "/saves.zip", pathSaveGames)) {
            System.out.println(pathSaveGames + "/saves.zip архив распакован\n");
        } else {
            System.out.println(pathSaveGames + "/saves.zip архив не распакован\n");
        }

        GameProgress gameProgress = openProgress(pathSaveGames + "/save3.dat");

        System.out.println(gameProgress);
    }

    public static boolean openZip(String pathZip, String pathOpenedZip) {
        try (ZipInputStream zin = new ZipInputStream(new FileInputStream(pathZip))) {
            ZipEntry entry;
            String name;
            while ((entry = zin.getNextEntry()) != null) {
                name = entry.getName();
                try (FileOutputStream fos = new FileOutputStream(pathOpenedZip + "/" + name)) {
                    for (int c = zin.read(); c != -1 ; c = zin.read()) {
                        fos.write(c);
                    }
                    fos.flush();
                    zin.closeEntry();
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

    public static GameProgress openProgress(String pathSave) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(pathSave))) {
            return (GameProgress) ois.readObject();
        }
        catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

        return null;
    }
}
