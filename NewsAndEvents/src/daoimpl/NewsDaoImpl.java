package daoimpl;

import java.util.ArrayList;

import dao.NewsDao;
import dto.News;

public class NewsDaoImpl implements NewsDao {

	@Override
	public ArrayList<News> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean addNews(News news) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean addRating(int idNews, String idUser, boolean isLike) {
		// TODO Auto-generated method stub
		return false;
	}

}
