package dao;

import java.util.ArrayList;

import dto.News;

public interface NewsDao {
	
	ArrayList<News> getAll();
	boolean addNews(News news);
	
}
