package com.example.springtutorial.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.springtutorial.form.ContactForm;

@Controller
public class ContactFormController {
    // フォームの表示
    @GetMapping("/form")
    public String showForm(Model model) {
        model.addAttribute("contactForm", new ContactForm());
        return "contactFormView"; // 入力画面
    }

    // フォーム送信処理
    @PostMapping("/form")
    public String submitForm(
        @Validated ContactForm contactForm,
        BindingResult result,
        Model model,
        RedirectAttributes redirectAttributes
    ) {
        if (result.hasErrors()) {
            model.addAttribute("contactForm", contactForm);
            return "contactFormView"; // 入力画面に戻る
        }
        // 正常時：一時的にcontactFormを保存してリダイレクト
        redirectAttributes.addFlashAttribute("contactForm", contactForm);
        return "redirect:/confirm";
    }

    // 確認画面の表示
    @GetMapping("/confirm")
    public String showConfirm(
        @ModelAttribute("contactForm") ContactForm contactForm,
        Model model
    ) {
        // ここで contactForm はリダイレクトされたときだけ値がセットされる
        if (contactForm == null || contactForm.getName() == null) {
            // 直接/confirmにアクセスされたときはフォームに戻す
            return "redirect:/form";
        }
        return "confirmView"; // 確認画面
    }
}