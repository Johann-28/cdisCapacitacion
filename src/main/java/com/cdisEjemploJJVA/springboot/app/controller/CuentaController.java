package com.cdisEjemploJJVA.springboot.app.controller;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.cdisEjemploJJVA.springboot.app.models.dao.ICuentaDao;
import com.cdisEjemploJJVA.springboot.app.models.dao.ITarjetaDao;
import com.cdisEjemploJJVA.springboot.app.models.entity.Cuenta;
import com.cdisEjemploJJVA.springboot.app.validator.CuentaValidator;

@Controller
@SessionAttributes("cuenta")
public class CuentaController {
	
	@Autowired
	private ICuentaDao cuentaDao;
	
	@Autowired
	private CuentaValidator cuentaValidator;
//	
	@Autowired
	private ITarjetaDao tarjetaDao;
	
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.addValidators(cuentaValidator);
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		dateFormat.setLenient(false);
		binder.registerCustomEditor(Date.class, "diaCreacion", new CustomDateEditor(dateFormat, false));
	}
	
	@GetMapping(value = "/lista")
	public String cuentaLista(Model model, Map<String, Object> modelCuenta){
		Cuenta cuenta = new Cuenta();
		modelCuenta.put("cuenta", cuenta);
		model.addAttribute("titulo", "Lista de cuentas");
		model.addAttribute("cuentas", cuentaDao.findAll());
		return "lista";
	}
	@RequestMapping(value = "/form-cuenta")
	public String crear(Map<String,Object> model) {
		Cuenta cuenta = new Cuenta();
		model.put("cuenta", cuenta);
		model.put("titulo", "Nueva cuenta, llene los datos");
		return "form-cuenta";
	}
	
	@RequestMapping(value = "/form-cuenta/{id}")
	public String editar(@PathVariable( value = "id") Long id,Map<String,Object> model){
		
		Cuenta cuenta = null;
		
		if(id > 0) {
			cuenta = cuentaDao.findOne(id);
		}else {
			return "redirect:/lista";
		}
		
		model.put("titulo", "Edite la cuenta");
		model.put("cuenta", cuenta);
		
		return "form-cuenta";
	}
	
	@PostMapping(value="form-cuenta")
	public String guardar(@Valid Cuenta cuenta, BindingResult result, Model model, SessionStatus status
			,RedirectAttributes flash) {
		
		if(result.hasErrors()) {
			model.addAttribute("titulo" , "Fomulario de cuenta");
			model.addAttribute("result", result.hasErrors());
			model.addAttribute("mensaje", "Error al registrar la cuenta");
			return "form-cuenta";
		}else {
			model.addAttribute("result" , false);
			model.addAttribute("errList" , "");
		}
		flash.addAttribute("completeMsj" , "Se guardo correctamente");
		cuentaDao.save(cuenta);
		status.setComplete();
		return "redirect:form-cuenta";
	}
	
	@RequestMapping(value = "/eliminarcuenta/{id}")
	public String eliminar(@PathVariable(value = "id") Long id, RedirectAttributes flash) {
		if(id>0 && id != null) {
			if(tarjetaDao.findByCuentaId(id.toString()).isEmpty()) {
				System.out.println("La lista esta vacia");
				cuentaDao.delete(id);
			}else {
				flash.addFlashAttribute("mensaje" , "La cuenta tiene tarjetas pendientes por eliminar");	
			}

		}
		return "redirect:/lista";
	}
	
	@PostMapping(value = "/buscar-numero-tel")
	public String cargarCuentasNumeroTelefono(Cuenta cuenta, RedirectAttributes flash) {
		
		if(!cuentaDao.findByNumeroTelefono(cuenta.getNumeroTelefono()).isEmpty()) {
			flash.addFlashAttribute("listCuentasNumeroT", cuentaDao.findByNumeroTelefono(cuenta.getNumeroTelefono()));
			flash.addFlashAttribute("mensajeSucces", "Se encontraron cuentas");
		}else {
			flash.addFlashAttribute("mensaje", "No se encontraron cuentas");
		}
		
		return "redirect:/lista";
	}
	
}
