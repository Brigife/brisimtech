package com.tech.brisim.cusmangt.feedbackreview;



@Data
public class FeedbackDTO {
    private Long id;
    private String customerId;
    private Long serviceRequestId;
    private String comment;
    private Integer rating;
}