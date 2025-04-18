package ut.edu.project_skincarebooking.controllers_for_api;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ut.edu.project_skincarebooking.models.Booking;
import ut.edu.project_skincarebooking.models.Service;
import ut.edu.project_skincarebooking.models.User;
import ut.edu.project_skincarebooking.repositories.BookingRepository;
import ut.edu.project_skincarebooking.repositories.ServiceRepository;

import jakarta.servlet.http.HttpSession;

@Controller
@RequiredArgsConstructor
@RequestMapping("/booking")
public class BookingController {

    private final ServiceRepository serviceRepo;
    private final BookingRepository bookingRepo;

    @GetMapping("/new")
    public String showBookingForm(@RequestParam("serviceId") Long serviceId, HttpSession session, Model model) {
        Service service = serviceRepo.findById(serviceId).orElseThrow();
        Booking booking = new Booking();
        booking.setService(service);

        // Lấy thông tin người dùng từ session (giống cách HomeController xử lý)
        User user = (User) session.getAttribute("user");
        if (user != null) {
            booking.setUser(user); // Gán người dùng vào booking
        }

        model.addAttribute("booking", booking);
        return "booking_form"; // Tên trang tạo booking
    }

    @PostMapping("/save")
    public String saveBooking(@ModelAttribute Booking booking, HttpSession session, Model model) {
        // Kiểm tra nếu không có người dùng trong booking
        User user = (User) session.getAttribute("user");
        if (user == null) {
            model.addAttribute("error", "Bạn cần đăng nhập để thực hiện đặt lịch.");
            return "redirect:/login"; // Nếu không có người dùng thì yêu cầu đăng nhập
        }

        // Nếu user chưa được gán, gán người dùng từ session vào booking
        if (booking.getUser() == null) {
            booking.setUser(user);
        }

        bookingRepo.save(booking);
        return "redirect:/"; // Quay về trang chủ sau khi lưu booking
    }
    @GetMapping("/list")
    public String listBookings(Model model, HttpSession session) {
        User user = (User) session.getAttribute("user");

        if (user == null) {
            return "redirect:/login";
        }

        // Lấy danh sách booking theo user
        model.addAttribute("bookings", bookingRepo.findByUser(user));
        return "booking_list";
    }

}

