package com.dig.easynotes.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dig.easynotes.entity.Note;

public interface NoteRepository extends JpaRepository<Note, Long>{

}
