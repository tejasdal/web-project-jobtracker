package com.web.project.jobtracker.jobapplication;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Tejas Patel
 * Enum to define status of job application.
 */
public enum JobApplicationStatus {

    WHISHLIST(1), APPLIED(2), INTERVIEW(3), OFFER(4), REJECT(5);

    private int value;
    private static Map map = new HashMap();

    private JobApplicationStatus(int value) {
        this.value = value;
    }

    static {
        for (JobApplicationStatus status : JobApplicationStatus.values()) {
            map.put(status.value, status);
        }
    }

    public static JobApplicationStatus valueOf(int value) {
        return (JobApplicationStatus) map.get(value);
    }

    public int getValue() {
        return value;
    }
}
