package com.example.springtutorial.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.springtutorial.form.ContactForm;

@Controller
public class ContactFormController {
	//フォームの表示
	@GetMapping("/form")
	public String showForm(Model model) {
		model.addAttribute("contactForm", new ContactForm());
		return "contactFormView";
	}

	//フォームの送信
	@PostMapping("/form")
	public String sbmitFrom(Model model,
			@Validated  ContactForm contactForm,
			BindingResult result) {
		//入力エラーがあればフォームに戻す
		if (result.hasErrors()) {
			model.addAttribute("contactForm", contactForm);
			return "contactFormView";
		}
		//正常なら確認画面へ
		model.addAttribute("contactForm", contactForm);
		return "confirmView";
	}
}