package vttp.paf.day27.controllers;


import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import vttp.paf.day27.models.Comment;
import vttp.paf.day27.models.Review;
import vttp.paf.day27.repositories.ReviewRepository;

@Controller
@RequestMapping(path = "/review", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
public class ReviewController {
    
    @Autowired
    private ReviewRepository rRepo;

    @PostMapping()
    public String postReviews(@RequestBody MultiValueMap<String, String> form ){

        String user = form.getFirst("user");
        int rating = Integer.parseInt(form.getFirst("rating"));
        String comment = form.getFirst("comment");
        int id = Integer.parseInt(form.getFirst("id"));
        String posted = form.getFirst("date");
        String name = form.getFirst("name");

        Review c = new Review();
        c.setUser(user);
        c.setRating(rating);
        c.setName(name);
        c.setId(id);
        c.setDate(posted);
        c.setComment(comment);
        
        System.out.println("Review created" + c.getComment() + c.getDate() + c.getId() + c.getRating() + c.getUser());
        Review result = rRepo.insertReview(c);

        if(null == result)
            return "error";

        return "result";
    }


    @PutMapping(path = "/{review_id}")
    public String updateReview(@PathVariable(value = "review_id") String reviewId, @RequestBody Comment update){
        System.out.println(reviewId);

        System.out.println(update.getRating());
        if(10 <update.getRating() || update.getRating() <= 0 ){
            return "ratingError";
        }
        Comment c = new Comment();
        c.setComment(update.getComment());
        c.setPosted(DateTime.now().toString());
        c.setRating(update.getRating());

        Long updated= rRepo.updateReview(reviewId, c);


        //Comment c = new Comment();
        //c.setComment(update.getString("comment"));
        //c.setRating(update.getInt("rating"));
        //c.setPosted(DateTime.now().toString());

        if(updated <1){
            return "error";
        }
        

        return "result";
    }
}
