package com.example.demo.admin.controller;

import com.example.demo.admin.dao.AdminRepository;
import com.example.demo.admin.service.AdminService;
import com.example.demo.admin.vo.AdminUpdateRequestDto;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.session.FindByIndexNameSessionRepository;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.security.Principal;

@RestController
@AllArgsConstructor
@Slf4j                      // 로그 확인용
public class AdminApiController {

    FindByIndexNameSessionRepository sessionRepository;

    AdminService adminService;

    AdminRepository adminRepository;

    HttpSession session;

    // 관리자 정보수정 API
    @PutMapping("/api/admin/settings/{id}")
    public Long updateForm(@PathVariable Long id, @RequestBody AdminUpdateRequestDto requestDto) {
        return adminService.update(id, requestDto);
    }

    // 관리자 정보삭제 API
    @DeleteMapping("/api/admin/delete/{id}")
    public Long delete(@PathVariable Long id, Principal principal) {
        sessionRepository.findByIndexNameAndIndexValue(FindByIndexNameSessionRepository.PRINCIPAL_NAME_INDEX_NAME,
                principal.getName()).keySet().forEach(session -> sessionRepository.deleteById((String) session));
        adminService.delete(id);

        return id;
    }
}
