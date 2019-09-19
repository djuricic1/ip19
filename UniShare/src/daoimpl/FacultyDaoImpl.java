package daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dao.FacultyDao;
import dto.Faculty;

public class FacultyDaoImpl implements FacultyDao {

	private static ConnectionPool connectionPool = ConnectionPool.getConnectionPool();
	private static final String SQL_GET_BY_ID = "SELECT * FROM faculty WHERE id=?";
	private static final String SQL_GET_ALL = "SELECT * FROM faculty";
	private static final String SQL_GET_BY_NAME = "SELECT * FROM faculty WHERE name=?";
	
	@Override
	public Faculty getFacultyById(int id) {
		Connection connection = null;
		ResultSet rs = null;
		Faculty rV = new Faculty();
		try { 
			connection = connectionPool.checkOut();
			PreparedStatement pstmt = connection.prepareStatement(SQL_GET_BY_ID);
			pstmt.setInt(1, id);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				rV.setId(rs.getInt("id"));
				rV.setName(rs.getString("name"));
			}
			else {
				return null;
			}
			pstmt.close();
		} catch(SQLException exception) {
			exception.printStackTrace();
		} finally {
			connectionPool.checkIn(connection);
		}
		return rV;
	}

	@Override
	public List<Faculty> getAllFaculties() {
		Connection connection = null;
		ResultSet rs = null;
		List<Faculty> faculties = new ArrayList<Faculty>();
		try { 
			connection = connectionPool.checkOut();
			PreparedStatement pstmt = connection.prepareStatement(SQL_GET_ALL);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				Faculty f = new Faculty();
				f.setId(rs.getInt("id"));
				f.setName(rs.getString("name"));
				faculties.add(f);
			}
			pstmt.close();
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			connectionPool.checkIn(connection);
		}
		return faculties;
	}

	@Override
	public Faculty getFacultyByName(String facultyName) {
		Connection connection = null;
		ResultSet rs = null;
		Faculty rV = new Faculty();
		try { 
			connection = connectionPool.checkOut();
			PreparedStatement pstmt = connection.prepareStatement(SQL_GET_BY_NAME);
			pstmt.setString(1, facultyName);
			rs = pstmt.executeQuery();
			rs.next();
			rV.setId(rs.getInt("id"));
			rV.setName(rs.getString("name"));
			pstmt.close();
		} catch(SQLException exception) {
			exception.printStackTrace();
		} finally {
			connectionPool.checkIn(connection);
		}
		return rV;
	}

}
