package com.example.springtutorial.form;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class ContactForm {
	//名前
	@NotBlank (message = "お名前を入力してくだい。")
	private String name;
	//メールアドレス
	@NotBlank (message = "メールアドレスの入力形式が正しくありません。")
	@Email (message = "メールアドレスの入力が正しくありません。")
	private String email;
	//お問い合わせ内容
	@NotBlank (message = "お問い合わせ内容を入力してください。")
	private String message;

}
