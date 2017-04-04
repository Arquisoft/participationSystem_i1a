package asw.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import asw.model.impl.Association;
import asw.model.impl.Comment;
import asw.model.impl.Proposal;
import asw.model.impl.User;
import asw.model.impl.Votable;
import asw.model.impl.Vote;
import asw.model.types.Topic;
import asw.model.types.VoteType;
import asw.persistence.services.CommentService;
import asw.persistence.services.ProposalService;
import asw.producers.KafkaProducer;
import asw.security.MyUserDetails;

@Controller
public class MainController {

    @Autowired
    private KafkaProducer kafkaProducer;
    
    private ProposalService ps;
    private CommentService cs;
    
/**
    @RequestMapping("/")
    public String landing(Model model) {
        model.addAttribute("message", new Message());
        return "index";
    }
    
    @RequestMapping("/send")
    public String send(Model model, @ModelAttribute Message message) {
        kafkaProducer.send("exampleTopic", message.getMessage());
        return "redirect:/";
    }
*/
    
    @RequestMapping("/")
	public ModelAndView landing(Model model) {
		return new ModelAndView("redirect:" + "/login");
	}

	@RequestMapping("/login")
	public String login(Model model) {
		return "login";
	}
	
	@RequestMapping("/loginCheck")
	public String loginCheck(Model model){
		MyUserDetails user = (MyUserDetails) SecurityContextHolder.getContext().
														getAuthentication().getPrincipal();
		
		if (user.getUser().isAdmin()) {
			return "admin";
		}
		model.addAttribute("createProposal", new Proposal());

		return "user";
	}

	@RequestMapping("/user")
	public String userHome(Model model) {
		return "user";
	}
	
	@RequestMapping("/admin")
	public String adminHome(Model model){
		return "admin";
	}
	
	@RequestMapping("/selectProposal/{id}")
	public String selectProposal(Model model, @PathVariable String id) {
		model.addAttribute("p", ps.findProposalById(id));
		model.addAttribute("createComment", new Comment());
		return "proposal";
	}

	@RequestMapping("/upvoteProposal/{id}")
	public String upvoteProposal(Model model, @PathVariable("id") String id) {
		MyUserDetails user = (MyUserDetails) SecurityContextHolder.getContext().
				getAuthentication().getPrincipal();	
		
		Proposal prop = ps.findProposalById(id);
		
		if (prop != null && user != null) {
			vote(user.getUser(), prop, VoteType.POSITIVE);
			ps.updateProposal(prop);
		}
		return "redirect:/selectProposal/" + id;
	}

	@RequestMapping("/downvoteProposal/{id}")
	public String downvoteProposal(Model model, @PathVariable("id") String id) {
		MyUserDetails user = (MyUserDetails) SecurityContextHolder.getContext().
				getAuthentication().getPrincipal();	
		
		Proposal prop = ps.findProposalById(id);
		
		if (prop != null && user != null) {
			vote(user.getUser(), prop, VoteType.NEGATIVE);
			ps.updateProposal(prop);
		}
		return "redirect:/selectProposal/" + id;
	}
	
	private void vote(User user, Votable p, VoteType vt){
		@SuppressWarnings("unused")
		Vote v = new Vote(user, p, vt);
	}

	@RequestMapping("/upvoteComment/{proposalId}/{id}")
	public String upvoteComment(Model model,
			@PathVariable("proposalId") String proposalId,
			@PathVariable("id") String id) {
		
		Comment c = cs.findByProposalAndId(proposalId, id);
		MyUserDetails user = (MyUserDetails) SecurityContextHolder.getContext().
				getAuthentication().getPrincipal();	
		
		if (c != null && user.getUser() != null) {
			vote(user.getUser(), c, VoteType.POSITIVE);
			cs.updateComment(proposalId, id);
		}
		
		return "redirect:/selectProposal/" + proposalId;
	}

	@RequestMapping("/downvoteComment/{proposalId}/{id}")
	public String downvoteComment(Model model,
			@PathVariable("proposalId") String proposalId,
			@PathVariable("id") String id) {
		
		Comment c = cs.findByProposalAndId(proposalId, id);
		MyUserDetails user = (MyUserDetails) SecurityContextHolder.getContext().
				getAuthentication().getPrincipal();	
		
		if (c != null && user.getUser() != null) {
			vote(user.getUser(), c, VoteType.NEGATIVE);
			cs.updateComment(proposalId, id);
		}
		
		return "redirect:/selectProposal/" + proposalId;
	}

	@RequestMapping("/createProposal")
	public String createProposal(Model model,
			@ModelAttribute Proposal createProposal,
			BindingResult result) {

		Proposal proposal = new Proposal();
		proposal.setTitle(createProposal.getTitle());
		proposal.setDescription(createProposal.getDescription());
		proposal.setTopic(createProposal.getTopic());

		if (!proposal.checkNotAllowedWords()) {
			ps.insertProposal(proposal);
			kafkaProducer.send("createdProposal", "created proposal");
		}

		return "redirect:/user";
	}

	@RequestMapping("/deleteProposal/{id}")
	public String deleteProposal(Model model, @PathVariable("id") String id) {
		Proposal p = ps.findProposalById(id);
		ps.delete(p);
		return "redirect:/user";
	}

	@RequestMapping("/createComment/{id}")
	public String commentProposal(Model model, @PathVariable("id") String id,
			@ModelAttribute Comment createComment) {
		
		Comment comment = new Comment();
		comment.setContent(createComment.getContent());
		Proposal p = ps.findProposalById(id);
		MyUserDetails user = (MyUserDetails) SecurityContextHolder.getContext().
				getAuthentication().getPrincipal();	
		
		Association.MakeComment.link(user.getUser(), comment, p);
		
		cs.insertComment(comment);
		kafkaProducer.send("createdComment", "created comment");
		return "redirect:/selectProposal/" + id;
	}

	@ModelAttribute("proposals")
	public List<Proposal> proposals() {
		return ps.findAll();
	}

	@ModelAttribute("topics")
	public List<String> topics() {
		List<String> l = new ArrayList<String>();
		for(Topic t:Topic.values())
			l.add(t.toString());
		return l;
	}
	
}