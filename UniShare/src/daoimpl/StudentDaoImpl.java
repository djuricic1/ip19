package daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
	private static final String SQL_INSERT = "INSERT INTO `mydb`.`student` (`name`, `surname`, `username`, `password`, `mail`, `faculty_id`) VALUES (?, ?, ?, ?, ?, ?)";
	private static final String SQL_UPDATE = "UPDATE `mydb`.`student` SET `name` = ?, `surname` = ?, `username` = ?, `password` = ?, `mail` = ?, `description` = ?, `studyProgram` = ?, `facultyYear` = ?, `faculty_id` = ?,  `lastTimeActive` = ? WHERE (`id` = ?)";
	private static final String SQL_UPDATE_TIME_ACTIVE = "UPDATE student SET lastTimeActive=? WHERE id=?";
	
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
			preparedStmt.setInt(6, student.getFaculty().getId());
			
			isSuccessful = preparedStmt.execute();
			
			preparedStmt.close();
			
			isSuccessful = true;
			
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

	

}
