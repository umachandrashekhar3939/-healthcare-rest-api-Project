package com.hms.healthcare.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Doctor {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(nullable = false)
	private String name;
	@Column(nullable = false, unique = true)
	private Long phoneNumber;
	@Column(nullable = false)
	private String specialization;
	@Column(nullable = false)
	private Integer experienceYears;
	@Column(nullable = false, length = 500)
	private String address;
	@Column(nullable = false, unique = true)
	private String licenceNumber;

	@OneToOne
	private User user;
}
