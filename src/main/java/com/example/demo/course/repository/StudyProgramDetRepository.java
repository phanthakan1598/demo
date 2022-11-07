package com.example.demo.course.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.course.model.StudyProgram;
import com.example.demo.course.model.StudyProgramDet;
import com.example.demo.faculty.model.Faculty;
import com.example.demo.faculty.model.Program;
import com.example.demo.university.model.DegreeLevel;

@Repository
public interface StudyProgramDetRepository extends JpaRepository<StudyProgramDet, Long>{
	Optional<StudyProgramDet> findOneByStudyProgramDetUuid(String studyProgramDetUuid);
	Optional<StudyProgramDet> findOneBySubjectCode(String subjectCode);
//	OrderByUpdatedAtDesc
	List<StudyProgramDet> findByStudyProgramOrderByLevelClassAscTermAsc (StudyProgram studyProgram);
	
	List<StudyProgramDet> findByStudyProgramCourseStructureProgramFacultyAndStudyProgramCourseStructureProgramDegreeLevelAndTypeSubjectTypeSubjectNameEn (Faculty faculty, DegreeLevel degreeLevel,String type);
	
	List<StudyProgramDet> findByStudyProgramCourseStructureProgramAndTypeSubjectTypeSubjectNameEn (Program program,String type);
	
	boolean existsBySubjectCodeAndStudyProgram(String subjectCode,StudyProgram studyProgram);
	boolean existsBySubjectCodeAndStudyProgramCourseStructureProgramFacultyAndStudyProgramCourseStructureProgramDegreeLevel(String subjectCode,Faculty faculty,DegreeLevel degreeLevel);
}
