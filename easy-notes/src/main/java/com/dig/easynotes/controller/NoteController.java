package com.dig.easynotes.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dig.easynotes.entity.Note;
import com.dig.easynotes.exception.ResourceNotFoundException;
import com.dig.easynotes.repository.NoteRepository;
import com.dig.easynotes.service.NoteService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/api/v1")
@Api(value = "Note Management system", description = "This app is for managing your notes")
public class NoteController {

	@Autowired
	private NoteRepository noteRepository;
	
	@Autowired
	private NoteService noteService;

	@GetMapping("/notes")
	@ApiOperation(value = "View a list of available notes", response = List.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved list"),
			@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
			@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found") })
	public List<Note> getAllNotes() {
		return noteService.getAllNotes();
	}

	@ApiOperation(value = "Get a note by Id")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved list"),
			@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
			@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found") })
	@GetMapping("/notes/{id}")
	public Note getNoteById(
			@ApiParam(value = "note id from which note object will retrieve", required = true) @PathVariable(value = "id") Long noteId) {
		return noteService.getNoteById(noteId);
	}

	@PostMapping("/notes")
	@ApiOperation(value = "Add a note")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully created note"),
			@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
			@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found") })
	public Note createNote(
			@ApiParam(value = "Note object store in database table", required = true) @Valid @RequestBody Note note) {
		return noteService.createNote(note);
	}

	@ApiOperation(value = "Update an note")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully updated the note"),
			@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
			@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found") })
	@PutMapping("/notes/{id}")
	public Note updateNote(
			@ApiParam(value = "Note Id to update note object", required = true) @PathVariable(value = "id") Long noteId,
			@ApiParam(value = "Update note object", required = true) @Valid @RequestBody Note noteDetails) {

		Note note = noteService.updateNote(noteId,noteDetails);
				
		return note;
	}

	@ApiOperation(value = "Delete a note")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully deleted the note"),
			@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
			@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found") })
	@DeleteMapping("/notes/{id}")
	public ResponseEntity<?> deleteNote(
			@ApiParam(value = "Note Id from which note object will delete from database table", required = true) @PathVariable(value = "id") Long noteId) {
		noteService.deleteNote(noteId);

		return ResponseEntity.ok().build();
	}
}
