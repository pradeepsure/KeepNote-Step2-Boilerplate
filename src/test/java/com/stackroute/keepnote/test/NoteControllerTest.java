package com.stackroute.keepnote.test;

import static org.mockito.ArgumentMatchers.any;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.forwardedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import com.stackroute.keepnote.config.ApplicationContextConfig;
import com.stackroute.keepnote.controller.NoteController;
import com.stackroute.keepnote.dao.NoteDAO;
import com.stackroute.keepnote.model.Note;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = { ApplicationContextConfig.class })
@WebAppConfiguration
public class NoteControllerTest {

	LocalDateTime localDate = LocalDateTime.now();

	private MockMvc mockMvc;
	private Note note;
	private List<Note> allNotes;

	@Mock
	private NoteDAO noteDao;
	
	@InjectMocks
	private NoteController noteController;

	@Before
	public void setUp() throws Exception {
		noteController = new NoteController(noteDao);
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(this.noteController).build();
		
		note = new Note(1, "Sample note application", "Testing for NoteController.class", "active", localDate);
		Note note1 = new Note(1, "Sample note application -1", "Testing for NoteController.class", "active", localDate);
		Note note2 = new Note(2, "Sample note application-2", "Testing for NoteController.class", "active", localDate);
		Note note3 = new Note(3, "Sample note application-3", "Testing for NoteController.class", "active", localDate);
		allNotes = Arrays.asList(note1, note2, note3);

	}

	
	@Test
    public void testMockCreation(){
        assertNotNull(noteDao);
        assertNotNull(noteController);
        
    }
	
	@Test
	public void testIndexPage() throws Exception {
		mockMvc.perform(get("/")).andExpect(status().isOk()).andExpect(forwardedUrl("index"));
	}

	@Test
	public void testAddNotesSuccess() throws Exception {
		when(noteDao.saveNote(any())).thenReturn(true);
		mockMvc.perform(post("/add").param("noteTitle", note.getNoteTitle()).param("noteContent", note.getNoteContent())
				.param("noteStatus", note.getNoteStatus())).andExpect(status().isFound()).andExpect(redirectedUrl("/"));
	}

	@Test
	public void testAddnotesFailure() throws Exception {

		when(noteDao.saveNote(any())).thenReturn(false);
		mockMvc.perform(post("/add").param("noteTitle", note.getNoteTitle()).param("noteContent", "")
				.param("noteStatus", note.getNoteStatus())).andExpect(status().isOk()).andExpect(view().name("index"))
				.andExpect(forwardedUrl("index"));

	}

	@Test
	public void testUpdateNoteSuccess() throws Exception {

		note.setNoteContent("Update test cases for NoteController");
		when(noteDao.UpdateNote(any())).thenReturn(true);
		mockMvc.perform(post("/update").param("noteId", "1").param("noteTitle", note.getNoteTitle())
				.param("noteContent", note.getNoteContent()).param("noteStatus", note.getNoteStatus()))
				.andExpect(status().isFound()).andExpect(redirectedUrl("/"));

	}

	@Test
	public void testDeleteNoteSuccess() throws Exception {

		when(noteDao.deleteNote(note.getNoteId())).thenReturn(true);
		mockMvc.perform(get("/delete").param("noteId", "1")).andExpect(status().isFound())
				.andExpect(redirectedUrl("/"));

	}

	@Test
	public void testGetAllNotes() throws Exception {

		when(noteDao.getAllNotes()).thenReturn(allNotes);
		mockMvc.perform(get("/")).andExpect(status().isOk()).andExpect(view().name("index"))
				.andExpect(forwardedUrl("index"));

	}

}
