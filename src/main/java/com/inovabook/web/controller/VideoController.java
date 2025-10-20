package com.inovabook.web.controller;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/*@RestController
@RequestMapping("/api/video")
public class VideoController {

    @GetMapping("/stream/{lessonId}")
    public void streamVideo(@PathVariable Long lessonId,
                            HttpServletResponse response) {
        videoStreamingService.streamVideo(lessonId, response);
    }

    @GetMapping("/progress/{lessonId}")
    public ProgressResponse getProgress(@PathVariable Long lessonId) {
        return progressService.getLessonProgress(lessonId);
    }
}*/