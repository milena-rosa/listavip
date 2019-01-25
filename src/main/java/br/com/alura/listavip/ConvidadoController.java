package br.com.alura.listavip;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import br.com.alura.listavip.models.Convidado;
import br.com.alura.listavip.service.ConvidadoService;

@Controller
public class ConvidadoController {

	@Autowired
	private ConvidadoService service;
	
	@RequestMapping("/")
	public String index() {
		return "index";
	}

	@RequestMapping("listaconvidados")
	public ModelAndView listaConvidados() {
		Iterable<Convidado> convidados = service.obterTodos();

		ModelAndView modelAndView = new ModelAndView("listaconvidados");
		modelAndView.addObject("convidados", convidados);
		return modelAndView;
	}

	@RequestMapping(value = "salvar", method = RequestMethod.POST)
	public ModelAndView salvar(Convidado convidado) {
		service.salvar(convidado);
		//new EmailService().enviar(convidado.getNome(), convidado.getEmail());
		return new ModelAndView("redirect:listaconvidados");
	}
}
