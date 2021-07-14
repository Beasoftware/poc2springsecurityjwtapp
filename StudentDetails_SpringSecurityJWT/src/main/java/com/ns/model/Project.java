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

import lombok.Data;
@Entity 
@Table(name="project")
public class Project {
	@Id
	@Column(name = "projid")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int projid;
	private String projname;
	private int duration;
	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(
            name = "student_project",
            joinColumns = @JoinColumn(name = "studid"),
            inverseJoinColumns = @JoinColumn(name = "projid")
            )
	 private Set<Project> project = new HashSet<>();
}//class
