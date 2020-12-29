package br.com.ltst.springwebmvcbasicodigitalinnovationone.controller;

import br.com.ltst.springwebmvcbasicodigitalinnovationone.model.Jedi;
import br.com.ltst.springwebmvcbasicodigitalinnovationone.repository.JediRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
public class JediTimeleafController {

    @Autowired
    private JediRepository jediRepository;


    @GetMapping("/jedi")
    public ModelAndView jedi() {
        final var modelAndView = new ModelAndView();
        modelAndView.setViewName("jedi");

        modelAndView.addObject("allJedi", jediRepository.findAll());

        return modelAndView;
    }

    @PostMapping("/jedi")
    public String create(@Valid @ModelAttribute Jedi jedi, BindingResult bindingResult, RedirectAttributes redirectAttributes) {

        // interface do spring que retorna o resultado dos erros dessa validação.
        if (bindingResult.hasErrors()) {
            return "new-jedi";
        }

        jediRepository.save(jedi);
        redirectAttributes.addFlashAttribute("message","Jedi criado com sucesso");
        return "redirect:jedi";
    }

    @GetMapping("/new-jedi")
    public ModelAndView newJedi() {
        final var modelAndView = new ModelAndView();
        modelAndView.setViewName("new-jedi");

        modelAndView.addObject("jedi", new Jedi());
        return modelAndView;
    }


}
