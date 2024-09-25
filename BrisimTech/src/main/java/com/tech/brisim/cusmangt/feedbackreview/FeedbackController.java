package com.tech.brisim.cusmangt.feedbackreview;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/feedback")
public class FeedbackController {
    @Autowired
    private FeedbackService feedbackService;

    @GetMapping("/{serviceRequestId}")
    public List<FeedbackDTO> getFeedback(@PathVariable Long serviceRequestId) {
        return feedbackService.getAllFeedbackForRequest(serviceRequestId);
    }

    @PostMapping("/feed")
    public FeedbackDTO submitFeedback(@RequestBody FeedbackDTO feedbackDto) {
        return feedbackService.submitFeedback(feedbackDto);
    }
}
