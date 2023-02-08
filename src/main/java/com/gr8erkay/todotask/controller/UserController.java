package com.gr8erkay.todotask.controller;

import com.gr8erkay.todotask.enums.Status;
import com.gr8erkay.todotask.model.Task;
import com.gr8erkay.todotask.services.implementation.TaskServiceImpl;
import com.gr8erkay.todotask.services.implementation.UserServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;




import com.gr8erkay.todotask.dto.ResponseDto;
import com.gr8erkay.todotask.dto.RequestDto;
import com.gr8erkay.todotask.services.UserService;
import com.gr8erkay.todotask.model.User;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;


import java.util.List;

    @Controller
    @RequestMapping("/")
//    @RequiredArgsConstructor
    public class UserController {

        private final UserServiceImpl userService;
        private final TaskServiceImpl taskServiceImpl;

        public UserController(UserServiceImpl userService,
                          TaskServiceImpl taskServiceImpl) {
        this.userService = userService;
        this.taskServiceImpl = taskServiceImpl;
    }



        @GetMapping("/")
    public String displayIndex(Model model) {
        model.addAttribute("user", new User());
        return "index";
    }

        @GetMapping("/register")
        public String getRegisterPage(Model model) {
            model.addAttribute("user", new User());
            return "register";
        }




//        @PostMapping("/register")
//        public String registerUser(User user, Model model, RedirectAttributes redirectAttributes) {
//            ResponseDto response = userService.addUser(user);
//
//            if (response.isStatus()) {
//                redirectAttributes.addFlashAttribute("message", response.getMessage());
//                return "redirect:/";
//            }
//            model.addAttribute("message", response.getMessage());
//            return "register";
//
//        }


        @GetMapping("/")
        public String getLoginPage(Model model) {
            model.addAttribute("user", new ResponseDto());
            return "index";
        }



//        @PostMapping("/login")
//        public String loginUser(RequestDto requestDto, Model model, RedirectAttributes redirectAttributes, HttpServletRequest request) {
//            HttpSession session = request.getSession();
//            User response = userService.loginUser(requestDto);
//            if (response.isStatusCode()) {
//                redirectAttributes.addFlashAttribute("message", response.getMessage());
//                redirectAttributes.addFlashAttribute("user", response.getData());
//                session.setAttribute("logUser", response.getData());
//                return "redirect:/home";
//            }
//            model.addAttribute("message", response.getMessage());
//            return "index";
//
//        }

        /**
         * Method to get the logout page
         */
        @GetMapping("/logout")
        public String logUserOut(Model model, HttpSession httpSession) {

            if (httpSession != null) {
                httpSession.invalidate();
            }

            model.addAttribute("user", new User());
            model.addAttribute("invalid", null);
            return "redirect:/";

        }
//        @GetMapping("/home")
//    public String getHome(HttpSession httpSession, Model model) {
//        User user = (User) httpSession.getAttribute("user");
//
//        List<Task> tasks = taskServiceImpl.findAllTask(user.getUserId());
//        System.out.println(tasks);
//        model.addAttribute("tasks", tasks);
//        model.addAttribute("user", user);
//
//        return "home";
//    }

    @GetMapping("/pending-task")
    public String getPending(HttpSession httpSession, Model model) {
        User user = (User) httpSession.getAttribute("user");

        List<Task> tasks = taskServiceImpl.findTaskByStatus(Status.PENDING, user.getUserId());
        System.out.println(tasks);
        model.addAttribute("tasks", tasks);
        model.addAttribute("user", user);

        return "pending_task";
    }

    @GetMapping("/completed-task")
    public String getCompletedTask(HttpSession httpSession, Model model) {
        User user = (User) httpSession.getAttribute("user");

        List<Task> tasks = taskServiceImpl.findTaskByStatus(Status.COMPLETED, user.getUserId());
        System.out.println(tasks);
        model.addAttribute("tasks", tasks);
        model.addAttribute("user", user);

        return "completed_task";
    }

    @GetMapping("/progress-task")
    public String getInProgressTask(HttpSession httpSession, Model model) {
        User user = (User) httpSession.getAttribute("user");

        List<Task> tasks = taskServiceImpl.findTaskByStatus(Status.ONGOING, user.getUserId());
        System.out.println(tasks);
        model.addAttribute("tasks", tasks);
        model.addAttribute("user", user);

        return "in_progress_task";
    }
//        @GetMapping("/all-users")
//        public String getAllUsers (Model model, HttpSession httpSession){
//            User user = (User) httpSession.getAttribute("logUser");
//            if (user==null) return "redirect:/";
//            List<User> users = userService.getAllUser();
//            model.addAttribute("all-users", users);
//
//            return "all-users";
//
//        }

    }


//
//
//
//
//
//
////    @GetMapping("/login")
////    public String loginForm(Model model) {
////        model.addAttribute("user", new LoginDto());
////        return "home";
////    }
//
//    @PostMapping("/login")
//    public String loginUser(@ModelAttribute("user") UserRequestDto loginDto, Model model,
//                            HttpSession httpSession) {
//        User user = userService.loginUser(loginDto);
//        httpSession.setAttribute("user", user);  //It makes user available in the homepage
//        List<Task> tasks = taskServiceImpl.findAllTask(user.getUserId());
//        httpSession.setAttribute("task", tasks);   //Saving the task session
//        model.addAttribute("tasks", tasks);  //It will make the task available for us to use in the homepage
//        if(Objects.nonNull(user)) {
//            return "redirect:/home";
//        } else {
//            return "redirect:/";
//        }
//    }
//    @GetMapping("/regSignIn")
//    public String loginUser(Model model) {
//        model.addAttribute("user", new LoginDto());
//        return "redirect:/";
//    }
//
//
