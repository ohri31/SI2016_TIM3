package ba.eventTS.controllers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import java.util.Date;
import ba.eventTS.models.Event;
import ba.eventTS.repositories.eventRepository;

@Controller    // This means that this class is a Controller
@RequestMapping(path="/event") // This means URL's start with /demo (after Application path)
public class EventController {
	@Autowired // This means to get the bean called eventRepository           // Which is auto-generated by Spring, we will use it to handle the data
	private EventDao eventRepository;

	@GetMapping(path="/save") // Map ONLY GET Requests
	public @ResponseBody String addNewEvent (@RequestParam Date date) {
		// @ResponseBody means the returned String is the response, not a view name
		// @RequestParam means it is a parameter from the GET or POST request

		Event e = new Event();
		e.setVoteDeadline(date);
		eventRepository.save(e);
		return "Saved";
	}

	@GetMapping(path="/delete")
	public @ResponseBody String delete(@RequestParam int idEvent)
	{
	    try {
	      Event event = new Event(idEvent);
	      eventRepository.delete(event);
	    }
	    catch(Exception ex) {
	      return ex.getMessage();
	    }
	    return "Event deleted!";
	  }

	@GetMapping(path="/all")
	public @ResponseBody Iterable<Event> getAllEvents() {
		// vraca sve evente
		return eventRepository.findAll();
	}
	
	
	
	
	
	
}