package daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import dao.FacultyDao;
import dto.Faculty;

public class FacultyDaoImpl implements FacultyDao {

	private static ConnectionPool connectionPool = ConnectionPool.getConnectionPool();
	private static final String SQL_GET_BY_ID = "SELECT * FROM faculty WHERE id=?";
	
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
