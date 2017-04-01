package asw.persistence.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import asw.persistence.repositories.CommentRepository;
import asw.model.impl.Comment;

@Service
public class CommentService {

	@Autowired
    private CommentRepository cR;

    public void createCommnet(Comment comment) {
        cR.insert(comment);
    }
	
}
