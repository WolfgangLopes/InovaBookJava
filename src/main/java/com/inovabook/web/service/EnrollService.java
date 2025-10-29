package com.inovabook.web.service;

import com.inovabook.web.model.User;

public interface EnrollService {
    void subscribeUserToCourse(User user, Long courseId);
}
