package com.davidefella.infoquiz.model.factory;

import com.davidefella.infoquiz.model.persistence.users.Student;
import com.davidefella.infoquiz.model.persistence.users.Teacher;
import com.davidefella.infoquiz.model.persistence.users.UserInfoQuiz;
import com.davidefella.infoquiz.model.persistence.users.role.InfoQuizRole;

import java.util.List;

public class UserInfoQuizFactory {

    private UserInfoQuizFactory(){}

    public static UserInfoQuiz createUser(String code, String firstName, String lastName, String email, List<String> subjects, InfoQuizRole role) {
        switch (role) {
            case STUDENT:
                return new Student(code, lastName, firstName, email);
            case TEACHER:
                return new Teacher(code, lastName, firstName, email, subjects);
            default:
                throw new IllegalArgumentException("Invalid role: " + role);
        }
    }
}
