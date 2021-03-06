package ba.eventTS.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import ba.eventTS.models.Messages;
import ba.eventTS.repositories.MessageRepository;

@Controller    // This means that this class is a Controller
@RequestMapping(path="/message") // This means URL's start with /demo (after Application path)
public class MessageController {
	@Autowired // This means to get the bean called userRepository
	           // Which is auto-generated by Spring, we will use it to handle the data
	private MessagesDao messageRepository;

	@GetMapping(path="/save") // Map ONLY GET Requests
	public @ResponseBody String addMessage (@RequestParam String text, @RequestParam int idUser, @RequestParam int idEvent) {
		// @ResponseBody means the returned String is the response, not a view name
		// @RequestParam means it is a parameter from the GET or POST request

		Messages m = new Messages();
		m.setText(text);
		m.setIdUsera(idUser);
		m.setIdEventa(idEvent);
		messageRepository.save(m);
		return "Saved";
	}

	@GetMapping(path="/all")
	public @ResponseBody Iterable<Messages> getAllMesages() {
		return messageRepository.findAll();
	}
	
	@RequestMapping(value="/get-by-id")
	  @ResponseBody
	  public String getById(@RequestParam int idUser) {
	    String userId;
	    try {
	      User user = messageRepository.getById(idUser);
	      userId = String.valueOf(user.getId());
	    }
	    catch(Exception ex) {
	      return "User not found";
	    }
	    return "The user id is: " + userId;
	  }
	
}