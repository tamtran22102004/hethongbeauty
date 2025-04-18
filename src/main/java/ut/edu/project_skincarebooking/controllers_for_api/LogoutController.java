package ut.edu.project_skincarebooking.controllers_for_api;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LogoutController {

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        // Xóa toàn bộ dữ liệu session
        session.invalidate();
        // Chuyển hướng về trang chủ sau khi logout
        return "redirect:/";
    }
}
