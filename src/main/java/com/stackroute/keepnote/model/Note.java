package com.stackroute.keepnote.model;

import java.time.LocalDateTime;

/*
 * The class "Note" will be acting as the data model for the note Table in the database. Please
 * note that this class is annotated with @Entity annotation. Hibernate will scan all package for 
 * any Java objects annotated with the @Entity annotation. If it finds any, then it will begin the 
 * process of looking through that particular Java object to recreate it as a table in your database.
 */

public class Note {

	public Note() {

	}

	public Note(int i, String string, String string2, String string3, LocalDateTime localDate) {
	}

	public int getNoteId() {

		return 0;
	}

	public String getNoteTitle() {

		return null;
	}

	public String getNoteContent() {

		return null;
	}

	public String getNoteStatus() {

		return null;
	}

	public void setNoteId(int parseInt) {

	}

	public void setNoteTitle(String parameter) {

	}

	public void setNoteContent(String parameter) {

	}

	public void setNoteStatus(String parameter) {

	}

	public void setCreatedAt(LocalDateTime now) {

	}

}
