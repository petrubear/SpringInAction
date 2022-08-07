package emg.taco.tacocloud.controller

import emg.taco.tacocloud.service.OrderAdminService
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping

import org.springframework.web.bind.annotation.RequestMapping


@Controller
@RequestMapping("/admin")
class AdminController(private val adminService: OrderAdminService) {

    @GetMapping
    fun adminPage(): String {
        return "admin"
    }

    @PostMapping("/deleteOrders")
    fun deleteAllOrders(): String {
        adminService.deleteAllOrders()
        return "redirect:/admin"
    }
}