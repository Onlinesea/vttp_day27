package vttp.paf.day27.repositories;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import com.mongodb.client.result.UpdateResult;

import vttp.paf.day27.controllers.ReviewController;
import vttp.paf.day27.models.Comment;
import vttp.paf.day27.models.Review;

@Repository
public class ReviewRepository {


    @Autowired
    private MongoTemplate template;

    // Stating the naem of the database so it is easier to change
    final static String REVIEW_COLLECTION = "reviews";

    public Review insertReview (Review r){

        return template.insert(r,REVIEW_COLLECTION);
    }

    public Long updateReview(String reviewId, Comment c){
        Query q = Query.query(Criteria.where("_id").is(reviewId));

        Update update = new Update().push("edited", c);
        UpdateResult result = template.updateMulti(q, update, Document.class,REVIEW_COLLECTION);
        System.out.printf("Document updated: %d\n", result.getModifiedCount());

        return result.getModifiedCount();
    }
}
