package daoimpl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.bson.Document;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientSettings;

import dao.BlogDao;
import dto.Blog;
import dto.Comment;



public class BlogDaoImpl implements BlogDao {

	
	private static MongoClient mongoClient = new MongoClient("localhost", 27017);	
	CodecRegistry pojoCodecRegistry = org.bson.codecs.configuration.CodecRegistries.fromRegistries(MongoClientSettings.getDefaultCodecRegistry(), org.bson.codecs.configuration.CodecRegistries.fromProviders(PojoCodecProvider.builder().automatic(true).build()));
    MongoDatabase mongoDB = mongoClient.getDatabase("mydb").withCodecRegistry(pojoCodecRegistry);
	//private static MongoDatabase mongoDB = mongoClient.getDatabase("mydb");
	
	@Override
	public List<Blog> getAllBlogs() {
		
		MongoCollection<Document> collection = mongoDB.getCollection("students");
		List<Blog> blogs = new ArrayList<>();
		
		MongoCursor<Document> cursor = collection.find().iterator();
		try {
			
			while(cursor.hasNext()) { 
				Document doc = cursor.next();
				Blog blog = new Blog();
				//blog.setId((int)doc.get("_id"));
				blog.setDateCreated((Date)doc.get("dateCreated"));
				blog.setContent((doc.getString("content")));
				blog.setTitle(doc.getString("title"));
				List<Document> comments = (List<Document>) doc.get("comments");
				for(Document document : comments) {
					Comment comment = new Comment();
					comment.setContent(doc.getString("content"));
					comment.setStudentId(doc.getInteger("studentId"));
					blog.getComments().add(comment);
				}
				blogs.add(blog);
			}
			
		} finally {
			cursor.close();
		}
		
		return blogs;
	}

	@Override
	public void insertBlog(Blog blog) {
		
		MongoCollection<Document> collection = mongoDB.getCollection("students");
		
		Document document = new Document("title", blog.getTitle())
				.append("studentId", blog.getStudentId())
				.append("content", blog.getContent())
				.append("dateCreated", blog.getDateCreated())
				.append("comments", blog.getComments());
		
		collection.insertOne(document);
	}

	
	
}
