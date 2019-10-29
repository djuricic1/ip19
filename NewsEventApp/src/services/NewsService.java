package services;

import java.util.List;

import dao.NewsDao;
import daoimpl.NewsDaoImpl;
import daoimpl.NewsLikesDaoImpl;
import dto.News;
import dto.NewsLike;

public class NewsService {
	
	NewsLikesDaoImpl nldi = new NewsLikesDaoImpl();
	NewsDao nd = new NewsDaoImpl();
	
	
	public boolean addRating(NewsLike newsLike) {
		return nldi.insert(newsLike);
	}
	
	public List<News> getAll() {
		return nd.getAll();
	}
	
	
}
