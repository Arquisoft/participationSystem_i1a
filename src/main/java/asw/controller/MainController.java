package asw.controller;

import java.util.ArrayList;
import java.util.List;

import asw.persistence.FillDatabase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import asw.model.impl.Association;
import asw.model.impl.Comment;
import asw.model.impl.Proposal;
import asw.model.impl.User;
import asw.model.impl.Vote;
import asw.model.types.Topic;
import asw.model.types.VoteType;
import asw.persistence.services.CommentService;
import asw.persistence.services.ProposalService;
import asw.persistence.services.UserService;
import asw.persistence.services.VoteService;
import asw.producers.KafkaProducer;

@Controller
public class MainController {

	private User loggedinUser;

    @Autowired
    private KafkaProducer kafkaProducer;

    @Autowired
    private UserService us;
    
    @Autowired
    private ProposalService ps;
    
    @Autowired
    private CommentService cs;
    
    @Autowired
    private VoteService vs;

    @RequestMapping("/")
	public ModelAndView landing(Model model) {
		return new ModelAndView("redirect:" + "/login");
	}

	@RequestMapping("/login")
	public String login(Model model) {
		model.addAttribute("loginUser", new User());
		return "login";
	}
	
	@RequestMapping("/loginCheck")
	public String loginCheck(Model model, @ModelAttribute User loginUser){
		
		User user = us.findUserByLoginAndPassword(loginUser.getLogin(), loginUser.getPassword());
		this.loggedinUser = user;

		if(loggedinUser != null){
			if (loggedinUser.isAdmin()) {
				return "admin";
			}
			model.addAttribute("createProposal", new Proposal());
			return "user";
		}
		return "login";
	}

    @RequestMapping("/user")
    public String user(Model model) {
        model.addAttribute("createProposal", new Proposal());
        return "user";
    }

    @RequestMapping("/admin")
    public String admin(Model model) {
        return "admin";
    }

	@RequestMapping("/createProposal")
	public String createProposal(Model model, @ModelAttribute Proposal createProposal) {

        Proposal proposal = new Proposal(
            loggedinUser,
            createProposal.getTitle(),
            createProposal.getDescription(),
            createProposal.getTopicAux()
        );

		if (proposal.checkNotAllowedWords()) {
			ps.save(proposal);
			kafkaProducer.send("createdProposal", "created proposal");
		}

		return "redirect:/user";
	}

	@RequestMapping("/deleteProposal/{id}")
	public String deleteProposal(Model model, @PathVariable("id") Long id) {
		Proposal p = ps.findById(id);
		ps.delete(p);
		return "redirect:/admin";
	}
	
	@RequestMapping("/selectProposal/{id}")
	public String selectProposal(Model model, @PathVariable Long id) {
		model.addAttribute("p", ps.findById(id));
		model.addAttribute("createComment", new Comment());
		return "proposal";
	}

	@RequestMapping("/upvoteProposal/{id}")
	public String upvoteProposal(Model model, @PathVariable("id") Long id) {

        Proposal prop = ps.findById(id);

        if (prop != null && loggedinUser != null) {
            Vote v = vs.findVoteByUserByVotable(loggedinUser, prop);

            if(v != null) {
                Association.MakeVote.unlink(loggedinUser, v, prop);
                vs.deleteVote(v);

                if (v.getVoteType() != VoteType.POSITIVE) {
                    v = new Vote(loggedinUser, prop, VoteType.POSITIVE);
                    vs.save(v);
                }
            }
            else{
                v = new Vote(loggedinUser, prop, VoteType.POSITIVE);
                vs.save(v);
            }

			ps.updateProposal(prop);
		}

		return "redirect:/selectProposal/" + id;
	}

	@RequestMapping("/downvoteProposal/{id}")
	public String downvoteProposal(Model model, @PathVariable("id") Long id) {

		Proposal prop = ps.findById(id);
		
		if (prop != null && loggedinUser != null) {
            Vote v = vs.findVoteByUserByVotable(loggedinUser, prop);

            if(v != null) {
                Association.MakeVote.unlink(loggedinUser, v, prop);
                vs.deleteVote(v);

                if (v.getVoteType() != VoteType.NEGATIVE) {
                    v = new Vote(loggedinUser, prop, VoteType.NEGATIVE);
                    vs.save(v);
                }
            }
            else{
                v = new Vote(loggedinUser, prop, VoteType.NEGATIVE);
                vs.save(v);
            }

            ps.updateProposal(prop);
		}

		return "redirect:/selectProposal/" + id;
	}

	@RequestMapping("/createComment/{id}")
	public String commentProposal(Model model, @PathVariable("id") Long id, @ModelAttribute Comment createComment) {

        Proposal p = ps.findById(id);

        Comment comment = new Comment(
            loggedinUser,
            createComment.getContent(),
            p
        );
		
		cs.save(comment);
		kafkaProducer.send("createdComment", "created comment");
		return "redirect:/selectProposal/" + id;
	}

	@RequestMapping("/upvoteComment/{proposalId}/{id}")
	public String upvoteComment(Model model, @PathVariable("proposalId") Long proposalId, @PathVariable("id") Long id){

		Comment c = cs.findByProposalAndId(proposalId, id);
		
		if (c != null && loggedinUser != null) {
            Vote v = vs.findVoteByUserByVotable(loggedinUser, c);

            if(v != null) {
                Association.MakeVote.unlink(loggedinUser, v, c);
                vs.deleteVote(v);

                if( v.getVoteType() != VoteType.POSITIVE){
                    v = new Vote(loggedinUser, c, VoteType.POSITIVE);
                    vs.save(v);
                }
            }
            else{
                v = new Vote(loggedinUser, c, VoteType.POSITIVE);
                vs.save(v);
            }

            cs.updateComment(proposalId, c);
		}

		return "redirect:/selectProposal/" + proposalId;
	}

	@RequestMapping("/downvoteComment/{proposalId}/{id}")
	public String downvoteComment(Model model, @PathVariable("proposalId") Long proposalId, @PathVariable("id") Long id){

		Comment c = cs.findByProposalAndId(proposalId, id);
		
		if (c != null && loggedinUser != null) {
            Vote v = vs.findVoteByUserByVotable(loggedinUser, c);

            if(v != null) {
                Association.MakeVote.unlink(loggedinUser, v, c);
                vs.deleteVote(v);

                if(v.getVoteType() != VoteType.NEGATIVE) {
                    v = new Vote(loggedinUser, c, VoteType.NEGATIVE);
                    vs.save(v);
                }
            }
            else{
                v = new Vote(loggedinUser, c, VoteType.NEGATIVE);
                vs.save(v);
            }

            cs.updateComment(proposalId, c);
		}
		
		return "redirect:/selectProposal/" + proposalId;
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