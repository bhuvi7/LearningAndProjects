package com.jwttokens.jwttokens;

import org.joda.time.DateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
public class User {

	@Id
	private Long id;
	private String name;
	private String  gender;
	private String  email_id;
	private String  phone_number;
	private String  password;
	private String user_type;
	private Boolean is_active;
	private Integer login_count;
	private String  sso_type;
	private DateTime login_at;
	private DateTime created_at;
	private DateTime updated_at;
}
