package daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import dao.StudentDao;
import dto.Faculty;
import dto.Student;

public class StudentDaoImpl implements StudentDao{

	private static ConnectionPool connectionPool = ConnectionPool.getConnectionPool();
	private FacultyDaoImpl fdi = new FacultyDaoImpl();
	private static final String SQL_SELECT_ALL = "SELECT * FROM student";
	private static final String SQL_SELECT_BY_ID = "SELECT * FROM student WHERE student.id=?";
	private static final String SQL_INSERT = "INSERT INTO `mydb`.`student` (`name`, `surname`, `username`, `password`, `mail`) VALUES (?, ?, ?, ?, ?)";
	private static final String SQL_UPDATE = "UPDATE `mydb`.`student` SET `name` = ?, `surname` = ?, `username` = ?, `password` = ?, `mail` = ?, `description` = ?, `studyProgram` = ?, `facultyYear` = ?, `faculty_id` = ?,  `lastTimeActive` = ? WHERE (`id` = ?)";
	private static final String SQL_UPDATE_TIME_ACTIVE = "UPDATE student SET lastTimeActive=? WHERE id=?";
	private static final String SQL_SELECT_BY_NAME_AND_USERNAME = "SELECT * FROM student where student.username=? AND student.password=?";
	private static final String SQL_GET_ALL_CONNECTED_STUDENTS  = "(SELECT id from student join connection on id=student_id1 where student_id = ? and typeOfConnection=? ) union (SELECT id from student join connection on id=student_id where student_id1 = ? and typeOfConnection=?)";
	private static final String SQL_GET_ALL_STUDENT_REQUEST = "SELECT id from student join connection on id=student_id1 where student_id = ? and typeOfConnection=?";
	private static final String SQL_SELECT_ALL_BY_FACULTY_ID = "SELECT * FROM student where faculty_id=?";
	
	@Override
	public List<Student> getAllStudents() {
		
		ArrayList<Student> students = new ArrayList<>();
		Connection connection = null;
		ResultSet rs = null;
		Object[] values = {}; 
		try {
			connection = connectionPool.checkOut();
			PreparedStatement pstmt = DAOUtil.prepareStatement(connection, SQL_SELECT_ALL, false, values);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				Student student = new Student();

				Faculty faculty = fdi.getFacultyById(rs.getInt("faculty_id"));
				
				student.setId(rs.getInt("id"));
				student.setName(rs.getString("name"));
				student.setSurname(rs.getString("surname"));
				student.setUsername(rs.getString("username"));
				student.setPassword(rs.getString("password"));
				student.setMail(rs.getString("mail"));
				student.setImage(rs.getBytes("image"));
				student.setStudyProgram(rs.getString("studyProgram"));
				student.setFaculty(faculty);
				student.setDescription(rs.getString("description"));
				student.setFacultyYear(rs.getInt("facultyYear"));
				student.setLastTimeActive(rs.getDate("lastTimeActive"));
				
				students.add(student);
			}
		} catch(Exception ex) {
			ex.printStackTrace();
		} finally {
			connectionPool.checkIn(connection);
		} 
		
