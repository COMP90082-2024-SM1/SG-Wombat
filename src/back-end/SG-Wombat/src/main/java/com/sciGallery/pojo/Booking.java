package com.sciGallery.pojo;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;



@Data
@AllArgsConstructor
@NoArgsConstructor
public class Booking {
    private int bookingId;
    private String programStream;
    private String requestConfirmed;
    private String status;
    private String facilitators;
    private String school;
    private LocalDate prefDate1;
    private LocalDate prefDate2;
    private LocalDate programDate;
    private LocalTime startTime;
    private LocalTime endTime;
    private LocalTime runTime;
    private String reporting3hrs;
    private String programCat;
    private String exhibition;
    private String busRequired;
    private String busBooked;
    private String todoListType;
    private String notes;
    private String partnerSchool;
    private String regStudentsNo;
    private String attendedStudentsNo;
    private String lowSes;
    private String accNeeds;
    private String allergyNeeds;
    private String teacherNotes;
    private String commentsSg;
    private String firstName;
    private String lastName;
    private String emailAddress;
    private String phoneNumber;
    private String teachingArea;
    private String processStatus;
    private String busStatus;
    private String quote;
    private String priceWoGst;
    private String priceFull;
    private String creditSurcharge;
    private LocalDate datePaid;
    private String cardOwner;
    private String busInvoiceNo;
    private String savedReceipt;
    private String expenseMaster;
    private String pinEmail;
    private String timesInMsc;
    private String busNotes;
    private String amount;
    private String abn;
    private String invoiceNo;
    private LocalDateTime createTime;
    private List<String> studentYear;
    private List<String> modules;
    private List<String> deliveryLocation;
    private Map<String, Object> checklistStatus;


    // JSON 序列化和反序列化
    public String getStudentYearJson() throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(studentYear);
    }

    public void setStudentYearJson(String studentYearJson) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        this.studentYear = mapper.readValue(studentYearJson, List.class);
    }

    public String getModulesJson() throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(modules);
    }

    public void setModulesJson(String modulesJson) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        this.modules = mapper.readValue(modulesJson, List.class);
    }

    public String getDeliveryLocationJson() throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(deliveryLocation);
    }

    public void setDeliveryLocationJson(String deliveryLocationJson) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        this.deliveryLocation = mapper.readValue(deliveryLocationJson, List.class);
    }

    public String getChecklistStatusJson() throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        String s = mapper.writeValueAsString(checklistStatus);
        return  s;
    }

    public void setChecklistStatusJson(String checklistStatusJson) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        this.checklistStatus = mapper.readValue(checklistStatusJson, Map.class);
    }
}
