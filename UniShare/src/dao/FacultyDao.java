package dao;

import java.util.List;

import dto.Faculty;

public interface FacultyDao {

	Faculty getFacultyById(int id);
	List<Faculty> getAllFaculties();
}
