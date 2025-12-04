package com.project.eloms.services.admin;

import com.project.eloms.dtos.LeaveDto;
import com.project.eloms.dtos.ResponseDto;
import com.project.eloms.entities.Leave;
import com.project.eloms.repositories.LeaveRepository;
import com.project.eloms.types.MessageType;
import com.project.eloms.utils.ResponseUtility;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@Service
public class LeaveService {

    private final LeaveRepository leaveRepository;

    public ResponseDto setLeave(LeaveDto dto){
       if (dto == null)
           return ResponseUtility.getErrorResponse("E0001", MessageType.DTO_IS_NULL);

       Leave  leave = leaveRepository.findByLeaveType(dto.getLeaveType()).orElse(null);

        if (leave == null){
            leave = new Leave();
            leave.setCreatedBy("ADMIN");
            leave.setCreatedAt(new Date());
        }
        SetLeaveFromDto(leave, dto);
        leave.setUpdatedAt(new Date());
        leave.setUpdatedBy("ADMIN");
        leaveRepository.save(leave);

        return ResponseUtility.getSuccessResponse(MessageType.SUCCESSFULLY_SAVED);
    }

    private void SetLeaveFromDto(Leave leave, LeaveDto dto) {
        String leaveType = dto.getLeaveType();
        Boolean isActive = dto.getIsActive();

        if(leaveType != null && !leaveType.isBlank())
            leave.setLeaveType(leaveType);

        leave.setIsActive(isActive != null? isActive: Boolean.TRUE);
    }

    public ResponseDto listLeave() {
        List<Map<String,Object>> list = leaveRepository.getLeaveList();

        return ResponseUtility.getSuccessResponse(MessageType.SUCCESSFULLY_FETCHED_DATA,list);
    }


    public ResponseDto deleteLeave(Long leaveId) {
        Leave leave = leaveRepository.findById(leaveId).orElse(null);
        if (leave == null)
            return ResponseUtility.getErrorResponse("E0001", MessageType.LEAVE_NOT_EXIST);

        leaveRepository.deleteById(leaveId);

        return ResponseUtility.getSuccessResponse(MessageType.LEAVE_SUCCESSFULLY_DELETED);
    }
}
