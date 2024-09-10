package com.davidefella.infoquiz.controller.api;

import com.davidefella.infoquiz.model.web.classroom.Classroom2StudentsDTOResponse;
import com.davidefella.infoquiz.model.web.classroom.Classroom2StudentsDTOResponseWrapper;
import com.davidefella.infoquiz.model.web.classroom.ClassroomDTOResponseWrapper;
import com.davidefella.infoquiz.model.web.classroom.mapper.ClassroomMapper;
import com.davidefella.infoquiz.model.web.classroom.ClassroomDTOResponse;
import com.davidefella.infoquiz.controller.api.util.endpoints.ApiEndpoints;
import com.davidefella.infoquiz.model.persistence.Classroom;
import com.davidefella.infoquiz.service.ClassroomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;


@RestController
public class ClassroomController {

    private ClassroomService classroomService;

    @Autowired
    public ClassroomController(ClassroomService classroomService) {
        this.classroomService = classroomService;
    }

    @PreAuthorize("hasRole('TEACHER')")
    @GetMapping(ApiEndpoints.TEACHER_CLASSROOMS_V1)
    public ClassroomDTOResponseWrapper getClassroomForTeacher(Authentication authentication) {
        JwtAuthenticationToken jwtAuthToken = (JwtAuthenticationToken) authentication;

        List<Classroom> classrooms = classroomService.findClassroomsByTeacherEmail(jwtAuthToken.getName());

        List<ClassroomDTOResponse> dtoClassrooms = new ArrayList<>();
        for (Classroom classroom : classrooms) {
            ClassroomDTOResponse dto = ClassroomMapper.toClassroomDto(classroom);
            dtoClassrooms.add(dto);
        }

        return new ClassroomDTOResponseWrapper(dtoClassrooms);
    }

    @PreAuthorize("hasRole('TEACHER')")
    @GetMapping(ApiEndpoints.TEACHER_CLASSROOMS_V1 + "/{uuid}")
    public Classroom2StudentsDTOResponseWrapper getClassroomForTeacher(@PathVariable UUID uuid) {
        Optional<Classroom> classroomOpt = classroomService.findByUUID(uuid);

        if(classroomOpt.isEmpty())
            return new Classroom2StudentsDTOResponseWrapper(null);

        Classroom2StudentsDTOResponse dtoResponse = ClassroomMapper.toClassroomStudentsDto(classroomOpt.get());

        return new Classroom2StudentsDTOResponseWrapper(dtoResponse);

    }
}