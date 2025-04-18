package ut.edu.project_skincarebooking.controllers_for_api;

import ut.edu.project_skincarebooking.repositories.ServiceRepository;
import ut.edu.project_skincarebooking.models.User;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/services")
public class ServiceController {

    @Autowired
    private ServiceRepository serviceRepository;

    // Hiển thị danh sách dịch vụ
    @GetMapping
    public String getAllServices(HttpSession session, Model model) {
        // Kiểm tra người dùng trong session
        User user = (User) session.getAttribute("user");
        if (user != null) {
            model.addAttribute("username", user.getUsername()); // Thêm tên người dùng vào model
        }

        // Thêm danh sách dịch vụ vào model
        model.addAttribute("services", serviceRepository.findAll());

        return "service"; // Trang hiển thị danh sách dịch vụ
    }
}
