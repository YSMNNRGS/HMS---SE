/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

import java.io.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet(urlPatterns = {"/RECEPTION"})
public class RECEPTION extends HttpServlet {

    private static final String FILE_PATH = "C:\\HMS\\patients.txt"; // Update path as needed

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        String action = request.getParameter("action");

        if ("register".equalsIgnoreCase(action)) {
            registerPatient(request, out);
        } else if ("notify".equalsIgnoreCase(action)) {
            notifyDoctor(request, out);
        } else if ("update".equalsIgnoreCase(action)) {
            updatePatientRecord(request, out);
        } else {
            out.println("<html><body>");
            out.println("<h2>Invalid action!</h2>");
            out.println("</body></html>");
        }
    }

    private void registerPatient(HttpServletRequest request, PrintWriter out) {
        String name = request.getParameter("name");
        String age = request.getParameter("age");
        String contact = request.getParameter("contact");

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_PATH, true))) {
            bw.write(name + "," + age + "," + contact);
            bw.newLine();

            out.println("<html><body>");
            out.println("<h2>Patient Registered Successfully</h2>");
            out.println("<p>Name: " + name + "</p>");
            out.println("<p>Notification sent to doctor.</p>");
            out.println("</body></html>");
        } catch (IOException e) {
            out.println("<html><body><p>Error saving patient record.</p></body></html>");
        }
    }

    private void notifyDoctor(HttpServletRequest request, PrintWriter out) {
        String patientName = request.getParameter("name");
        String doctorName = request.getParameter("doctor");
        String message = request.getParameter("message");

        // For now, just display a confirmation — you can add actual notification logic later
        out.println("<html><body>");
        out.println("<h2>Notification Sent</h2>");
        out.println("<p>Doctor " + doctorName + " notified about patient " + patientName + ".</p>");
        out.println("<p>Message: " + message + "</p>");
        out.println("</body></html>");
    }

    private void updatePatientRecord(HttpServletRequest request, PrintWriter out) {
        String patientName = request.getParameter("name");
        String newAge = request.getParameter("age");
        String newContact = request.getParameter("contact");

        // For simplicity, read all patients, update the matching one, and rewrite the file
        File file = new File(FILE_PATH);
        if (!file.exists()) {
            out.println("<html><body><p>Patient records not found.</p></body></html>");
            return;
        }

        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            StringBuilder sb = new StringBuilder();
            String line;
            boolean updated = false;

            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length >= 3 && parts[0].equalsIgnoreCase(patientName)) {
                    // Update this patient's record
                    sb.append(patientName).append(",").append(newAge).append(",").append(newContact).append("\n");
                    updated = true;
                } else {
                    sb.append(line).append("\n");
                }
            }
            br.close();

            if (updated) {
                BufferedWriter bw = new BufferedWriter(new FileWriter(file));
                bw.write(sb.toString());
                bw.close();

                out.println("<html><body>");
                out.println("<h2>Patient Record Updated Successfully</h2>");
                out.println("<p>Name: " + patientName + "</p>");
                out.println("<p>New Age: " + newAge + "</p>");
                out.println("<p>New Contact: " + newContact + "</p>");
                out.println("</body></html>");
            } else {
                out.println("<html><body>");
                out.println("<h2>Patient Not Found</h2>");
                out.println("</body></html>");
            }
        } catch (IOException e) {
            out.println("<html><body><p>Error updating patient record.</p></body></html>");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<h2>This servlet only handles POST requests from the form.</h2>");
    }

    @Override
    public String getServletInfo() {
        return "Reception module servlet";
    }
}


/**
 *
 * @author XPS 9365
 */
