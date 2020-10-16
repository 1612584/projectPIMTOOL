package vn.elca.training.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter @Setter @AllArgsConstructor
public class ResponseDto {
    private String message;
    private boolean success;
    private List<String> visaList;
    private boolean conflictProjectNumber;
    private boolean isConcurrentUpdate;
    private boolean invalidDates;
    public ResponseDto(boolean isConcurrentUpdate, boolean success, String message) {
        this.isConcurrentUpdate = isConcurrentUpdate;
        this.success = success;
        this.message = message;
    }
    public ResponseDto() {
        this.setSuccess(false);
        this.setMessage("");
        this.setConflictProjectNumber(false);
        this.setVisaList(new ArrayList<>());
    }
}
