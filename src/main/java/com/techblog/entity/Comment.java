package com.techblog.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Comment {
@Id
@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer commnetId;
	private String commentContent;
	private String user;
	@ManyToOne
	private Post post;
}
