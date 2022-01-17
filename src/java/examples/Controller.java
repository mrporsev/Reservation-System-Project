package examples;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Controller extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Creating the session object has been moved to getpost (compared to the lecture)
        RequestDispatcher rd = request.getRequestDispatcher("/home.jsp");
        rd.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        HttpSession session = request.getSession(true);

        ServletContext application = request.getServletContext();
        DbHandler dbh = (DbHandler) application.getAttribute("dbh");
        if (dbh == null) {
            dbh = new DbHandler();
        }

        Person[] students = null;
        Instrument[] instruments = null;
        Personal_information person = null;
        Rental[] allRentals = null;
        GroupLesson[] allLessons = null;
        GroupLesson[] myLessons = null;
        Appointment[] allAppointments = null;
        Rental[] rentals = null;

        try {
            students = dbh.getAllStudents();
            instruments = dbh.getInstruments();
            //allLessons = dbh.getAllGroupLessons();
            //application.setAttribute("lessons", allLessons);
        } catch (Exception ex) {
            out.println("there was an error " + ex);
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }

        if ("login".equals(request.getParameter("studentIDCheck"))) {
            application.setAttribute("students", students);
            //SendMail.send(request.getParameter("username"), request.getParameter("password"), "hello", "world");
            boolean match = false;
            String studentID = "";
            String person_id = "";
            String admin = "";

            for (Person student : students) {
                if (student.getStudentID().equals(request.getParameter("username"))) {
                    try {
                        match = true;
                        studentID = student.getStudentID();
                        person_id = student.getPerson_id();
                        admin = student.getAdmin();
                        Personal_information student_personal_information = dbh.getPerson(person_id);
                        rentals = dbh.getMyRentals(studentID);
                        myLessons = dbh.getAllGroupLessonsWithMine(studentID);
                        application.setAttribute("myLessons", myLessons);
                        application.setAttribute("rentals", rentals);
                        application.setAttribute("personal_info", student_personal_information);
                        application.setAttribute("studentID", studentID);
                        application.setAttribute("admin", admin);
                    } catch (Exception ex) {
                        out.println("there was an error " + ex);
                        Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    break;
                }
            }

            if (match == true) {
                RequestDispatcher rd = request.getRequestDispatcher("/sucessLogin.jsp");
                rd.forward(request, response);
            } else {
                RequestDispatcher rd = request.getRequestDispatcher("/invalidLogin.jsp");
                rd.forward(request, response);
            }
        }

        if ("List all instruments".equals(request.getParameter("listInstruments"))) {
            application.setAttribute("instruments", instruments);
            RequestDispatcher rd = request.getRequestDispatcher("/listInstruments.jsp");
            rd.forward(request, response);
        }

        if ("See all rentals".equals(request.getParameter("listRentals"))) {
            try {
                String studentID = (String) application.getAttribute("studentID");
                allRentals = dbh.getAllRentals(studentID);
                application.setAttribute("allRentals", allRentals);
            } catch (Exception ex) {
                out.println(ex);
            }
            RequestDispatcher rd = request.getRequestDispatcher("/allRentals.jsp");
            rd.forward(request, response);
        }

        if (request.getParameter("rentInstrument") != null) {
            try {
                dbh.updateInstrumentRentStatus(request.getParameter("rentInstrument"), (String) application.getAttribute("studentID"));
                instruments = dbh.getInstruments();
            } catch (Exception ex) {
                out.println(ex);
            }
            application.setAttribute("instruments", instruments);
            RequestDispatcher rd = request.getRequestDispatcher("/listInstruments.jsp");
            rd.forward(request, response);
        }

        if (request.getParameter("terminateInstrument") != null) {

            try {
                String studentID = dbh.getStudentIdFromInstrumentId(request.getParameter("terminateInstrument"));
                dbh.terminateRental(request.getParameter("terminateInstrument"));
                rentals = dbh.getMyRentals(studentID);
                application.setAttribute("rentals", rentals);
            } catch (Exception ex) {
                out.println(ex);
            }
            RequestDispatcher rd = request.getRequestDispatcher("/sucessLogin.jsp");
            rd.forward(request, response);
        }

        if ("registerVal".equals(request.getParameter("register"))) {
            try {
                dbh.injectUser(request.getParameter("registerbirth"), request.getParameter("registerFirstName"), request.getParameter("registerLastName"), request.getParameter("registerSSN"), request.getParameter("registerMail"), request.getParameter("registerPhone"), request.getParameter("registerZip"), request.getParameter("registerStreet"), request.getParameter("registerCity"));
            } catch (Exception ex) {
                out.println(ex);
            }
            RequestDispatcher rd = request.getRequestDispatcher("/home.jsp");
            rd.forward(request, response);
        }

        /**
         * Ta bort den här och länka direkt i html senare
         */
        if ("Forgot my studentID".equals(request.getParameter("forgotID"))) {
            RequestDispatcher rd = request.getRequestDispatcher("/forgotStudentId.jsp");
            rd.forward(request, response);
        }

        if ("sendEmail".equals(request.getParameter("email"))) {
            String studentID = "";
            try {
                person = dbh.getPersonWithEmail(request.getParameter("emailValue"));
                studentID = dbh.getStudentIdwithPersonId(person.getPerson_id());
                if (person.getEmail() != null) {
                    SendMail.send(person.getEmail(), studentID, request.getParameter("passwordValue"));
                }
                //SendMail.send(person.getEmail(), studentID);
            } catch (Exception e) {
                out.print(e);
            }

            RequestDispatcher rd = request.getRequestDispatcher("/home.jsp");
            rd.forward(request, response);
        }

        if ("Make appointment".equals(request.getParameter("makeAppointment"))) {
            try {
                dbh.injectGroupLesson(request.getParameter("date"), request.getParameter("time"), request.getParameter("content"));
            } catch (Exception ex) {
                out.println(ex);
            }

            RequestDispatcher rd = request.getRequestDispatcher("/sucessLogin.jsp");
            rd.forward(request, response);
        }

        if ("Find appointment".equals(request.getParameter("findAppointment"))) {
            try {
                allLessons = dbh.getAllGroupLessonsWithoutMine((String) application.getAttribute("studentID"));
                application.setAttribute("lessons", allLessons);
                RequestDispatcher rd = request.getRequestDispatcher("/findAppointment.jsp");
                rd.forward(request, response);
            } catch (Exception ex) {
                out.println(ex);
            }
        }

        if ("Make admin".equals(request.getParameter("makeAdmin"))) {
            try {
                dbh.updateAdmin(request.getParameter("newAdminId"));
            } catch (Exception ex) {
                Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
            }

            RequestDispatcher rd = request.getRequestDispatcher("/sucessLogin.jsp");
            rd.forward(request, response);

        }

        if (request.getParameter("bookAppointment") != null) {
            try {
                dbh.injectAppointment((String) application.getAttribute("studentID"), request.getParameter("bookAppointment"));
                //allAppointments = dbh.getAllAppointment((String) application.getAttribute("studentID"));
                allLessons = dbh.getAllGroupLessonsWithoutMine((String) application.getAttribute("studentID"));
                application.setAttribute("lessons", allLessons);
                out.println((String) application.getAttribute("studentID"));
                for (GroupLesson lesson : allLessons) {
                    out.println(lesson.getLessonContent());
                    out.println(lesson.getId());
                    out.println(lesson.getDate());
                    out.println(lesson.getTime());
                }
            } catch (Exception ex) {
                out.print(ex);
            }
            RequestDispatcher rd = request.getRequestDispatcher("/findAppointment.jsp");
            rd.forward(request, response);
        }

        if (request.getParameter("goBack") != null) {
            try {
                myLessons = dbh.getAllGroupLessonsWithMine((String) application.getAttribute("studentID"));
                rentals = dbh.getMyRentals((String) application.getAttribute("studentID"));
                application.setAttribute("myLessons", myLessons);
                application.setAttribute("rentals", rentals);
                RequestDispatcher rd = request.getRequestDispatcher("/sucessLogin.jsp");
                rd.forward(request, response);
            } catch (Exception ex) {
                Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
