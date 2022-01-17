/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package examples;

import javax.servlet.http.HttpServlet;
import java.sql.Array;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class DbHandler extends HttpServlet {

    int usersize;
    int instrumentSize;
    int rentalSize;
    int allRentalSize;
    int lessonSize;
    int appointmentSize;
    String studentID;
    Question[] questions;
    String[] previousResults;
    Person[] students;
    Personal_information p_info;
    Instrument[] instruments;
    Rental[] rentals;
    Rental[] allRentals;
    GroupLesson[] lessons;
    Appointment[] appointments;

    public Person[] getAllStudents() throws Exception {

        Statement stmt = createStatement();
        ResultSet student = stmt.executeQuery("SELECT * from student");

        student.last();
        this.usersize = student.getRow();
        student.beforeFirst();

        this.students = new Person[usersize];

        while (student.next()) {

            int row = student.getRow();

            String studentID = student.getString("student_id");
            String person_id = student.getString("person_id");
            String admin = student.getString("admin");

            students[row - 1] = new Person();

            students[row - 1].setStudentID(studentID);
            students[row - 1].setPerson_id(person_id);
            students[row - 1].setAdmin(admin);

        }

        //con.close();
        student.close();

        //return users;
        return students;
    }

    public Personal_information getPerson(String person_id) throws Exception {
        Statement stmt = createStatement();
        String sqlStatement = "SELECT * from person WHERE person_id = " + person_id;
        ResultSet person_info = stmt.executeQuery(sqlStatement);
        person_info.first();
        this.p_info = new Personal_information(person_info.getString("person_id"), person_info.getString("age"), person_info.getString("first_name"), person_info.getString("last_name"), person_info.getString("social_security_number"), person_info.getString("email"), person_info.getString("phone_number"), person_info.getString("zip_code"), person_info.getString("street_name"), person_info.getString("city_name"));
        person_info.close();
        return p_info;
    }

    public GroupLesson[] getAllGroupLessons() throws Exception {
        Statement stmt = createStatement();
        String sqlStatement = "SELECT * FROM group_lesson";
        ResultSet rs = stmt.executeQuery(sqlStatement);

        rs.last();
        this.lessonSize = rs.getRow();
        rs.beforeFirst();

        this.lessons = new GroupLesson[this.lessonSize];

        while (rs.next()) {
            int row = rs.getRow();

            String id = rs.getString("group_lesson_id");
            String date = rs.getString("date");
            String time = rs.getString("time");
            String content = rs.getString("lesson_content");

            lessons[row - 1] = new GroupLesson(id, date, time, content);
        }

        return lessons;
    }

    public Appointment[] getAllAppointment(String studentId) throws Exception {
        Statement stmt = createStatement();
        String sqlStatement = "SELECT * FROM appointment WHERE student_id = " + studentId;
        ResultSet rs = stmt.executeQuery(sqlStatement);

        rs.last();
        this.appointmentSize = rs.getRow();
        rs.beforeFirst();

        this.appointments = new Appointment[this.appointmentSize];

        while (rs.next()) {
            int row = rs.getRow();

            String appointmentId = rs.getString("appointment_id");
            String sId = rs.getString("student_id");
            String groupLessonId = rs.getString("group_lesson_id");

            appointments[row - 1] = new Appointment(appointmentId, sId, groupLessonId);
        }

        return appointments;
    }

    public GroupLesson[] getAllGroupLessonsWithoutMine(String studentId) throws Exception {
        Statement stmt = createStatement();
        String sqlStatement = "SELECT * FROM group_lesson WHERE group_lesson_id NOT IN (SELECT group_lesson_id FROM appointment WHERE student_id = " + studentId + ") ";
        ResultSet rs = stmt.executeQuery(sqlStatement);

        rs.last();
        this.lessonSize = rs.getRow();
        rs.beforeFirst();

        this.lessons = new GroupLesson[this.lessonSize];

        while (rs.next()) {
            int row = rs.getRow();

            String id = rs.getString("group_lesson_id");
            String date = rs.getString("date");
            String time = rs.getString("time");
            String content = rs.getString("lesson_content");

            lessons[row - 1] = new GroupLesson(id, date, time, content);
        }

        return lessons;
    }
    
    public GroupLesson[] getAllGroupLessonsWithMine(String studentId) throws Exception {
        Statement stmt = createStatement();
        String sqlStatement = "SELECT * FROM group_lesson WHERE group_lesson_id IN (SELECT group_lesson_id FROM appointment WHERE student_id = " + studentId + ") ";
        ResultSet rs = stmt.executeQuery(sqlStatement);

        rs.last();
        this.lessonSize = rs.getRow();
        rs.beforeFirst();

        this.lessons = new GroupLesson[this.lessonSize];

        while (rs.next()) {
            int row = rs.getRow();

            String id = rs.getString("group_lesson_id");
            String date = rs.getString("date");
            String time = rs.getString("time");
            String content = rs.getString("lesson_content");

            lessons[row - 1] = new GroupLesson(id, date, time, content);
        }

        return lessons;
    }
    
    public void updateAdmin(String studentId) throws Exception {
        Statement stmt = createStatement();
        String sqlStatement = "UPDATE student SET admin = true WHERE student_id = " + studentId;
        stmt.executeUpdate(sqlStatement);
    }

    public void injectGroupLesson(String date, String time, String content) throws Exception {
        Class.forName("com.mysql.jdbc.Driver");
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/project_database?serverTimezone=UTC", "root", "1234");
        String insertSQL = "INSERT INTO group_lesson VALUES (default, ?, ?, ?)";
        PreparedStatement preparedStatement = con.prepareStatement(insertSQL);
        preparedStatement.setString(1, date);
        preparedStatement.setString(2, time);
        preparedStatement.setString(3, content);
        preparedStatement.executeUpdate();
    }

    public void injectAppointment(String studentId, String groupLessonId) throws Exception {
        Class.forName("com.mysql.jdbc.Driver");
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/project_database?serverTimezone=UTC", "root", "1234");
        Statement stmt3 = createStatement();
        String sqlStatement3 = "INSERT INTO appointment VALUES (default, ?, ?)";
        PreparedStatement preparedStatement2 = con.prepareStatement(sqlStatement3);
        preparedStatement2.setString(1, studentId);
        preparedStatement2.setString(2, groupLessonId);
        preparedStatement2.executeUpdate();
    }

    public Personal_information getPersonWithEmail(String email) throws Exception {
        Statement stmt = createStatement();
        String sqlStatement = "SELECT * from person WHERE email = '" + email + "'";
        ResultSet person_info = stmt.executeQuery(sqlStatement);
        person_info.first();
        this.p_info = new Personal_information(person_info.getString("person_id"), person_info.getString("age"), person_info.getString("first_name"), person_info.getString("last_name"), person_info.getString("social_security_number"), person_info.getString("email"), person_info.getString("phone_number"), person_info.getString("zip_code"), person_info.getString("street_name"), person_info.getString("city_name"));
        person_info.close();
        return p_info;
    }

    public String getStudentIdwithPersonId(String id) throws Exception {
        Statement stmt = createStatement();
        String sqlStatement = "SELECT * from student WHERE person_id = " + id;
        ResultSet student = stmt.executeQuery(sqlStatement);
        student.first();
        this.studentID = student.getString("student_id");
        student.close();
        return studentID;
    }

    public Instrument[] getInstruments() throws Exception {
        Statement stmt = createStatement();
        String sqlStatement = "SELECT * FROM instrument WHERE is_rented = false";
        ResultSet rs = stmt.executeQuery(sqlStatement);

        rs.last();
        this.instrumentSize = rs.getRow();
        rs.beforeFirst();

        this.instruments = new Instrument[this.instrumentSize];

        while (rs.next()) {
            int row = rs.getRow();

            String id = rs.getString("instrument_id");
            String type = rs.getString("type_of_instrument");
            String rental = rs.getString("instrument_rental_per_month");
            boolean isRented = rs.getBoolean("is_rented");

            instruments[row - 1] = new Instrument(id, type, rental, isRented);

        }

        return instruments;
    }

    public void updateInstrumentRentStatus(String instrumentID, String studentID) throws Exception {
        Statement stmt = createStatement();
        String sqlStatement = "UPDATE instrument SET is_rented = true WHERE instrument_id = " + instrumentID;
        Statement stmt2 = createStatement();
        String sqlStatement2 = "INSERT rental (student_id, rental_start_date, rental_end_date, instrument_id) VALUES('" + studentID + "', '2021-01-01', '2021-02-01', '" + instrumentID + "')";
        stmt.executeUpdate(sqlStatement);
        stmt2.executeUpdate(sqlStatement2);
    }

    public Rental[] getAllRentals(String studentID) throws Exception {
        Statement stmt = createStatement();
        String sqlStatement = "SELECT * FROM rental WHERE student_id <> " + studentID;
        ResultSet rs = stmt.executeQuery(sqlStatement);

        rs.last();
        this.allRentalSize = rs.getRow();
        rs.beforeFirst();

        this.allRentals = new Rental[allRentalSize];

        while (rs.next()) {
            int row = rs.getRow();

            String rental_id = rs.getString("rental_id");
            String student_id = rs.getString("student_id");
            String rental_start_date = rs.getString("rental_start_date");
            String rental_end_date = rs.getString("rental_end_date");
            String instrumentId = rs.getString("instrument_id");
            String instrument_name = getInstrumentName(instrumentId);

            allRentals[row - 1] = new Rental(rental_id, student_id, rental_start_date, rental_end_date, instrumentId, instrument_name);

        }

        return allRentals;

    }

    public Rental[] getMyRentals(String studentID) throws Exception {
        Statement stmt = createStatement();
        String sqlStatement = "SELECT * FROM rental WHERE student_id = " + studentID;
        ResultSet rs = stmt.executeQuery(sqlStatement);

        rs.last();
        this.rentalSize = rs.getRow();
        rs.beforeFirst();

        this.rentals = new Rental[rentalSize];

        while (rs.next()) {
            int row = rs.getRow();

            String rental_id = rs.getString("rental_id");
            String student_id = rs.getString("student_id");
            String rental_start_date = rs.getString("rental_start_date");
            String rental_end_date = rs.getString("rental_end_date");
            String instrumentId = rs.getString("instrument_id");
            String instrument_name = getInstrumentName(instrumentId);

            rentals[row - 1] = new Rental(rental_id, student_id, rental_start_date, rental_end_date, instrumentId, instrument_name);

        }
        return rentals;
    }

    public void terminateRental(String instrumentID) throws Exception {
        Statement stmt = createStatement();
        String sqlStatement = "UPDATE instrument SET is_rented = false WHERE instrument_id = " + instrumentID;
        Statement stmt2 = createStatement();
        String sqlStatement2 = "DELETE FROM rental WHERE instrument_id = " + instrumentID;
        stmt.executeUpdate(sqlStatement);
        stmt2.executeUpdate(sqlStatement2);
    }

    public String getStudentIdFromInstrumentId(String instrumentID) throws Exception {
        Statement stmt = createStatement();
        String sqlStatement = "SELECT student_id FROM rental WHERE instrument_id = " + instrumentID;
        ResultSet rs = stmt.executeQuery(sqlStatement);
        rs.first();

        return rs.getString("student_id");

    }

    public String getInstrumentName(String instrument_id) throws Exception {
        Statement stmt = createStatement();
        String sqlStatement = "SELECT * FROM instrument WHERE instrument_id = " + instrument_id;
        ResultSet rs = stmt.executeQuery(sqlStatement);

        rs.first();
        return rs.getString("type_of_instrument");
    }

    public Links[] getLink() throws Exception {

        Statement stmt = createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * from links");

        rs.last();
        int size = rs.getRow();
        rs.beforeFirst();

        Links[] links = new Links[size];

        while (rs.next()) {

            String instrument = rs.getString("instrument");
            String link = rs.getString("page");
            String gif = rs.getString("gif");

            int row = rs.getRow();
            links[row - 1] = new Links(instrument, link, gif);

        }
        return links;
    }

    public void injectUser(String age, String firstName, String lastName, String ssc, String mail, String phone, String zip, String street, String city) throws Exception {
        Class.forName("com.mysql.jdbc.Driver");
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/project_database?serverTimezone=UTC", "root", "1234");
        String insertSQL = "INSERT INTO person VALUES (default, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement preparedStatement = con.prepareStatement(insertSQL);
        preparedStatement.setString(1, age);
        preparedStatement.setString(2, firstName);
        preparedStatement.setString(3, lastName);
        preparedStatement.setString(4, ssc);
        preparedStatement.setString(5, mail);
        preparedStatement.setString(6, phone);
        preparedStatement.setString(7, zip);
        preparedStatement.setString(8, street);
        preparedStatement.setString(9, city);
        preparedStatement.executeUpdate();

        Statement stmt2 = createStatement();
        String sqlStatement2 = "SELECT person_id FROM person WHERE social_security_number = " + ssc;
        ResultSet rs = stmt2.executeQuery(sqlStatement2);
        rs.first();

        Statement stmt3 = createStatement();
        String sqlStatement3 = "INSERT INTO student VALUES (default, ?)";
        PreparedStatement preparedStatement2 = con.prepareStatement(sqlStatement3);
        preparedStatement2.setString(1, rs.getString("person_id"));
        preparedStatement2.executeUpdate();

    }

    public Statement createStatement() throws Exception {

        Class.forName("com.mysql.jdbc.Driver");
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/project_database?serverTimezone=UTC", "root", "1234");

        Statement stmt = con.createStatement(
                ResultSet.TYPE_SCROLL_INSENSITIVE,
                ResultSet.CONCUR_READ_ONLY
        );

        return stmt;
    }
}
