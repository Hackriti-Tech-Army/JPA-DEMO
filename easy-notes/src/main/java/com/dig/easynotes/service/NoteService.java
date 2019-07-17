package com.dig.easynotes.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.dig.easynotes.entity.Note;
import com.dig.easynotes.exception.ResourceNotFoundException;
import com.dig.easynotes.repository.NoteRepository;

@Service
public class NoteService {

	@Autowired
	private NoteRepository noteRepository;

	public List<Note> getAllNotes() {
		return noteRepository.findAll();
	}

	public Note getNoteById(Long noteId) {
		return noteRepository.findById(noteId)
				.orElseThrow(() -> new ResourceNotFoundException("Note not found for this id :: " + noteId));
	}

	public Note createNote(Note note) {
		return noteRepository.save(note);
	}

	public Note updateNote(Long noteId, Note noteDetails) {

		Note note = noteRepository.findById(noteId)
				.orElseThrow(() -> new ResourceNotFoundException("Note not found for this id :: " + noteId));

		note.setTitle(noteDetails.getTitle());
		note.setContent(noteDetails.getContent());

		Note updatedNote = noteRepository.save(note);
		return updatedNote;
	}

	public void deleteNote(Long noteId) {
		Note note = noteRepository.findById(noteId)
				.orElseThrow(() -> new ResourceNotFoundException("Note not found for this id :: " + noteId));

		noteRepository.delete(note);

	}
}
