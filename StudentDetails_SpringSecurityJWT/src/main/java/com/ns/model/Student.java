package com.ns.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.nimbusds.oauth2.sdk.Role;

import lombok.Data;

@Entity
@Table(name = "student")
@Data
public class Student {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "studid")
	private long studid;
	private String firstname;
	private String lastname;
	private int mobilenumber;
	private String emailaddress;
	private int proj;
	private String photoupload;
	 @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	    @JoinTable(
	            name = "student_project",
	            joinColumns = @JoinColumn(name = "studid"),
	            inverseJoinColumns = @JoinColumn(name = "projid")
	            )
	    private Set<Project> project = new HashSet<>();
}// class
