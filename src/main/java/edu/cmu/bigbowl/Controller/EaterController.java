package edu.cmu.bigbowl.Controller;

import edu.cmu.bigbowl.Entity.Cook;
import edu.cmu.bigbowl.Entity.Eater;
import edu.cmu.bigbowl.Service.EaterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Collection;

@RestController
@RequestMapping("/eater")
public class EaterController {

    @Autowired
    private EaterService eaterService;

    // GET
    @RequestMapping(method = RequestMethod.GET)
    public Collection<Eater> getAllEaters() {
        return eaterService.getAllEaters();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Eater getEaterById(@PathVariable("id") String id) {
        return eaterService.getEaterById(id).orElse(null);
    }

    // DELETE
    @RequestMapping(method = RequestMethod.DELETE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public Eater deleteEater(@RequestBody Eater eater) {
        return eaterService.deleteEater(eater).orElse(null);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public Eater deleteEaterById(@PathVariable("id") String id) {
        return eaterService.deleteEaterById(id).orElse(null);
    }

    @RequestMapping(value = "/reset", method = RequestMethod.DELETE)
    public void deleteAccounts() {
        eaterService.deleteEaters();
    }

    // PATCH
    @RequestMapping(method = RequestMethod.PATCH, consumes = MediaType.APPLICATION_JSON_VALUE)
    public Eater updateEater(@RequestBody Eater eater) {
        return eaterService.updateEaters(eater).orElse(null);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PATCH)
    public Eater updateEaterById(@PathVariable("id") String id, @RequestBody Eater eater) {
        return eaterService.updateEaterById(id, eater).orElse(null);
    }

    // POST
    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public Eater insertEater(@RequestBody Eater eater) {
        return eaterService.postEater(eater);
    }

    @RequestMapping(value = "/fake", method = RequestMethod.POST)
    public Collection<Eater> insertCook() throws IOException {
        eaterService.postFakeEaters();
        return eaterService.getAllEaters();
    }
}
