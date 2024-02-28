package com.quadecom.QuadrantEcom.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class SessionDto {
    private String checkoutMode;
    private String merchant;
    private String result;
    private Session session;
    @Data
    public static class Session {
        private String id;
        private String updateStatus;
        private String version;
    }
    private String successIndicator;
}
