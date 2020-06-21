package org.emp.gateway.model;

public class Mail {

	public String from;
	public String to;
	public String subject;
	public String text;
	public String time;
	
	public Mail(String from, String to, String subject, String text, String time) {
		super();
		this.from = from;
		this.to = to;
		this.subject = subject;
		this.text = text;
		this.time=time;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getFrom() {
		return from;
	}
	public void setFrom(String from) {
		this.from = from;
	}
	public String getTo() {
		return to;
	}
	public void setTo(String to) {
		this.to = to;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	
	
}
