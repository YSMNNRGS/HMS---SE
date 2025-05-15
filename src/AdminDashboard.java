import java.io.*;

public class AdminDashboard {
    private final String USERS_FILE = "users.txt";

    // 1. Create User
    public void createUser(String id, String name, String role, String password) {
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(USERS_FILE, true));
            String line = id + "|" + name + "|" + password + "|" + role;
            bw.write(line);
            bw.newLine();
            bw.close();
            System.out.println("User created successfully.");
        } catch (Exception e) {
            System.out.println("Error creating user.");
        }
    }

    // 2. Delete User
    public void deleteUser(String id) {
        try {
            BufferedReader br = new BufferedReader(new FileReader(USERS_FILE));
            BufferedWriter bw = new BufferedWriter(new FileWriter("temp.txt"));

            String line;
            while ((line = br.readLine()) != null) {
                if (!line.startsWith(id + "|")) {
                    bw.write(line);
                    bw.newLine();
                }
            }

            br.close();
            bw.close();

            File original = new File(USERS_FILE);
            File temp = new File("temp.txt");

            original.delete();
            temp.renameTo(original);

            System.out.println("User deleted successfully.");
        } catch (Exception e) {
            System.out.println("Error deleting user.");
        }
    }

    // 3. Backup System (copy all .txt files to "backup" folder)
    public void backupSystem() {
        try {
            File folder = new File(".");
            File backupFolder = new File("backup");

            if (!backupFolder.exists()) {
                backupFolder.mkdir();
            }

            File[] files = folder.listFiles();
            for (File file : files) {
                if (file.getName().endsWith(".txt")) {
                    FileInputStream in = new FileInputStream(file);
                    FileOutputStream out = new FileOutputStream(new File(backupFolder, file.getName()));

                    int b;
                    while ((b = in.read()) != -1) {
                        out.write(b);
                    }

                    in.close();
                    out.close();
                }
            }

            System.out.println("System backed up.");
        } catch (Exception e) {
            System.out.println("Error backing up system.");
        }
    }
}

