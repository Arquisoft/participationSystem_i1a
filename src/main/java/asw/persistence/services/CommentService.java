package asw.persistence.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import asw.persistence.repositories.CommentRepository;
import asw.model.impl.Comment;

@Service
public class CommentService {

	@Autowired
    private CommentRepository cR;

    public void createComment(Comment comment) {
        cR.insert(comment);
    }
    
    public void deleteComment(Comment comment) {
    	cR.delete(comment);
    }
	
    public List<Comment> getAllComments() {
		return cR.findAll();
	}
}
