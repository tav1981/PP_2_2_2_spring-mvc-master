package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import web.dao.CarDao;
import web.model.Car;

import java.util.List;

@Service
@Controller
@RequestMapping("/cars")
public class CarController {

    private CarDao carDao;

    @Autowired
    public CarController(CarDao carDao) {
        this.carDao = carDao;
    }

    //@Transactional(readOnly = true)
    @Transactional(readOnly = false)
    @GetMapping()
    public String printCars(ModelMap model,
                            @RequestParam(value = "count", required = false, defaultValue = "0") byte count) {

        if (carDao.listCar((byte) 1).isEmpty()) {
            carDao.add5Car();
        }

        List<Car> cars = carDao.listCar(count);

        model.addAttribute("cars", cars);

        System.out.println("Количество машин = " + count);

        return "cars";
    }
}
