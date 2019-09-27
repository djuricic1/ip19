package dao;

import java.util.List;

import dto.File;

public interface FileDao {

	List<File> getAllFilesByStudentId(int studentId);
	boolean insertFile(File file);	
	
	
}