		return students;
	}

	@Override
	public Student getStudentyById(int id) {
		Student student = new Student();
		Connection connection = null;
		ResultSet rs = null;
		try {
			connection = connectionPool.checkOut();
			PreparedStatement preparedStatement = connection.prepareStatement(SQL_SELECT_BY_ID);
			preparedStatement.setInt(1, id);
			rs = preparedStatement.executeQuery();
			if(rs.next()) {
				
				Faculty faculty = fdi.getFacultyById(rs.getInt("faculty_id"));
				
				student.setId(rs.getInt("id"));
				student.setName(rs.getString("name"));
				student.setSurname(rs.getString("surname"));
				student.setUsername(rs.getString("username"));
				student.setPassword(rs.getString("password"));
				student.setMail(rs.getString("mail"));
				student.setImage(rs.getBytes("image"));
				student.setStudyProgram(rs.getString("studyProgram"));
				student.setFaculty(faculty);
				student.setDescription(rs.getString("description"));
				student.setFacultyYear(rs.getInt("facultyYear"));
				student.setLastTimeActive(rs.getDate("lastTimeActive"));	
			}
			else {
				// no student for given id
				student = null;
			}	
			
			preparedStatement.close();
			
		} catch(SQLException sql) {
			sql.printStackTrace();
		} finally {
			connectionPool.checkIn(connection);
		}
		return student;
	}

	@Override
	public boolean insertStudent(Student student) {
		Connection connection = null;
		boolean isSuccessful = false;
		
		try {
			connection = connectionPool.checkOut();
		    PreparedStatement preparedStmt = connection.prepareStatement(SQL_INSERT);
		
			preparedStmt.setString(1, student.getName());
			preparedStmt.setString(2, student.getSurname());
			preparedStmt.setString(3, student.getUsername());
			preparedStmt.setString(4, student.getPassword());
			preparedStmt.setString(5, student.getMail());
			
			isSuccessful = preparedStmt.execute();
			
			preparedStmt.close();
			
			isSuccessful = true;
			
		} catch (SQLIntegrityConstraintViolationException e) {
			return false;
		} catch (SQLException e) {
			// TODO add err hadnling for same username 
			e.printStackTrace();
		} finally {
			connectionPool.checkIn(connection);
		}
		return isSuccessful;
	}

	@Override
	public boolean updateStudent(Student student) {
		Connection connection = null;
		boolean isSuccessful = false;
		
		try {
			connection = connectionPool.checkOut();
			PreparedStatement preparedStatement = connection.prepareStatement(SQL_UPDATE);
			
			preparedStatement.setString(1, student.getName());
			preparedStatement.setString(2, student.getSurname());
			preparedStatement.setString(3, student.getUsername());
			preparedStatement.setString(4, student.getPassword());
			preparedStatement.setString(5, student.getMail());
			preparedStatement.setString(6, student.getDescription());
			preparedStatement.setString(7, student.getStudyProgram());
			preparedStatement.setInt(8, student.getFacultyYear());
			preparedStatement.setInt(9, student.getFaculty().getId());
			
			if(student.getLastTimeActive() != null)
				preparedStatement.setTimestamp(10, new java.sql.Timestamp(student.getLastTimeActive().getTime()));
			else 
				preparedStatement.setTimestamp(10, new java.sql.Timestamp(new Date(System.currentTimeMillis()).getTime()));
			
			preparedStatement.setInt(11, student.getId());
			
			preparedStatement.executeUpdate();
			
			preparedStatement.close();
			
			isSuccessful = true;
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			connectionPool.checkIn(connection);
		}
		
		return isSuccessful;
	}

	@Override
	public boolean updateStudentLastTimeActive(Student student) {
		Connection connection = null;
		boolean isSuccessful = false;
		
		try {
			connection = connectionPool.checkOut();
			PreparedStatement preparedStatement = connection.prepareStatement(SQL_UPDATE_TIME_ACTIVE);
			System.out.println();
			preparedStatement.setTimestamp(1, new java.sql.Timestamp(student.getLastTimeActive().getTime()));
			preparedStatement.setInt(2, student.getId());
			
			preparedStatement.executeUpdate();
			preparedStatement.close();
			
			isSuccessful = true;
			
		} catch (SQLException e) {
			// TODO add exception handling
			e.printStackTrace();
		} finally {
			connectionPool.checkIn(connection);
		}
		
		return false;
	}

	@Override
	public Student getStudentByNameAndPassword(String username, String password) {
		Student student = new Student();
		Connection connection = null;
		ResultSet rs = null;
		try {
			connection = connectionPool.checkOut();
			PreparedStatement preparedStatement = connection.prepareStatement(SQL_SELECT_BY_NAME_AND_USERNAME);
			preparedStatement.setString(1, username);
			preparedStatement.setString(2, password);
			rs = preparedStatement.executeQuery();
			if(rs.next()) {
				
				Faculty faculty = fdi.getFacultyById(rs.getInt("faculty_id"));
				
				student.setId(rs.getInt("id"));
				student.setName(rs.getString("name"));
				student.setSurname(rs.getString("surname"));
				student.setUsername(rs.getString("username"));
				student.setPassword(rs.getString("password"));
				student.setMail(rs.getString("mail"));
				student.setImage(rs.getBytes("image"));
				student.setStudyProgram(rs.getString("studyProgram"));
				student.setFaculty(faculty);
				student.setDescription(rs.getString("description"));
				student.setFacultyYear(rs.getInt("facultyYear"));
				student.setLastTimeActive(rs.getDate("lastTimeActive"));	
			}
			else {
				// no student for given id
				student = null;
			}	
			
			preparedStatement.close();
			
		} catch(SQLException sql) {
			sql.printStackTrace();
		} finally {
			connectionPool.checkIn(connection);
		}
		return student;
	}

	@Override
	public List<Student> getAllStudentsConnected(Student student) {
	
		Connection connection = null;
		ResultSet rs = null;
		List<Student> connectedStudents = new ArrayList<>();
		
		try { 
			connection = connectionPool.checkOut();
			PreparedStatement pstmt = connection.prepareStatement(SQL_GET_ALL_CONNECTED_STUDENTS);
			pstmt.setInt(1, student.getId());
			pstmt.setInt(2, 0);
			pstmt.setInt(3, student.getId());
			pstmt.setInt(4, 0);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				Student temp = getStudentyById(rs.getInt("id"));
				connectedStudents.add(temp);
			}
			pstmt.close();
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	
		return connectedStudents;
	}
	
	@Override
	public List<Student> getAllStudentRequests(Student student) {
		
		Connection connection = null;
		ResultSet rs = null;
		List<Student> studentRequests = new ArrayList<>();
		
		try { 
			connection = connectionPool.checkOut();
			PreparedStatement pstmt = connection.prepareStatement(SQL_GET_ALL_STUDENT_REQUEST);
			pstmt.setInt(1, student.getId());
			pstmt.setInt(2, 1);
			
			rs = pstmt.executeQuery();
			while(rs.next()) {
				Student temp = getStudentyById(rs.getInt("id"));
				studentRequests.add(temp);
			}
			pstmt.close();
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	
		return studentRequests;
	}
	
	@Override
	public List<Student> getAllStudentsByFacultyId(int facultyId) {
		
		ArrayList<Student> students = new ArrayList<>();
		Connection connection = null;
		ResultSet rs = null;
		try {
			connection = connectionPool.checkOut();
			PreparedStatement pstmt = connection.prepareStatement(SQL_SELECT_ALL_BY_FACULTY_ID);
			pstmt.setInt(1, facultyId);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				Student student = new Student();

				Faculty faculty = fdi.getFacultyById(rs.getInt("faculty_id"));
				
				student.setId(rs.getInt("id"));
				student.setName(rs.getString("name"));
				student.setSurname(rs.getString("surname"));
				student.setUsername(rs.getString("username"));
				student.setPassword(rs.getString("password"));
				student.setMail(rs.getString("mail"));
				student.setImage(rs.getBytes("image"));
				student.setStudyProgram(rs.getString("studyProgram"));
				student.setFaculty(faculty);
				student.setDescription(rs.getString("description"));
				student.setFacultyYear(rs.getInt("facultyYear"));
				student.setLastTimeActive(rs.getDate("lastTimeActive"));
				
				students.add(student);
			}
			pstmt.close();
			
			
		} catch(Exception ex) {
			ex.printStackTrace();
		} finally {
			connectionPool.checkIn(connection);
		} 
		return students;
	}
}
