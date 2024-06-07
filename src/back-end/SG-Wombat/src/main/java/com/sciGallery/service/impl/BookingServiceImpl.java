package com.sciGallery.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.sciGallery.mapper.BookingMapper;
import com.sciGallery.pojo.Booking;
import com.sciGallery.pojo.PageBean;
import com.sciGallery.service.BookingService;
import org.apache.ibatis.jdbc.Null;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;

import org.springframework.transaction.annotation.Transactional;


@Service
public class BookingServiceImpl implements BookingService {

    @Autowired
    private BookingMapper bookingMapper;

    @Override
    @Transactional
    public void add(Booking booking) {
        try {
            // 根据 todoListType 生成 checklistStatus 字典
            TreeMap<String, Object> checklistStatus = new TreeMap<String, Object>(
                    new Comparator<>() {
                        public int compare(String obj1, String obj2) {
                            // 降序排序
                            Integer o1 = Integer.parseInt(obj1.split("")[0]);
                            Integer o2 = Integer.parseInt(obj2.split("")[0]);

                            return o1.compareTo(o2);
                        }
                    });

            String todoListType = booking.getTodoListType();
            if (todoListType != null) {
                switch (todoListType) {
                    case "Paid workshop":
                        checklistStatus.put("1. Make initial Priava Hold and MSC Hold", false);
                        checklistStatus.put("2. Update holds in MSC & Priava as relevant.", false);
                        checklistStatus.put("3. Add to LER", false);
                        checklistStatus.put("4. Respond with confirmation of program, date", false);
                        checklistStatus.put("5. If Future Foods, ask for med info for staff & students", false);
                        checklistStatus.put("6. KE to roster", false);
                        checklistStatus.put("7. Check in with teacher 2 weeks out", false);
                        break;
                    case "Guided tour":
                        checklistStatus.put("1. Priava", false);
                        checklistStatus.put("2. LER", false);
                        checklistStatus.put("3. Make a new MSC", false);
                        checklistStatus.put("4. KE to Roster LEFS", false);
                        checklistStatus.put("5. Check in with teacher 2 weeks out", false);
                        checklistStatus.put("6. Process invoice ~2 weeks before they arrive", false);
                        checklistStatus.put("7. Confirm student numbers on the day", false);
                        break;
                    case "Free unguided tour":
                        checklistStatus.put("1. Add to Priava: Mini-bleachers/PACCAR", false);
                        checklistStatus.put("2. LER", false);
                        checklistStatus.put("3. MSC", false);
                        checklistStatus.put("4. Check in with teacher 2 weeks out", false);
                        checklistStatus.put("5. Confirm student numbers on the day", false);
                        break;
                    case "Buxton Contemporary tour":
                        checklistStatus.put("1. Add to Priava.", false);
                        checklistStatus.put("2. Add to the LER.", false);
                        checklistStatus.put("3. Add to MSC, Plus invite Buxton & EB", false);
                        checklistStatus.put("4. Attach : Child Safety Doc, Educational Resource", false);
                        checklistStatus.put("5. Email teacher", false);
                        checklistStatus.put("6. Teacher has confirmed?", false);
                        checklistStatus.put("7. EB: Roster LEFS", false);
                        break;
                    case "Indigenous STEM program":
                        checklistStatus.put("1. Place initial hold in Priava", false);
                        checklistStatus.put("2. If not already done, update Priava with detail", false);
                        checklistStatus.put("3. LER", false);
                        checklistStatus.put("4. Add to LTSB calendar", false);
                        checklistStatus.put("5. Sends initial confirmation email to teacher", false);
                        checklistStatus.put("6. MC makes & sends pack", false);
                        checklistStatus.put("7. Send confirmation with pack", false);
                        checklistStatus.put("8. Book buses", false);
                        checklistStatus.put("9. Rosters LEFs", false);
                        break;
                    case "Partner school booking":
                        checklistStatus.put("1. Priava", false);
                        checklistStatus.put("2. MSC", false);
                        checklistStatus.put("3. LER - update status to upcoming", false);
                        checklistStatus.put("4. Book interpreter if needed (VCD)", false);
                        checklistStatus.put("5. Book bus if needed", false);
                        checklistStatus.put("6. Paid for interpreter/bus", false);
                        checklistStatus.put("7. Risk assessment if requested by SCoE", false);
                        checklistStatus.put("8. Confirm with teacher if requested by SCoE", false);
                        checklistStatus.put("9. Request - Medical and allergies information", false);
                        break;
                    default:
                        checklistStatus = new TreeMap<>();
                        break;
                }
            } else {
                checklistStatus = new TreeMap<>();
            }

            // 序列化 checkListStatus 字段
            ObjectMapper mapper = new ObjectMapper();
            booking.setChecklistStatusJson(mapper.writeValueAsString(checklistStatus));

            // 序列化其他 JSON 字段
            booking.setStudentYearJson(booking.getStudentYearJson());
            booking.setModulesJson(booking.getModulesJson());
            booking.setDeliveryLocationJson(booking.getDeliveryLocationJson());

        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        // 设置创建时间
        booking.setCreateTime(LocalDateTime.now());

        // 插入数据库
        bookingMapper.insert(booking);
    }

    @Override
    @Transactional
    public void updateTodolist(Object o) {
        Booking booking = new Booking();
        ObjectMapper mapper = new ObjectMapper();
        try {
            TreeMap<String, Object> map = JSONObject.parseObject(JSON.toJSONString(o), TreeMap.class);

            booking.setChecklistStatusJson(JSON.toJSONString(map));
        } catch (Exception e) {
            e.printStackTrace();
        }

        bookingMapper.updateTodolist(booking);
    }


    @Override
    @Transactional
    public void delete(Integer id) {
        bookingMapper.deleteById(id);
    }

    @Override
    public PageBean page(Integer page, String requestConfirmed) {
        PageHelper.startPage(page, 100);

        List<Booking> schoolList = bookingMapper.list(requestConfirmed);
        Page<Booking> p = (Page<Booking>) schoolList;

        // 反序列化 JSON 字段
        for (Booking booking : p) {
            try {
                booking.setStudentYearJson(booking.getStudentYearJson());
                booking.setModulesJson(booking.getModulesJson());
                booking.setDeliveryLocationJson(booking.getDeliveryLocationJson());
                booking.setChecklistStatusJson(booking.getChecklistStatusJson());
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        }

        return new PageBean(p.getTotal(), p.getResult());
    }

    @Override
    public Booking getById(Integer id) {
        Booking booking = bookingMapper.selectById(id);
        if (booking != null) {
            try {
                ObjectMapper mapper = new ObjectMapper();
                booking.setStudentYear(mapper.readValue(booking.getStudentYearJson(), List.class));
                booking.setModules(mapper.readValue(booking.getModulesJson(), List.class));
                booking.setDeliveryLocation(mapper.readValue(booking.getDeliveryLocationJson(), List.class));
                booking.setChecklistStatus(mapper.readValue(booking.getChecklistStatusJson(), Map.class));
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        }
        return booking;
    }

    @Override
    public void update(Booking booking) {


        // 先从数据库中获取现有的 Booking 对象
        Booking existingBooking = bookingMapper.selectById(booking.getBookingId());

        if (existingBooking == null) {
            // 如果现有对象不存在，可以抛出异常或进行其他处理
            throw new IllegalArgumentException("Booking with id " + booking.getBookingId() + " does not exist.");
        }


        if (booking.getTodoListType() != null && !Objects.equals(existingBooking.getTodoListType(), booking.getTodoListType())) {
            try {
                // 根据 todoListType 生成 checklistStatus 字典
                Map<String, Object> checklistStatus = new HashMap<>();
                switch (booking.getTodoListType()) {
                    case "Paid workshop":
                        checklistStatus.put("1. Make initial Priava Hold and MSC Hold", false);
                        checklistStatus.put("2. Update holds in MSC & Priava as relevant.", false);
                        checklistStatus.put("3. Add to LER", false);
                        checklistStatus.put("4. Respond with confirmation of program, date", false);
                        checklistStatus.put("5. If Future Foods, ask for med info for staff & students", false);
                        checklistStatus.put("6. KE to roster", false);
                        checklistStatus.put("7. Check in with teacher 2 weeks out", false);
                        break;
                    case "Guided tour":
                        checklistStatus.put("1. Priava", false);
                        checklistStatus.put("2. LER", false);
                        checklistStatus.put("3. Make a new MSC", false);
                        checklistStatus.put("4. KE to Roster LEFS", false);
                        checklistStatus.put("5. Check in with teacher 2 weeks out", false);
                        checklistStatus.put("6. Process invoice ~2 weeks before they arrive", false);
                        checklistStatus.put("7. Confirm student numbers on the day", false);
                        break;
                    case "Free unguided tour":
                        checklistStatus.put("1. Add to Priava: Mini-bleachers/PACCAR", false);
                        checklistStatus.put("2. LER", false);
                        checklistStatus.put("3. MSC", false);
                        checklistStatus.put("4. Check in with teacher 2 weeks out", false);
                        checklistStatus.put("5. Confirm student numbers on the day", false);
                        break;
                    case "Buxton Contemporary tour":
                        checklistStatus.put("1. Add to Priava.", false);
                        checklistStatus.put("2. Add to the LER.", false);
                        checklistStatus.put("3. Add to MSC, Plus invite Buxton & EB", false);
                        checklistStatus.put("4. Attach : Child Safety Doc, Educational Resource", false);
                        checklistStatus.put("5. Email teacher", false);
                        checklistStatus.put("6. Teacher has confirmed?", false);
                        checklistStatus.put("7. EB: Roster LEFS", false);
                        break;
                    case "Indigenous STEM program":
                        checklistStatus.put("1. Place initial hold in Priava", false);
                        checklistStatus.put("2. If not already done, update Priava with detail", false);
                        checklistStatus.put("3. LER", false);
                        checklistStatus.put("4. Add to LTSB calendar", false);
                        checklistStatus.put("5. Sends initial confirmation email to teacher", false);
                        checklistStatus.put("6. MC makes & sends pack", false);
                        checklistStatus.put("7. Send confirmation with pack", false);
                        checklistStatus.put("8. Book buses", false);
                        checklistStatus.put("9. Rosters LEFs", false);
                        break;
                    case "Partner school booking":
                        checklistStatus.put("1. Priava", false);
                        checklistStatus.put("2. MSC", false);
                        checklistStatus.put("3. LER - update status to upcoming", false);
                        checklistStatus.put("4. Book interpreter if needed (VCD)", false);
                        checklistStatus.put("5. Book bus if needed", false);
                        checklistStatus.put("6. Paid for interpreter/bus", false);
                        checklistStatus.put("7. Risk assessment if requested by SCoE", false);
                        checklistStatus.put("8. Confirm with teacher if requested by SCoE", false);
                        checklistStatus.put("9. Request - Medical and allergies information", false);
                        break;
                    default:
                        checklistStatus = new HashMap<>();
                        break;
                }

                // 序列化 checkListStatus 字段
                ObjectMapper mapper = new ObjectMapper();
                booking.setChecklistStatusJson(mapper.writeValueAsString(checklistStatus));

            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        }


        try {
            ObjectMapper mapper = new ObjectMapper();

            // 保留现有的 JSON 字段，如果传入对象中的 JSON 字段为 null
            if (booking.getStudentYear() != null) {
                booking.setStudentYearJson(mapper.writeValueAsString(booking.getStudentYear()));
            } else {
                booking.setStudentYearJson(existingBooking.getStudentYearJson());
            }

            if (booking.getModules() != null) {
                booking.setModulesJson(mapper.writeValueAsString(booking.getModules()));
            } else {
                booking.setModulesJson(existingBooking.getModulesJson());
            }

            if (booking.getDeliveryLocation() != null) {
                booking.setDeliveryLocationJson(mapper.writeValueAsString(booking.getDeliveryLocation()));
            } else {
                booking.setDeliveryLocationJson(existingBooking.getDeliveryLocationJson());
            }

            if (booking.getChecklistStatus() != null) {
                booking.setChecklistStatusJson(mapper.writeValueAsString(booking.getChecklistStatus()));
            } else {
                booking.setChecklistStatusJson(existingBooking.getChecklistStatusJson());
            }

        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        // 更新数据库
        bookingMapper.update(booking);
    }


}