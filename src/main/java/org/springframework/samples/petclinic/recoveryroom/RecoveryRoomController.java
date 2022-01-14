package org.springframework.samples.petclinic.recoveryroom;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("recoveryroom/")
public class RecoveryRoomController {

    @Autowired
    private RecoveryRoomService recoveryRoomService;

    private String VIEWS_RECOVERY_ROOM_CREATE_UPDATE_FORM= "recoveryroom/createOrUpdateRecoveryRoomForm";

    private static final String VIEWS_WELCOME = "welcome";


    @ModelAttribute("product_types")
	public List<RecoveryRoomType> populateProductTypes() {
		return this.recoveryRoomService.getAllRecoveryRoomTypes();
	}


    @GetMapping(path = "create")
    public String initCreationForm(ModelMap modelMap) {
        String view = VIEWS_RECOVERY_ROOM_CREATE_UPDATE_FORM;
        modelMap.addAttribute("recoveryRoom", new RecoveryRoom());
        return view;
    }

    @PostMapping(path = "create")
    public String processCreationForm(@Valid RecoveryRoom recoveryRoom, BindingResult result, ModelMap modelMap) {
        if (result.hasErrors()){
            modelMap.addAttribute("recoveryRoom", recoveryRoom);
            return VIEWS_RECOVERY_ROOM_CREATE_UPDATE_FORM;

        }else {
            recoveryRoomService.save(recoveryRoom);
            modelMap.addAttribute("message","Recovery room succesfully created!");
            return VIEWS_WELCOME;
        }
    }
    
}
