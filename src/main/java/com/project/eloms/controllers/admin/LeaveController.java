package com.project.eloms.controllers.admin;

import com.project.eloms.dtos.LeaveDto;
import com.project.eloms.dtos.ResponseDto;
import com.project.eloms.services.admin.LeaveService;
import com.project.eloms.types.MessageType;
import com.project.eloms.utils.ResponseUtility;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/admin/leave")
public class LeaveController {

    private final LeaveService leaveService;

    @PostMapping("/set")
    public ResponseDto setLeave(@RequestBody LeaveDto dto){
        return leaveService.setLeave(dto);
    }

    @PostMapping("/list")
    public ResponseDto listLeave(){
        return leaveService.listLeave();
    }

    @PostMapping("/delete/{leaveId}")
    public ResponseDto deleteLeaveById(@PathVariable Long leaveId){
        return leaveService.deleteLeave(leaveId);
    }
}
