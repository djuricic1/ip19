package daoimpl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.bson.Document;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;
import org.bson.types.ObjectId;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.BasicDBObject;
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
				ObjectId objid = doc.getObjectId("_id");
				//System.out.println(objid.toString());
				blog.setId(objid.toString());
				blog.setStudentId(doc.getInteger("studentId"));
				blog.setDateCreated((Date)doc.get("dateCreated"));
				blog.setContent((doc.getString("content")));
				blog.setTitle(doc.getString("title"));
				List<Document> comments = (List<Document>) doc.get("comments");
				for(Document document : comments) {
					Comment comment = new Comment();
					comment.setContent(document.getString("content"));
					
					comment.setStudentId(document.getInteger("studentId"));
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

	/*
	 * accepts id of blog that needs to be changed and list of comments
	 */
	@Override
	public void addComment(String blogId, String newComment, int studentId) {
		
		MongoCollection<Document> collection = mongoDB.getCollection("students");
		//MongoCollection<Document> myCollection = database.getCollection("myCollection");
		MongoCursor<Document> cursor = collection.find().iterator();
		Document doc= null;
		while(cursor.hasNext()) {
			 doc = cursor.next();
			 if( doc.getObjectId("_id").toString().equals(blogId)) 
				 break;
		}
	
		List<Document> comments = (List<Document>) doc.get("comments");
		List<Comment> temp = new ArrayList<Comment>();
		for(Document document : comments) {
			Comment comment = new Comment();
			comment.setContent(doc.getString("content"));
			comment.setStudentId(doc.getInteger("studentId"));
			temp.add(comment);
		}
		Comment comment = new Comment();
		comment.setContent(newComment);
		comment.setStudentId(studentId);
		temp.add(comment);
		
		BasicDBObject newDocument = new BasicDBObject();
		newDocument.append("$set", new BasicDBObject().append("comments", temp));


		collection.updateOne(doc, newDocument);
	}
	
}
