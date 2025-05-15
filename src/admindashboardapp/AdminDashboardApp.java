package admindashboardapp;

public class AdminDashboardApp {

    public static void main(String[] args) {
        AdminDashboard admin = new AdminDashboard();

        // 1. Create User
        admin.createUser("001", "Dr. Ayesha", "doctor", "doc123");

        // 2. Delete User
        // admin.deleteUser("001");

        // 3. Backup System
        // admin.backupSystem();
    }

    private static class AdminDashboard {

        public AdminDashboard() {
        }

        private void createUser(String string, String dr_Ayesha, String doctor, String doc123) {
            throw new UnsupportedOperationException("Not supported yet."); 
        }
    }
}
