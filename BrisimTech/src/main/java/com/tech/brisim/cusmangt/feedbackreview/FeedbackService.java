package com.tech.brisim.cusmangt.feedbackreview;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FeedbackService {
    @Autowired
    private FeedbackRepository feedbackRepository;

    public List<FeedbackDTO> getAllFeedbackForRequest(Long serviceRequestId) {
        return feedbackRepository.findByServiceRequestId(serviceRequestId).stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    public FeedbackDTO submitFeedback(FeedbackDTO feedbackDto) {
        Feedback feedback = new Feedback();
        feedback.setCustomerId(feedbackDto.getCustomerId());
        feedback.setServiceRequestId(feedbackDto.getServiceRequestId());
        feedback.setComment(feedbackDto.getComment());
        feedback.setRating(feedbackDto.getRating());
        feedback = feedbackRepository.save(feedback);
        return convertToDto(feedback);
    }

    private FeedbackDTO convertToDto(Feedback feedback) {
        FeedbackDTO dto = new FeedbackDTO();
        dto.setId(feedback.getId());
        dto.setCustomerId(feedback.getCustomerId());
        dto.setServiceRequestId(feedback.getServiceRequestId());
        dto.setComment(feedback.getComment());
        dto.setRating(feedback.getRating());
        return dto;
    }
}
