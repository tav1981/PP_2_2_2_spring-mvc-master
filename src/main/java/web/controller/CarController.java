package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import web.model.Car;
import web.service.CarService;

import java.util.List;


@Controller
@RequestMapping("/cars")
public class CarController {

    @Autowired
    private CarService carService;

    @Transactional(readOnly = false)
    @GetMapping()
    public String printCars(ModelMap model,
                            @RequestParam(value = "count", required = false, defaultValue = "0") byte count) {

        if (carService.listCar((byte) 1).isEmpty()) {
            carService.add(new Car("car1", 1, 1L));
            carService.add(new Car("car2", 2, 1L));
            carService.add(new Car("car3", 3, 1L));
            carService.add(new Car("car4", 4, 1L));
            carService.add(new Car("car5", 5, 1L));
        }

        List<Car> cars = carService.listCar(count);

        model.addAttribute("cars", cars);

        System.out.println("Количество машин = " + count);

        return "cars";
    }
}
