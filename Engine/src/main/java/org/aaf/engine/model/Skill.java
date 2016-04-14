package org.aaf.engine.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
 
@Entity
public class Skill {
 
	@Id
	@GeneratedValue(generator = "GENERATE_Skill", strategy = GenerationType.SEQUENCE)
	@SequenceGenerator(name = "GENERATE_Skill", sequenceName = "Skill_pk_seq", allocationSize = 1)
	private Long id;
 
    private String key;
 
    private String value;
 
    public String getKey() {
        return key;
    }
 
    public void setKey(String key) {
        this.key = key;
    }
 
    public String getValue() {
        return value;
    }
 
    public void setValue(String value) {
        this.value = value;
    }
}