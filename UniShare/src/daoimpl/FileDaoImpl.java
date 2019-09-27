package daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import dao.FileDao;
import dto.File;
import dto.Post;

public class FileDaoImpl implements FileDao {
	
	private static ConnectionPool connectionPool = ConnectionPool.getConnectionPool();
	private static final String SQL_INSERT = "INSERT INTO file (description, file, student_id) VALUES (?, ?, ?)"; 
	private static final String SQL_GET_ALL_BY_STUDENT_ID = "SELECT * FROM file WHERE student_id=?";
	
	@Override
	public List<File> getAllFilesByStudentId(int studentId) {
		Connection connection = null;
		ResultSet rs = null;
		List<File> files = new ArrayList<>();
		try { 
			connection = connectionPool.checkOut();
			PreparedStatement pstmt = connection.prepareStatement(SQL_GET_ALL_BY_STUDENT_ID);
			pstmt.setInt(1, studentId);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				File f = new File();
				f.setDescription(rs.getString("description"));
				f.setId(rs.getInt("id"));
				f.setPath(rs.getString("file"));
				f.setStudentId(studentId);
				files.add(f);
			}
			pstmt.close();
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			connectionPool.checkIn(connection);
		}
		return files;
	}

	@Override
	public boolean insertFile(File file) {
		
		Connection connection = null;
		boolean isSuccessful = false;
		
		try {
			connection = connectionPool.checkOut();
		    PreparedStatement preparedStmt = connection.prepareStatement(SQL_INSERT);
			preparedStmt.setString(1, file.getDescription());	
			preparedStmt.setString(2, file.getPath());		
			preparedStmt.setInt(3, file.getStudentId());
			
			preparedStmt.execute();
			preparedStmt.close();
			isSuccessful = true;
			
		} catch (SQLIntegrityConstraintViolationException e) {
			return false;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			connectionPool.checkIn(connection);
		}
		return isSuccessful;
	}

}
